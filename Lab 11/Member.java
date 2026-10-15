import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// EXPLAIN: Represents a registered library patron, strictly enforcing max 3 active loans.
public class Member {
    public static final int MAX_LOANS = 3;

    private final String id;
    private String name;
    private String email;
    private final List<Loan> activeLoans = new ArrayList<>();

    public Member(String id, String name, String email) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member name cannot be null or empty.");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Valid email address is required (must contain '@').");
        }
        this.id = id.trim();
        this.name = name.trim();
        this.email = email.trim();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Valid email address is required.");
        }
        this.email = email.trim();
    }

    public int getMaxLoans() {
        return MAX_LOANS;
    }

    public int getLoanCount() {
        return activeLoans.size();
    }

    public boolean canBorrow() {
        return activeLoans.size() < MAX_LOANS;
    }

    public void addLoan(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("Loan cannot be null.");
        }
        if (!activeLoans.contains(loan)) {
            activeLoans.add(loan);
        }
    }

    public void removeLoan(Loan loan) {
        activeLoans.remove(loan);
    }

    public List<Loan> getActiveLoans() {
        return Collections.unmodifiableList(activeLoans);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Member [ID: %s] Name: %-18s | Email: %-25s | Loans: %d/%d",
                id, name, email, activeLoans.size(), MAX_LOANS);
    }
}
