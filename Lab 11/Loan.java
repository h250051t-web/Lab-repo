import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

// EXPLAIN: Represents a borrowing transaction linking a Member and a LibraryItem.
public class Loan {
    public static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    private final String loanId;
    private final Member member;
    private final LibraryItem item;
    private final LocalDate issueDate;
    private final LocalDate dueDate;
    private LocalDate returnDate; // null if actively on loan

    public Loan(String loanId, Member member, LibraryItem item, LocalDate issueDate, LocalDate dueDate) {
        this(loanId, member, item, issueDate, dueDate, null);
    }

    public Loan(String loanId, Member member, LibraryItem item, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate) {
        if (loanId == null || loanId.isBlank()) {
            throw new IllegalArgumentException("Loan ID cannot be null or empty.");
        }
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null.");
        }
        if (item == null) {
            throw new IllegalArgumentException("Library item cannot be null.");
        }
        if (issueDate == null) {
            throw new IllegalArgumentException("Issue date cannot be null.");
        }
        if (dueDate == null || dueDate.isBefore(issueDate)) {
            throw new IllegalArgumentException("Due date cannot be null and must be after or on issue date.");
        }
        this.loanId = loanId.trim();
        this.member = member;
        this.item = item;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public Member getMember() {
        return member;
    }

    public LibraryItem getItem() {
        return item;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void markReturned(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Return date cannot be null.");
        }
        this.returnDate = date;
    }

    public boolean isOverdue(LocalDate referenceDate) {
        if (isReturned()) {
            return false;
        }
        return referenceDate.isAfter(dueDate);
    }

    public long getOverdueDays(LocalDate referenceDate) {
        if (!isOverdue(referenceDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, referenceDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan loan)) return false;
        return Objects.equals(loanId, loan.loanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId);
    }

    @Override
    public String toString() {
        String status;
        if (isReturned()) {
            status = "Returned (" + returnDate.format(DATE_FMT) + ")";
        } else if (LocalDate.now().isAfter(dueDate)) {
            long days = getOverdueDays(LocalDate.now());
            status = "OVERDUE by " + days + " day" + (days == 1 ? "" : "s") + " (Due: " + dueDate.format(DATE_FMT) + ")";
        } else {
            status = "Active (Due: " + dueDate.format(DATE_FMT) + ")";
        }

        return String.format("Loan [%s] Member: %-15s (%s) | Item: \"%-20s\" (%s) | Issued: %s | Status: %s",
                loanId, member.getName(), member.getId(), item.getTitle(), item.getId(),
                issueDate.format(DATE_FMT), status);
    }
}
