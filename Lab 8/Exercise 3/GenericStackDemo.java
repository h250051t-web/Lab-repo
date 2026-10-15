
import java.util.ArrayList;

// EXPLAIN: Stack is a generic class, so it can store
// different data types, such as String, Integer or Double.
class Stack<T> {

    // EXPLAIN: ArrayList stores the elements in the stack.
    private final ArrayList<T> items = new ArrayList<>();

    // EXPLAIN: push() adds an element to the top of the stack.
    public void push(T item) {
        items.add(item);
    }

    // EXPLAIN: pop() removes and returns the top element.
    // If the stack is empty, it reports an error.
    public T pop() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot pop from an empty stack.");
        }

        return items.remove(items.size() - 1);
    }

    // EXPLAIN: peek() returns the top element without removing it.
    public T peek() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot peek at an empty stack.");
        }

        return items.get(items.size() - 1);
    }

    // EXPLAIN: isEmpty() checks whether the stack has no elements.
    public boolean isEmpty() {
        return items.isEmpty();
    }
}

public class GenericStackDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create a stack that stores String values.
        Stack<String> names = new Stack<>();

        // EXPLAIN: Push three names onto the stack.
        names.push("Tariro");
        names.push("Rudo");
        names.push("Farai");

        System.out.println("Top element: " + names.peek());

        // EXPLAIN: pop() removes the most recently added element.
        System.out.println("Removed: " + names.pop());

        System.out.println("New top element: " + names.peek());

        // EXPLAIN: Display whether the stack is empty.
        System.out.println("Is the stack empty? " + names.isEmpty());

        // EXPLAIN: Remove the remaining elements.
        System.out.println("Removed: " + names.pop());
        System.out.println("Removed: " + names.pop());

        System.out.println("Is the stack empty? " + names.isEmpty());
    }
}
