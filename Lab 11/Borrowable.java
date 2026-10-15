// EXPLAIN: Interface specifying borrow and return behaviour for library items.
public interface Borrowable {
    void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException;
    void giveBack();
}
