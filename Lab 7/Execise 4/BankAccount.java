
public class BankAccount {

    private final String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName,
                       double openingBalance) {

        // EXPLAIN: Reject a negative opening balance.
        if (openingBalance < 0) {
            throw new IllegalArgumentException(
                "Opening balance cannot be negative"
            );
        }

        this.accountNumber = accountNumber;
        setOwnerName(ownerName);
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setOwnerName(String ownerName) {
        // EXPLAIN: Ensure that the owner's name is not empty.
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException(
                "Owner name required"
            );
        }

        this.ownerName = ownerName.trim();
    }

    public void deposit(double amount) {
        // EXPLAIN: Only positive deposits are accepted.
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit must be positive"
            );
        }

        balance += amount;
    }

    // EXPLAIN: This method declares the checked exception.
    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Withdrawal must be positive"
            );
        }

        // EXPLAIN: Calculate the shortfall if funds are insufficient.
        if (amount > balance) {
            double shortfall = amount - balance;

            // EXPLAIN: Throw the custom exception.
            throw new InsufficientFundsException(
                "Insufficient funds. Available balance: "
                    + balance,
                shortfall
            );
        }

        // EXPLAIN: Update the balance only after validation.
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [%s] balance: %.2f",
            accountNumber, ownerName, balance
        );
    }
}
