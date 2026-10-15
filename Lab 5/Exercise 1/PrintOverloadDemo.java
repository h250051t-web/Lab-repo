
public class PrintOverloadDemo {

    // EXPLAIN: Prints an integer.
    static void print(int value) {
        System.out.println("Integer: " + value);
    }

    // EXPLAIN: Prints a decimal number.
    static void print(double value) {
        System.out.println("Double: " + value);
    }

    // EXPLAIN: Prints a text value.
    static void print(String value) {
        System.out.println("String: " + value);
    }

    // EXPLAIN: Prints every element in an integer array.
    static void print(int[] values) {
        System.out.print("Integer array: ");

        for (int value : values) {
            System.out.print(value + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // EXPLAIN: Each call selects a method based on its argument type.
        print(25);
        print(12.5);
        print("Java Polymorphism");

        int[] numbers = {10, 20, 30, 40};
        print(numbers);
    }
}
