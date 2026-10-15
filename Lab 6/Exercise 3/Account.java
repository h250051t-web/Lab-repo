
public abstract class Account {
    private double balance;

    // EXPLAIN: The constructor sets the initial account balance.
    public Account(double initialBalance) {
        balance = initialBalance;
    }

    // EXPLAIN: This concrete method adds money to the account.
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // EXPLAIN: Returns the current account balance.
    public double getBalance() {
        return balance;
    }

    // EXPLAIN: Each subclass must provide its own monthly fee.
    public abstract double monthlyFee();
}
