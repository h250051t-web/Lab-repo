
public class MobileMoneyPayment implements Payment {

    private final String phoneNumber;
    private final double amount;

    public MobileMoneyPayment(String phoneNumber, double amount) {
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Implement the process() method required by Payment.
    @Override
    public void process() {
        System.out.printf(
            "Processing mobile money payment for %s: %.2f%n",
            phoneNumber,
            amount
        );
    }
}
