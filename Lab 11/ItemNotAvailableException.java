// EXPLAIN: Checked exception thrown when attempting to borrow an item that is already checked out.
public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}
