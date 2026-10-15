// EXPLAIN: OBSERVER PATTERN - Loose coupling for system-wide notifications and logging.
public interface LibraryObserver {
    void onItemBorrowed(Loan loan);
    void onItemReturned(Loan loan);
    void onOverdueAlert(Loan loan, long overdueDays);
}

// Concrete Observer: Console Auditor logger
class ConsoleAuditLogger implements LibraryObserver {
    @Override
    public void onItemBorrowed(Loan loan) {
        System.out.println("[AUDIT LOG] Item borrowed: '" + loan.getItem().getTitle()
                + "' by member '" + loan.getMember().getName() + "'. Due: " + loan.getDueDate());
    }

    @Override
    public void onItemReturned(Loan loan) {
        System.out.println("[AUDIT LOG] Item returned: '" + loan.getItem().getTitle()
                + "' by member '" + loan.getMember().getName() + "'.");
    }

    @Override
    public void onOverdueAlert(Loan loan, long overdueDays) {
        System.out.println("[AUDIT ALERT] Item OVERDUE: '" + loan.getItem().getTitle()
                + "' held by " + loan.getMember().getName() + " (" + overdueDays + " days overdue)!");
    }
}
