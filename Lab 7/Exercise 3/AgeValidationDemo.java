
import java.util.Scanner;

public class AgeValidationDemo {

    // EXPLAIN: This method checks whether an age is valid.
    // The throws keyword declares that it may throw our exception.
    public static void validateAge(int age)
            throws InvalidAgeException {

        // EXPLAIN: Ages below 0 or above 120 are invalid.
        if (age < 0 || age > 120) {
            throw new InvalidAgeException(
                "Age must be between 0 and 120."
            );
        }

        // EXPLAIN: This message appears when the age is valid.
        System.out.println("Age is valid: " + age);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // EXPLAIN: Read an age from the user.
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            // EXPLAIN: Call the method that validates the age.
            validateAge(age);

        } catch (InvalidAgeException e) {
            // EXPLAIN: Handle our custom checked exception.
            System.out.println("Invalid age: " + e.getMessage());

        } finally {
            // EXPLAIN: This block runs whether an exception occurs or not.
            scanner.close();
        }
    }
}
