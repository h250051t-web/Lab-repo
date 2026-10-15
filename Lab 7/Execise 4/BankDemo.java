
public class BankDemo {

    public static void main(String[] args) {

        BankAccount account =
            new BankAccount("ACC-001", "Tendai", 500.00);

        account.deposit(250.00);
        System.out.println(account);

        try {
            // EXPLAIN: This withdrawal succeeds.
            account.withdraw(100.00);
            System.out.println("Withdrawal of 100 successful.");

            // EXPLAIN: This withdrawal exceeds the balance.
            account.withdraw(5000.00);
            System.out.println("Withdrawal of 5000 successful.");

        } catch (InsufficientFundsException e) {
            // EXPLAIN: Display the exception message and shortfall.
            System.out.println(e.getMessage());
            System.out.println(
                "Short by: " + e.getShortfall()
            );

        } finally {
            // EXPLAIN: Show the balance after the transactions.
            System.out.println(account);
        }
    }
}
