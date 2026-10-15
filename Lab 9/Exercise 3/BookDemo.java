
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// EXPLAIN: Book represents a book with a title and an ISBN.
class Book {
    private final String title;
    private final String isbn;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    // EXPLAIN: Get the title of the book.
    public String getTitle() {
        return title;
    }

    // EXPLAIN: Books are equal when their ISBN values are equal.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Book other)) {
            return false;
        }

        return Objects.equals(isbn, other.isbn);
    }

    // EXPLAIN: Equal books must have the same hash code.
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    // EXPLAIN: Display useful information about the book.
    @Override
    public String toString() {
        return title + " (ISBN: " + isbn + ")";
    }
}

public class BookDemo {

    public static void main(String[] args) {

        // EXPLAIN: These books have the same ISBN but different titles.
        Book book1 = new Book("Introduction to Java", "9781234567890");
        Book book2 = new Book("Java Programming Basics", "9781234567890");

        // EXPLAIN: A HashSet stores unique elements according to equals/hashCode.
        Set<Book> books = new HashSet<>();

        // EXPLAIN: Add the first book.
        System.out.println("First book added: " + books.add(book1));

        // EXPLAIN: The second book has the same ISBN, so it is a duplicate.
        System.out.println("Second book added: " + books.add(book2));

        // EXPLAIN: The set should contain only one book.
        System.out.println("Number of books in set: " + books.size());

        System.out.println("Books in the set: " + books);
    }
}
