
import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisionDemo {
    public static void main(String[] args) {

        // EXPLAIN: Scanner reads input entered by the user.
        Scanner scanner = new Scanner(System.in);

        try {
            // EXPLAIN: Read two integers from the user.
            System.out.print("Enter the first integer: ");
            int first = scanner.nextInt();

            System.out.print("Enter the second integer: ");
            int second = scanner.nextInt();

            // EXPLAIN: Prevent division by zero.
            if (second == 0) {
                System.out.println("Error: Cannot divide by zero.");
            } else {
                // EXPLAIN: Perform integer division.
                int result = first / second;
                System.out.println("Result: " + result);
            }

        } catch (InputMismatchException e) {
            // EXPLAIN: Handle input that is not an integer.
            System.out.println("Error: Please enter integers only.");

        } finally {
            // EXPLAIN: This block executes whether an exception occurs or not.
            scanner.close();
            System.out.println("Program finished.");
        }
    }
}
