
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListExercise {

    public static void main(String[] args) {

        // EXPLAIN: Create an ArrayList to store integers.
        ArrayList<Integer> numbers = new ArrayList<>();

        // EXPLAIN: Add 10 integers to the list.
        numbers.add(12);
        numbers.add(5);
        numbers.add(28);
        numbers.add(7);
        numbers.add(19);
        numbers.add(3);
        numbers.add(45);
        numbers.add(16);
        numbers.add(9);
        numbers.add(22);

        // EXPLAIN: Display the original list.
        System.out.println("Original list: " + numbers);

        // EXPLAIN: Calculate the sum of all integers.
        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        System.out.println("Sum: " + sum);

        // EXPLAIN: Find the largest integer in the list.
        int maximum = Collections.max(numbers);

        System.out.println("Maximum: " + maximum);

        // EXPLAIN: Reverse the order of the list.
        Collections.reverse(numbers);

        System.out.println("Reversed list: " + numbers);
    }
}
