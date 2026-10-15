
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class RemoveEvenNumbers {
    public static void main(String[] args) {

        // EXPLAIN: Create a list containing numbers, including even and odd values.
        List<Integer> numbers = new ArrayList<>(
            Arrays.asList(10, 15, 20, 25, 30, 35, 40, 45)
        );

        // EXPLAIN: Display the original list before removing any numbers.
        System.out.println("Original list: " + numbers);

        // EXPLAIN: A Predicate tests whether a number is even.
        // If the remainder after division by 2 is 0, the number is even.
        Predicate<Integer> isEven = number -> number % 2 == 0;

        // EXPLAIN: removeIf() removes every list element that matches
        // the condition defined by the Predicate.
        numbers.removeIf(isEven);

        // EXPLAIN: Display the list after all even numbers are removed.
        System.out.println("After removing even numbers: " + numbers);
    }
}
