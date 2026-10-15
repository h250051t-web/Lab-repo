
public class ArrayAccessDemo {

    // EXPLAIN: This method accesses an array element at a given index.
    public static void displayElement(int[] numbers, int index) {
        try {
            System.out.println("Element: " + numbers[index]);

        } catch (ArrayIndexOutOfBoundsException e) {
            // EXPLAIN: Handle an index outside the array's valid range.
            System.out.println("Error: Array index is out of bounds.");
        }
    }

    public static void main(String[] args) {

        // EXPLAIN: Create an array containing three integers.
        int[] numbers = {10, 20, 30};

        // EXPLAIN: Index 1 is valid.
        displayElement(numbers, 1);

        // EXPLAIN: Index 5 is invalid because the array has only three elements.
        displayElement(numbers, 5);
    }
}
