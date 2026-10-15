
import java.util.Iterator;
import java.util.NoSuchElementException;

// EXPLAIN: Implementing Iterable allows the list to work in a for-each loop.
class CustomLinkedList<T> implements Iterable<T> {

    // EXPLAIN: A static nested Node class stores a value and the next node.
    // It does not need an instance of CustomLinkedList to be created.
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    // EXPLAIN: head points to the first node; tail points to the last node.
    private Node<T> head;
    private Node<T> tail;

    // EXPLAIN: Add a new element to the end of the linked list.
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            // EXPLAIN: For an empty list, the first node is also the last.
            head = newNode;
            tail = newNode;
        } else {
            // EXPLAIN: Link the current last node to the new node.
            tail.next = newNode;
            tail = newNode;
        }
    }

    // EXPLAIN: Return an iterator that can traverse this list.
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    // EXPLAIN: This inner class can access the list's private head field.
    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;

        // EXPLAIN: Check whether another element is available.
        @Override
        public boolean hasNext() {
            return current != null;
        }

        // EXPLAIN: Return the current element and move to the next node.
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T value = current.data;
            current = current.next;
            return value;
        }
    }
}

public class CustomLinkedListDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create a linked list that stores String values.
        CustomLinkedList<String> names = new CustomLinkedList<>();

        // EXPLAIN: Add three elements to the list.
        names.add("Tariro");
        names.add("Farai");
        names.add("Rudo");

        System.out.println("Names in the linked list:");

        // EXPLAIN: The for-each loop automatically uses the iterator.
        for (String name : names) {
            System.out.println(name);
        }
    }
}
