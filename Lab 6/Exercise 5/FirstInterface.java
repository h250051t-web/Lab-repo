
public interface FirstInterface {

    // EXPLAIN: A default method provides an implementation in an interface.
    default void hello() {
        System.out.println("Hello from the first interface.");
    }
}
