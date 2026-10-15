public class PaymentDemo {

    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor();

        // Create the payment objects.
        Payment card = new CardPayment("Tendai", 150.00);

        Payment mobile = new MobileMoneyPayment(
            "0771234567", 80.00
        );

        // Test the instanceof approach.
        System.out.println("=== Using instanceof ===");

        processor.pay(card);
        processor.pay(mobile);

        // Test the polymorphic approach.
        System.out.println();

        System.out.println("=== Using polymorphism ===");

        processor.payPolymorphically(card);
        processor.payPolymorphically(mobile);
    }
}