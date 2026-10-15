import java.util.Objects;

// EXPLAIN: Abstract base class capturing common characteristics and state of all library items.
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Item ID cannot be null or empty.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.id = id.trim();
        this.title = title.trim();
    }

    // EXPLAIN: Polymorphic abstract method - every concrete item type defines its loan period in days.
    public abstract int loanPeriodDays();

    // EXPLAIN: Identifies concrete item type (e.g., BOOK, DVD) for storage, persistence and display.
    public abstract String getItemType();

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException {
        if (!available) {
            throw new ItemNotAvailableException("Item '" + title + "' (ID: " + id + ") is currently unavailable.");
        }
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null.");
        }
        if (!member.canBorrow()) {
            throw new MemberLimitExceededException("Member '" + member.getName() + "' (ID: " + member.getId()
                    + ") has reached the maximum loan limit of " + member.getMaxLoans() + " items.");
        }
        this.available = false;
    }

    @Override
    public void giveBack() {
        this.available = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryItem other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s] ID: %s | Title: \"%s\" | Available: %s | Loan Period: %d days",
                getItemType(), id, title, available ? "Yes" : "No", loanPeriodDays());
    }
}
