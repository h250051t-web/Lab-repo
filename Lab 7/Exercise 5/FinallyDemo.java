
public class FinallyDemo {

    public static int testMethod() {

        try {
            // EXPLAIN: This return prepares the value 10.
            System.out.println("Inside try block");
            return 10;

        } finally {
            // EXPLAIN: Finally executes before the method returns.
            System.out.println("Inside finally block");
        }
    }

    public static void main(String[] args) {

        // EXPLAIN: Call the method and display its returned value.
        int result = testMethod();

        System.out.println("Returned value: " + result);
    }
}
