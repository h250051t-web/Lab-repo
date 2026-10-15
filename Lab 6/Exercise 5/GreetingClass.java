
public class GreetingClass
        implements FirstInterface, SecondInterface {

    // EXPLAIN: Override hello() to resolve the conflict.
    @Override
    public void hello() {
        System.out.println("Hello from GreetingClass.");

        // EXPLAIN: Call the default method from FirstInterface.
        FirstInterface.super.hello();

        // EXPLAIN: Call the default method from SecondInterface.
        SecondInterface.super.hello();
    }
}
