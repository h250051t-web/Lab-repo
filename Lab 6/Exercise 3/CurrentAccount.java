
public class CurrentAccount extends Account {

    // EXPLAIN: The constructor passes the initial balance to Account.
    public CurrentAccount(double initialBalance) {
        super(initialBalance);
    }

    // EXPLAIN: This subclass provides its monthly fee.
    // The fee of 5.00 is an example value for this exercise.
    @Override
    public double monthlyFee() {
        return 5.00;
    }
}
