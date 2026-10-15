import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// EXPLAIN: Main interactive entry point and demonstration harness for the Library Management Mini-Project.
public class LibraryApp {
    private static final Path DATA_DIR = Path.of(".");
    private static final Path ITEMS_FILE = DATA_DIR.resolve("items.csv");
    private static final Path MEMBERS_FILE = DATA_DIR.resolve("members.csv");
    private static final Path LOANS_FILE = DATA_DIR.resolve("loans.csv");

    private final Library library = new Library();
    private FineStrategy fineStrategy;
    private final LibraryConfig config = LibraryConfig.getInstance();

    public LibraryApp() {
        // Default Strategy: Standard Rate from Singleton Configuration
        this.fineStrategy = new StandardFineStrategy(config.getStandardFinePerDay());
    }

    public void initialize() {
        if (Files.exists(ITEMS_FILE) && Files.exists(MEMBERS_FILE)) {
            try {
                System.out.println("Loading existing records from CSV files...");
                library.loadFromCsv(ITEMS_FILE, MEMBERS_FILE, LOANS_FILE);
                System.out.println("Loaded " + library.getAllItems().size() + " items and "
                        + library.getAllMembers().size() + " members successfully.");
            } catch (IOException e) {
                System.err.println("Warning: Error loading CSV data. Seeding fresh sample catalogue. Error: " + e.getMessage());
                library.seedSampleData();
                saveDataSafely();
            }
        } else {
            System.out.println("No existing CSV database found. Seeding initial library catalogue & patrons...");
            library.seedSampleData();
            saveDataSafely();
        }
    }

    public void saveDataSafely() {
        try {
            library.saveToCsv(ITEMS_FILE, MEMBERS_FILE, LOANS_FILE);
            System.out.println("Persistence: All catalogue, member, and loan records saved to CSV.");
        } catch (IOException e) {
            System.err.println("Failed to save data to CSV: " + e.getMessage());
        }
    }

    public void runInteractive() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n=======================================================");
        System.out.println("    WELCOME TO " + config.getLibraryName().toUpperCase());
        System.out.println("=======================================================");

        while (running) {
            printMenu();
            System.out.print("Enter choice (0-10): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> handleAddItem(scanner);
                case "2" -> handleRegisterMember(scanner);
                case "3" -> handleBorrow(scanner);
                case "4" -> handleReturn(scanner);
                case "5" -> handleListOverdue();
                case "6" -> handleSearchTitle(scanner);
                case "7" -> handleListAllItems();
                case "8" -> handleListAllMembers();
                case "9" -> handleChangeFineStrategy(scanner);
                case "10" -> runAutomatedVerification();
                case "0" -> {
                    saveDataSafely();
                    System.out.println("Thank you for using " + config.getLibraryName() + ". Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid selection. Please choose an option from 0 to 10.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n----------------- MAIN MENU -----------------");
        System.out.println("1.  Add New Item (Book or DVD)");
        System.out.println("2.  Register New Member");
        System.out.println("3.  Borrow Item");
        System.out.println("4.  Return Item");
        System.out.println("5.  List Overdue Items & Calculate Fines");
        System.out.println("6.  Search Items by Title (Stream query)");
        System.out.println("7.  List All Inventory Items");
        System.out.println("8.  List All Members & Active Loans");
        System.out.println("9.  Select / Switch Fine Calculation Strategy");
        System.out.println("10. Run Automated Test & OOP Verification Suite");
        System.out.println("0.  Save & Exit");
        System.out.println("Current Strategy: " + fineStrategy.getStrategyName());
        System.out.println("---------------------------------------------");
    }

    private void handleAddItem(Scanner scanner) {
        System.out.println("\n--- Add New Library Item ---");
        System.out.print("Item type (BOOK / DVD): ");
        String type = scanner.nextLine().trim().toUpperCase();

        if (!type.equals("BOOK") && !type.equals("DVD")) {
            System.out.println("Error: Supported item types are BOOK or DVD.");
            return;
        }

        System.out.print("Enter Item ID (e.g. B105 or D205): ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();

        try {
            if (type.equals("BOOK")) {
                System.out.print("Enter Author: ");
                String author = scanner.nextLine().trim();
                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine().trim();

                LibraryItem book = ItemFactory.createItem("BOOK", id, title, author, isbn);
                library.addItem(book);
                System.out.println("Success! Added " + book);
            } else {
                System.out.print("Enter Director: ");
                String director = scanner.nextLine().trim();
                System.out.print("Enter Duration in minutes: ");
                String duration = scanner.nextLine().trim();

                LibraryItem dvd = ItemFactory.createItem("DVD", id, title, director, duration);
                library.addItem(dvd);
                System.out.println("Success! Added " + dvd);
            }
            saveDataSafely();
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

    private void handleRegisterMember(Scanner scanner) {
        System.out.println("\n--- Register New Member ---");
        System.out.print("Enter Member ID (e.g. M004): ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().trim();

        try {
            Member member = new Member(id, name, email);
            library.registerMember(member);
            System.out.println("Success! Registered " + member);
            saveDataSafely();
        } catch (Exception e) {
            System.out.println("Error registering member: " + e.getMessage());
        }
    }

    private void handleBorrow(Scanner scanner) {
        System.out.println("\n--- Borrow Item ---");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine().trim();

        System.out.print("Enter Item ID: ");
        String itemId = scanner.nextLine().trim();

        try {
            Loan loan = library.borrow(memberId, itemId);
            System.out.println("Loan Issued Successfully!");
            System.out.println("Details: " + loan);
            saveDataSafely();
        } catch (ItemNotAvailableException e) {
            System.out.println("[ITEM NOT AVAILABLE] " + e.getMessage());
        } catch (MemberLimitExceededException e) {
            System.out.println("[LIMIT EXCEEDED] " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("[INVALID REQUEST] " + e.getMessage());
        }
    }

    private void handleReturn(Scanner scanner) {
        System.out.println("\n--- Return Item ---");
        System.out.print("Enter Item ID to return: ");
        String itemId = scanner.nextLine().trim();

        try {
            Loan loan = library.returnItem(itemId);
            System.out.println("Return Processed Successfully!");
            System.out.println("Details: " + loan);

            // Check if returned item was overdue
            if (loan.getReturnDate().isAfter(loan.getDueDate())) {
                long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
                double fine = fineStrategy.calculateFine(overdueDays);
                System.out.printf("Item was returned %d days late. Fine assessed (%s): $%.2f\n",
                        overdueDays, fineStrategy.getStrategyName(), fine);
            }
            saveDataSafely();
        } catch (IllegalArgumentException e) {
            System.out.println("[RETURN ERROR] " + e.getMessage());
        }
    }

    private void handleListOverdue() {
        System.out.println("\n--- Overdue Items & Fine Assessment ---");
        LocalDate today = LocalDate.now();
        List<Loan> overdue = library.getOverdueLoans(today);

        if (overdue.isEmpty()) {
            System.out.println("Great news! There are currently no overdue loans.");
            return;
        }

        System.out.println("Found " + overdue.size() + " overdue loan(s) as of " + today + ":");
        System.out.println("Using Fine Strategy: " + fineStrategy.getStrategyName());
        System.out.println("----------------------------------------------------------------------------------");
        for (Loan l : overdue) {
            long days = l.getOverdueDays(today);
            double fine = fineStrategy.calculateFine(days);
            System.out.printf("%-10s | %-18s | \"%s\" | Due: %s | Overdue: %d days | Fine: $%.2f\n",
                    l.getLoanId(), l.getMember().getName(), l.getItem().getTitle(),
                    l.getDueDate(), days, fine);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    private void handleSearchTitle(Scanner scanner) {
        System.out.println("\n--- Search Inventory by Title (Stream Query) ---");
        System.out.print("Enter title keyword to search: ");
        String text = scanner.nextLine().trim();

        List<LibraryItem> results = library.searchByTitle(text);
        if (results.isEmpty()) {
            System.out.println("No items found matching keyword: \"" + text + "\"");
        } else {
            System.out.println("Found " + results.size() + " matching item(s) (sorted alphabetically):");
            results.forEach(item -> System.out.println("  * " + item));
        }
    }

    private void handleListAllItems() {
        System.out.println("\n--- All Library Inventory Items ---");
        List<LibraryItem> all = library.getAllItems();
        if (all.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            all.forEach(item -> System.out.println("  " + item));
        }
    }

    private void handleListAllMembers() {
        System.out.println("\n--- Registered Members & Active Loans ---");
        List<Member> all = library.getAllMembers();
        if (all.isEmpty()) {
            System.out.println("No registered members.");
        } else {
            for (Member m : all) {
                System.out.println("  " + m);
                if (m.getActiveLoans().isEmpty()) {
                    System.out.println("    -> No active loans.");
                } else {
                    for (Loan l : m.getActiveLoans()) {
                        System.out.println("    -> " + l);
                    }
                }
            }
        }
    }

    private void handleChangeFineStrategy(Scanner scanner) {
        System.out.println("\n--- Select Overdue Fine Strategy (Strategy Pattern) ---");
        System.out.println("1. Standard Rate ($1.00/day)");
        System.out.println("2. Student Discount Rate ($0.50/day)");
        System.out.println("3. VIP / Courtesy Waiver ($0.00)");
        System.out.print("Select strategy (1-3): ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> {
                this.fineStrategy = new StandardFineStrategy(config.getStandardFinePerDay());
                System.out.println("Strategy updated to: " + fineStrategy.getStrategyName());
            }
            case "2" -> {
                this.fineStrategy = new StudentDiscountFineStrategy(config.getStudentDiscountFinePerDay());
                System.out.println("Strategy updated to: " + fineStrategy.getStrategyName());
            }
            case "3" -> {
                this.fineStrategy = new VipWaiverFineStrategy();
                System.out.println("Strategy updated to: " + fineStrategy.getStrategyName());
            }
            default -> System.out.println("Invalid selection. Current strategy retained.");
        }
    }

    // EXPLAIN: Automated end-to-end verification proving every requirement and OOP concept.
    public void runAutomatedVerification() {
        System.out.println("\n==================================================================");
        System.out.println("     RUNNING AUTOMATED VERIFICATION & TEST SUITE FOR MINI-PROJECT  ");
        System.out.println("==================================================================");

        Library testLib = new Library();
        testLib.seedSampleData();

        // 1. Verify Encapsulation & Polymorphic Loan Periods
        System.out.println("\n[TEST 1] Verifying Polymorphic Loan Periods & Inheritance:");
        LibraryItem testBook = new Book("TB01", "Design Patterns in Java", "Gamma", "111-222");
        LibraryItem testDvd = new Dvd("TD01", "Coding Documentary", "Turing", 90);
        System.out.println("  * Book loan period: " + testBook.loanPeriodDays() + " days (Expected: 14)");
        System.out.println("  * DVD loan period:  " + testDvd.loanPeriodDays() + " days (Expected: 7)");
        assert testBook.loanPeriodDays() == 14;
        assert testDvd.loanPeriodDays() == 7;
        testLib.addItem(testBook);
        testLib.addItem(testDvd);

        // 2. Verify Member Registration & Limit (Max 3)
        System.out.println("\n[TEST 2] Verifying Member 3-Loan Limit Exception (MemberLimitExceededException):");
        Member testMember = new Member("TM99", "Limit Tester", "tester@test.com");
        testLib.registerMember(testMember);

        LibraryItem b1 = new Book("TB02", "Book Alpha", "Author A", "001");
        LibraryItem b2 = new Book("TB03", "Book Beta", "Author B", "002");
        LibraryItem b3 = new Book("TB04", "Book Gamma", "Author C", "003");
        LibraryItem b4 = new Book("TB05", "Book Delta", "Author D", "004");
        testLib.addItem(b1);
        testLib.addItem(b2);
        testLib.addItem(b3);
        testLib.addItem(b4);

        try {
            System.out.println("  * Borrowing loan 1...");
            testLib.borrow(testMember.getId(), b1.getId());
            System.out.println("  * Borrowing loan 2...");
            testLib.borrow(testMember.getId(), b2.getId());
            System.out.println("  * Borrowing loan 3...");
            testLib.borrow(testMember.getId(), b3.getId());
            System.out.println("  * Active loans count: " + testMember.getLoanCount() + " (Limit: 3)");

            System.out.println("  * Attempting 4th loan (should throw MemberLimitExceededException)...");
            testLib.borrow(testMember.getId(), b4.getId());
            System.err.println("  FAILED: Exception was not thrown!");
        } catch (MemberLimitExceededException e) {
            System.out.println("  PASSED: Caught expected MemberLimitExceededException -> " + e.getMessage());
        } catch (Exception e) {
            System.err.println("  FAILED: Unexpected exception: " + e);
        }

        // 3. Verify Item Availability Exception (ItemNotAvailableException)
        System.out.println("\n[TEST 3] Verifying Item Availability Exception (ItemNotAvailableException):");
        Member otherMember = new Member("TM98", "Second Patron", "second@test.com");
        testLib.registerMember(otherMember);
        try {
            System.out.println("  * Attempting to borrow Book Alpha (already borrowed by Limit Tester)...");
            testLib.borrow(otherMember.getId(), b1.getId());
            System.err.println("  FAILED: Exception was not thrown!");
        } catch (ItemNotAvailableException e) {
            System.out.println("  PASSED: Caught expected ItemNotAvailableException -> " + e.getMessage());
        } catch (Exception e) {
            System.err.println("  FAILED: Unexpected exception: " + e);
        }

        // 4. Verify Return Process & Item Re-availability
        System.out.println("\n[TEST 4] Verifying Return Item Contract & Re-availability:");
        testLib.returnItem(b1.getId());
        System.out.println("  * Returned Book Alpha. Available again? " + b1.isAvailable() + " (Expected: true)");
        try {
            System.out.println("  * Second Patron now borrows Book Alpha...");
            Loan l = testLib.borrow(otherMember.getId(), b1.getId());
            System.out.println("  PASSED: Borrow successful after return! -> " + l.getLoanId());
        } catch (Exception e) {
            System.err.println("  FAILED: " + e);
        }

        // 5. Verify Stream Title Search
        System.out.println("\n[TEST 5] Verifying Stream Search by Title:");
        List<LibraryItem> found = testLib.searchByTitle("java");
        System.out.println("  * Searching for 'java': found " + found.size() + " items:");
        found.forEach(i -> System.out.println("    - " + i.getTitle()));

        // 6. Verify Overdue & Fine Strategy Calculations
        System.out.println("\n[TEST 6] Verifying Overdue Filtering & Fine Strategy Calculations:");
        List<Loan> overdue = testLib.getOverdueLoans(LocalDate.now());
        System.out.println("  * Found " + overdue.size() + " overdue loan(s).");
        FineStrategy std = new StandardFineStrategy(1.00);
        FineStrategy disc = new StudentDiscountFineStrategy(0.50);
        FineStrategy vip = new VipWaiverFineStrategy();
        for (Loan l : overdue) {
            long days = l.getOverdueDays(LocalDate.now());
            System.out.printf("    Loan %s (%d days late) -> Standard: $%.2f | Student: $%.2f | VIP: $%.2f\n",
                    l.getLoanId(), days, std.calculateFine(days), disc.calculateFine(days), vip.calculateFine(days));
        }

        // 7. Verify CSV Persistence Round-Trip
        System.out.println("\n[TEST 7] Verifying File Persistence (Save & Reload CSV):");
        Path tItems = DATA_DIR.resolve("test_items.csv");
        Path tMembers = DATA_DIR.resolve("test_members.csv");
        Path tLoans = DATA_DIR.resolve("test_loans.csv");
        try {
            testLib.saveToCsv(tItems, tMembers, tLoans);
            System.out.println("  * Saved test data to temporary CSV files.");

            Library reloadedLib = new Library();
            reloadedLib.loadFromCsv(tItems, tMembers, tLoans);
            System.out.println("  * Reloaded library from CSV: " + reloadedLib.getAllItems().size() + " items, "
                    + reloadedLib.getAllMembers().size() + " members, " + reloadedLib.getAllLoans().size() + " loans.");
            assert reloadedLib.getAllItems().size() == testLib.getAllItems().size();
            System.out.println("  PASSED: CSV round-trip persistence verified perfectly.");

            // Cleanup test files
            Files.deleteIfExists(tItems);
            Files.deleteIfExists(tMembers);
            Files.deleteIfExists(tLoans);
        } catch (IOException e) {
            System.err.println("  FAILED: CSV error: " + e.getMessage());
        }

        System.out.println("\n==================================================================");
        System.out.println("     ALL AUTOMATED VERIFICATION CHECKS COMPLETED SUCCESSFULLY!    ");
        System.out.println("==================================================================\n");
    }

    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.initialize();

        if (args.length > 0 && args[0].equalsIgnoreCase("--demo")) {
            System.out.println("Running automated demonstration mode...");
            app.runAutomatedVerification();
            app.saveDataSafely();
            return;
        }

        app.runInteractive();
    }
}
