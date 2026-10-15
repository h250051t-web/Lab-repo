
public interface SecondInterface {

    // EXPLAIN: This interface also defines a default hello() method.
    default void hello() {
        System.out.println("Hello from the second interface.");
    }
}
