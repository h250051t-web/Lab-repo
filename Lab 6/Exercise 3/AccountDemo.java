
public class AccountDemo {
    public static void main(String[] args) {

        // EXPLAIN: Create a savings account with an initial balance of 100.
        SavingsAccount savings = new SavingsAccount(100.00);

        // EXPLAIN: Create a current account with an initial balance of 200.
        CurrentAccount current = new CurrentAccount(200.00);

        // EXPLAIN: Deposit money into each account.
        savings.deposit(50.00);
        current.deposit(75.00);

        // EXPLAIN: Display the updated balances and monthly fees.
        System.out.printf("Savings balance: %.2f%n", savings.getBalance());
        System.out.printf("Savings monthly fee: %.2f%n", savings.monthlyFee());

        System.out.printf("Current balance: %.2f%n", current.getBalance());
        System.out.printf("Current monthly fee: %.2f%n", current.monthlyFee());
    }
}
