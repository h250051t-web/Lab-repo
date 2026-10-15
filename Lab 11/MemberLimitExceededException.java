// EXPLAIN: Checked exception thrown when a member attempts to exceed their active loan limit (max 3).
public class MemberLimitExceededException extends Exception {
    public MemberLimitExceededException(String message) {
        super(message);
    }
}
