
import java.util.Arrays;

public class BubbleSortDemo {

    // EXPLAIN: T must implement Comparable<T>.
    // This allows Java to compare elements using compareTo().
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {

        // EXPLAIN: Each pass moves the largest remaining
        // element towards the end of the array.
        for (int i = 0; i < arr.length - 1; i++) {

            // EXPLAIN: Compare adjacent elements that
            // have not yet reached their final positions.
            for (int j = 0; j < arr.length - 1 - i; j++) {

                // EXPLAIN: If elements are in the wrong
                // order, swap them.
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        // EXPLAIN: Integer is a wrapper class, so this
        // array can be passed to the generic method.
        Integer[] numbers = {8, 3, 6, 1, 9, 2};

        System.out.println("Before sorting integers: "
                + Arrays.toString(numbers));

        bubbleSort(numbers);

        System.out.println("After sorting integers: "
                + Arrays.toString(numbers));

        // EXPLAIN: String implements Comparable<String>,
        // so strings can also be sorted alphabetically.
        String[] names = {"Tariro", "Farai", "Anesu", "Rudo"};

        System.out.println("\nBefore sorting names: "
                + Arrays.toString(names));

        bubbleSort(names);

        System.out.println("After sorting names: "
                + Arrays.toString(names));
    }
}
