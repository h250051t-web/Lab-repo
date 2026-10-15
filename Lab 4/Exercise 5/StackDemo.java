
public class StackDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create a new stack.
        Stack stack = new Stack();

        // EXPLAIN: Push three elements onto the stack.
        stack.push("Book");
        stack.push("Pen");
        stack.push("Laptop");

        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element: " + stack.peek());

        // EXPLAIN: Pop removes the most recently added element.
        System.out.println("Removed: " + stack.pop());
        System.out.println("New top: " + stack.peek());

        System.out.println("Stack size: " + stack.size());
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
