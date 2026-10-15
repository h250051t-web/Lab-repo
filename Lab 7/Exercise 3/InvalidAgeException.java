
public class InvalidAgeException extends Exception {

    // EXPLAIN: This constructor receives an error message
    // and passes it to the parent Exception class.
    public InvalidAgeException(String message) {
        super(message);
    }
}
