public class CardPayment implements Payment {

    private final String cardHolder;
    private final double amount;

    public CardPayment(String cardHolder, double amount) {
        this.cardHolder = cardHolder;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    // Polymorphic payment processing.
    @Override
    public void process() {
        System.out.printf(
            "Processing card payment for %s: %.2f%n",
            cardHolder, amount
        );
    }
}