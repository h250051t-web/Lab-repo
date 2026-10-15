public interface Payment {

    double getAmount();

    // Each payment type must implement this method.
    void process();
}