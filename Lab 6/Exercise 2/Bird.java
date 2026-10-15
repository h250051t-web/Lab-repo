
class Bird implements Flyable {

    // EXPLAIN: The Bird class provides its own
    // implementation of the fly() method.
    @Override
    public void fly() {
        System.out.println("Bird flies by flapping its wings.");
    }
}
