
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack {

    // EXPLAIN: The stack contains an ArrayList.
    // It does not inherit ArrayList's public methods.
    private ArrayList<String> elements = new ArrayList<>();

    // EXPLAIN: Add an element to the top of the stack.
    public void push(String item) {
        elements.add(item);
    }

    // EXPLAIN: Remove and return the top element.
    public String pop() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.remove(elements.size() - 1);
    }

    // EXPLAIN: View the top element without removing it.
    public String peek() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return elements.get(elements.size() - 1);
    }

    // EXPLAIN: Check whether the stack contains no elements.
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // EXPLAIN: Return the number of elements.
    public int size() {
        return elements.size();
    }
}
