
public class SavingsAccountDemo {

    public static void main(String[] args) {

        // EXPLAIN: create a new savings account
        SavingsAccount account =
            new SavingsAccount("Rudo", 1000.00);

        // EXPLAIN: perform transactions
        account.deposit(300.00);
        account.withdraw(150.00);

        System.out.println(account);

        // EXPLAIN: display the transaction history
        System.out.println("\nTransaction History:");

        for (String transaction : account.getTransactions()) {
            System.out.println(transaction);
        }

        // EXPLAIN: attempt to modify the returned list
        try {
            account.getTransactions().clear();
        } catch (UnsupportedOperationException e) {
            System.out.println(
                "\nTransaction history cannot be modified directly."
            );
        }

        // EXPLAIN: confirm that the original history remains intact
        System.out.println(
            "Total recorded transactions: "
            + account.getTransactions().size()
        );
    }
}

class SavingsAccount {
    private final String accountHolder;
    private double balance;
    private final java.util.List<String> transactions =
        new java.util.ArrayList<>();

    SavingsAccount(String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        transactions.add(String.format("Account opened with balance: $%.2f", balance));
    }

    void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        transactions.add(String.format("Deposited: $%.2f", amount));
    }

    void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
        balance -= amount;
        transactions.add(String.format("Withdrew: $%.2f", amount));
    }

    java.util.List<String> getTransactions() {
        return java.util.Collections.unmodifiableList(transactions);
    }

    @Override
    public String toString() {
        return String.format("Savings account for %s: $%.2f", accountHolder, balance);
    }
}
