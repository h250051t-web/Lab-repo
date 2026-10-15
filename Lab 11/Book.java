// EXPLAIN: Concrete subclass of LibraryItem representing a physical book with a 14-day loan window.
public class Book extends LibraryItem {
    private final String author;
    private final String isbn;

    public Book(String id, String title, String author, String isbn) {
        super(id, title);
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be null or blank.");
        }
        this.author = author.trim();
        this.isbn = isbn.trim();
    }

    @Override
    public int loanPeriodDays() {
        return 14; // Standard book loan period: 14 days
    }

    @Override
    public String getItemType() {
        return "BOOK";
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return String.format("%s | Author: %s | ISBN: %s",
                super.toString(), author, isbn);
    }
}
