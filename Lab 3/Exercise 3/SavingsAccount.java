
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SavingsAccount {

    // EXPLAIN: private fields protect the account information
    private String owner;
    private double balance;

    // EXPLAIN: stores a record of account transactions
    private final List<String> transactions = new ArrayList<>();

    // EXPLAIN: constructor initializes a new savings account
    public SavingsAccount(String owner, double openingBalance) {
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name is required");
        }

        if (openingBalance < 0) {
            throw new IllegalArgumentException(
                "Opening balance cannot be negative"
            );
        }

        this.owner = owner;
        this.balance = openingBalance;

        transactions.add(
            String.format("Account opened with: %.2f", openingBalance)
        );
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    // EXPLAIN: deposits must be positive
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit must be positive"
            );
        }

        balance += amount;
        transactions.add(
            String.format("Deposit: +%.2f", amount)
        );
    }

    // EXPLAIN: withdraw only when the balance is sufficient
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        transactions.add(
            String.format("Withdrawal: -%.2f", amount)
        );

        return true;
    }

    // EXPLAIN: return an unmodifiable copy of the transaction history
    public List<String> getTransactions() {
        return Collections.unmodifiableList(
            new ArrayList<>(transactions)
        );
    }

    @Override
    public String toString() {
        return String.format(
            "Savings account for %s | Balance: %.2f",
            owner, balance
        );
    }
}
