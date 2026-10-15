
public class PaymentProcessor {

    // EXPLAIN: First design - identify the payment type
    // and process it using instanceof pattern matching.
    public void pay(Payment payment) {

        if (payment instanceof CardPayment cardPayment) {
            System.out.printf(
                "Processing card payment for %s: %.2f%n",
                cardPayment.getCardHolder(),
                cardPayment.getAmount()
            );

        } else if (payment instanceof MobileMoneyPayment mobilePayment) {
            System.out.printf(
                "Processing mobile money payment for %s: %.2f%n",
                mobilePayment.getPhoneNumber(),
                mobilePayment.getAmount()
            );

        } else {
            System.out.println("Unsupported payment type.");
        }
    }

    // EXPLAIN: Second design - delegate processing to
    // the actual payment object.
    public void payPolymorphically(Payment payment) {
        payment.process();
    }
}
