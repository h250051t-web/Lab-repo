import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

// EXPLAIN: Core domain service managing library inventory, patrons, loans, and persistence.
public class Library {
    private final Map<String, LibraryItem> items = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final Map<String, Loan> loans = new HashMap<>();
    private final List<LibraryObserver> observers = new ArrayList<>();

    private int nextLoanNumber = 1001;

    public Library() {
        // Register default audit logger observer
        addObserver(new ConsoleAuditLogger());
    }

    public void addObserver(LibraryObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    // ---------- MEMBER MANAGEMENT ----------
    public void registerMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null.");
        }
        if (members.containsKey(member.getId())) {
            throw new IllegalArgumentException("Member with ID '" + member.getId() + "' already exists.");
        }
        members.put(member.getId(), member);
    }

    public Optional<Member> findMember(String memberId) {
        if (memberId == null) return Optional.empty();
        return Optional.ofNullable(members.get(memberId.trim()));
    }

    public List<Member> getAllMembers() {
        return members.values().stream()
                .sorted(Comparator.comparing(Member::getId))
                .toList();
    }

    // ---------- ITEM MANAGEMENT ----------
    public void addItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("Item with ID '" + item.getId() + "' already exists.");
        }
        items.put(item.getId(), item);
    }

    public Optional<LibraryItem> findItem(String itemId) {
        if (itemId == null) return Optional.empty();
        return Optional.ofNullable(items.get(itemId.trim()));
    }

    public List<LibraryItem> getAllItems() {
        return items.values().stream()
                .sorted(Comparator.comparing(LibraryItem::getId))
                .toList();
    }

    // EXPLAIN: STREAMS API - Case-insensitive search by title, sorted alphabetically.
    public List<LibraryItem> searchByTitle(String text) {
        if (text == null || text.isBlank()) {
            return Collections.emptyList();
        }
        String query = text.trim().toLowerCase();
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(query))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    // ---------- BORROW & RETURN OPERATIONS ----------
    public Loan borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. Look up member and item (throw IllegalArgumentException if missing)
        Member member = findMember(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member with ID '" + memberId + "' was not found."));

        LibraryItem item = findItem(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item with ID '" + itemId + "' was not found."));

        // 2. Check item availability and member loan limit
        if (!item.isAvailable()) {
            throw new ItemNotAvailableException("Item '" + item.getTitle() + "' (ID: " + itemId + ") is currently checked out.");
        }
        if (!member.canBorrow()) {
            throw new MemberLimitExceededException("Member '" + member.getName() + "' (ID: " + memberId
                    + ") has already reached the maximum loan limit of " + member.getMaxLoans() + " items.");
        }

        // 3. Update item state via Borrowable contract
        item.borrow(member);

        // 4. Create Loan with calculated polymorphic due date
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(item.loanPeriodDays());
        String loanId = "L" + (nextLoanNumber++);

        Loan loan = new Loan(loanId, member, item, issueDate, dueDate);
        member.addLoan(loan);
        loans.put(loanId, loan);

        // 5. Notify Observers
        for (LibraryObserver observer : observers) {
            observer.onItemBorrowed(loan);
        }

        return loan;
    }

    public Loan returnItem(String itemId) {
        if (itemId == null || itemId.isBlank()) {
            throw new IllegalArgumentException("Item ID cannot be null or empty.");
        }

        LibraryItem item = findItem(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item with ID '" + itemId + "' was not found."));

        // Find active loan for this item
        Loan activeLoan = loans.values().stream()
                .filter(l -> !l.isReturned() && l.getItem().getId().equalsIgnoreCase(itemId.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No active loan found for item ID '" + itemId + "'."));

        // Update item and loan state
        item.giveBack();
        LocalDate returnDate = LocalDate.now();
        activeLoan.markReturned(returnDate);
        activeLoan.getMember().removeLoan(activeLoan);

        // Notify Observers
        for (LibraryObserver observer : observers) {
            observer.onItemReturned(activeLoan);
        }

        return activeLoan;
    }

    // EXPLAIN: STREAMS API - Identifies active overdue items and triggers overdue alerts.
    public List<Loan> getOverdueLoans(LocalDate referenceDate) {
        LocalDate ref = (referenceDate != null) ? referenceDate : LocalDate.now();

        List<Loan> overdue = loans.values().stream()
                .filter(l -> l.isOverdue(ref))
                .sorted(Comparator.comparing(Loan::getDueDate))
                .toList();

        // Alert observers for each overdue item
        for (Loan loan : overdue) {
            long days = loan.getOverdueDays(ref);
            for (LibraryObserver observer : observers) {
                observer.onOverdueAlert(loan, days);
            }
        }

        return overdue;
    }

    public List<Loan> getActiveLoans() {
        return loans.values().stream()
                .filter(l -> !l.isReturned())
                .sorted(Comparator.comparing(Loan::getIssueDate).reversed())
                .toList();
    }

    public List<Loan> getAllLoans() {
        return loans.values().stream()
                .sorted(Comparator.comparing(Loan::getLoanId))
                .toList();
    }

    // ---------- FILE PERSISTENCE (CSV) ----------
    public void saveToCsv(Path itemsFile, Path membersFile, Path loansFile) throws IOException {
        // 1. Save Items
        try (BufferedWriter writer = Files.newBufferedWriter(itemsFile)) {
            writer.write("TYPE,ID,TITLE,AVAILABLE,EXTRA1,EXTRA2\n");
            for (LibraryItem item : items.values()) {
                if (item instanceof Book b) {
                    writer.write(String.format("BOOK,%s,%s,%b,%s,%s\n",
                            escapeCsv(b.getId()), escapeCsv(b.getTitle()), b.isAvailable(),
                            escapeCsv(b.getAuthor()), escapeCsv(b.getIsbn())));
                } else if (item instanceof Dvd d) {
                    writer.write(String.format("DVD,%s,%s,%b,%s,%d\n",
                            escapeCsv(d.getId()), escapeCsv(d.getTitle()), d.isAvailable(),
                            escapeCsv(d.getDirector()), d.getDurationMinutes()));
                }
            }
        }

        // 2. Save Members
        try (BufferedWriter writer = Files.newBufferedWriter(membersFile)) {
            writer.write("ID,NAME,EMAIL\n");
            for (Member m : members.values()) {
                writer.write(String.format("%s,%s,%s\n",
                        escapeCsv(m.getId()), escapeCsv(m.getName()), escapeCsv(m.getEmail())));
            }
        }

        // 3. Save Loans
        try (BufferedWriter writer = Files.newBufferedWriter(loansFile)) {
            writer.write("LOAN_ID,MEMBER_ID,ITEM_ID,ISSUE_DATE,DUE_DATE,RETURN_DATE\n");
            for (Loan l : loans.values()) {
                String ret = l.isReturned() ? l.getReturnDate().format(Loan.DATE_FMT) : "null";
                writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
                        l.getLoanId(), l.getMember().getId(), l.getItem().getId(),
                        l.getIssueDate().format(Loan.DATE_FMT),
                        l.getDueDate().format(Loan.DATE_FMT),
                        ret));
            }
        }
    }

    public void loadFromCsv(Path itemsFile, Path membersFile, Path loansFile) throws IOException {
        items.clear();
        members.clear();
        loans.clear();
        int maxLoanIdNumber = 1000;

        // 1. Load Items
        if (Files.exists(itemsFile)) {
            try (BufferedReader reader = Files.newBufferedReader(itemsFile)) {
                String line = reader.readLine(); // skip header
                while ((line = reader.readLine()) != null) {
                    if (line.isBlank()) continue;
                    String[] parts = line.split(",", -1);
                    if (parts.length >= 6) {
                        String type = parts[0];
                        String id = parts[1];
                        String title = parts[2];
                        boolean available = Boolean.parseBoolean(parts[3]);
                        String extra1 = parts[4];
                        String extra2 = parts[5];

                        LibraryItem item = ItemFactory.createItem(type, id, title, extra1, extra2);
                        item.setAvailable(available);
                        items.put(item.getId(), item);
                    }
                }
            }
        }

        // 2. Load Members
        if (Files.exists(membersFile)) {
            try (BufferedReader reader = Files.newBufferedReader(membersFile)) {
                String line = reader.readLine(); // skip header
                while ((line = reader.readLine()) != null) {
                    if (line.isBlank()) continue;
                    String[] parts = line.split(",", -1);
                    if (parts.length >= 3) {
                        Member member = new Member(parts[0], parts[1], parts[2]);
                        members.put(member.getId(), member);
                    }
                }
            }
        }

        // 3. Load Loans
        if (Files.exists(loansFile)) {
            try (BufferedReader reader = Files.newBufferedReader(loansFile)) {
                String line = reader.readLine(); // skip header
                while ((line = reader.readLine()) != null) {
                    if (line.isBlank()) continue;
                    String[] parts = line.split(",", -1);
                    if (parts.length >= 6) {
                        String loanId = parts[0];
                        String memberId = parts[1];
                        String itemId = parts[2];
                        LocalDate issueDate = LocalDate.parse(parts[3], Loan.DATE_FMT);
                        LocalDate dueDate = LocalDate.parse(parts[4], Loan.DATE_FMT);
                        LocalDate returnDate = "null".equalsIgnoreCase(parts[5].trim()) ? null
                                : LocalDate.parse(parts[5], Loan.DATE_FMT);

                        Member member = members.get(memberId);
                        LibraryItem item = items.get(itemId);

                        if (member != null && item != null) {
                            Loan loan = new Loan(loanId, member, item, issueDate, dueDate, returnDate);
                            loans.put(loanId, loan);
                            if (returnDate == null) {
                                member.addLoan(loan);
                                item.setAvailable(false);
                            }
                            try {
                                int num = Integer.parseInt(loanId.replace("L", ""));
                                if (num > maxLoanIdNumber) maxLoanIdNumber = num;
                            } catch (NumberFormatException ignored) {}
                        }
                    }
                }
            }
        }

        this.nextLoanNumber = maxLoanIdNumber + 1;
    }

    // EXPLAIN: Seeds realistic sample library catalogue and patrons if starting fresh.
    public void seedSampleData() {
        // Books
        addItem(new Book("B101", "Effective Java", "Joshua Bloch", "978-0134685991"));
        addItem(new Book("B102", "Clean Code", "Robert C. Martin", "978-0132350884"));
        addItem(new Book("B103", "Design Patterns", "Gang of Four", "978-0201633610"));
        addItem(new Book("B104", "Java: The Complete Reference", "Herbert Schildt", "978-1260440232"));

        // DVDs
        addItem(new Dvd("D201", "Inception", "Christopher Nolan", 148));
        addItem(new Dvd("D202", "The Matrix", "Lana & Lilly Wachowski", 136));
        addItem(new Dvd("D203", "Interstellar", "Christopher Nolan", 169));

        // Members
        Member m1 = new Member("M001", "Alice Smith", "alice@university.edu");
        Member m2 = new Member("M002", "Bob Johnson", "bob@university.edu");
        Member m3 = new Member("M003", "Charlie Brown", "charlie@university.edu");
        registerMember(m1);
        registerMember(m2);
        registerMember(m3);

        // Create sample loan (including an overdue loan for demonstration)
        try {
            // Alice borrowed Effective Java (Issued 20 days ago -> 6 days overdue on a 14-day book loan)
            LibraryItem item1 = items.get("B101");
            item1.setAvailable(false);
            LocalDate issue1 = LocalDate.now().minusDays(20);
            LocalDate due1 = issue1.plusDays(item1.loanPeriodDays());
            Loan overdueLoan = new Loan("L1001", m1, item1, issue1, due1);
            m1.addLoan(overdueLoan);
            loans.put("L1001", overdueLoan);

            // Bob borrowed The Matrix (Issued 2 days ago -> active, 5 days remaining)
            LibraryItem item2 = items.get("D202");
            item2.setAvailable(false);
            LocalDate issue2 = LocalDate.now().minusDays(2);
            LocalDate due2 = issue2.plusDays(item2.loanPeriodDays());
            Loan activeLoan = new Loan("L1002", m2, item2, issue2, due2);
            m2.addLoan(activeLoan);
            loans.put("L1002", activeLoan);

            nextLoanNumber = 1003;
        } catch (Exception e) {
            System.err.println("Notice: Sample loan setup issue: " + e.getMessage());
        }
    }

    private String escapeCsv(String value) {
        if (value == null) return "";
        return value.replace(",", " "); // simple CSV safety
    }
}
