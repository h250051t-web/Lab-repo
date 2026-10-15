// EXPLAIN: SINGLETON PATTERN - Ensures a single shared configuration instance across the application.
public class LibraryConfig {
    private static final LibraryConfig INSTANCE = new LibraryConfig();

    private final String libraryName = "Apex University Library";
    private final int maxLoansPerMember = 3;
    private final double standardFinePerDay = 1.00; // $1.00 per overdue day
    private final double studentDiscountFinePerDay = 0.50; // $0.50 per overdue day

    // Private constructor prevents external instantiation
    private LibraryConfig() {}

    public static LibraryConfig getInstance() {
        return INSTANCE;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public int getMaxLoansPerMember() {
        return maxLoansPerMember;
    }

    public double getStandardFinePerDay() {
        return standardFinePerDay;
    }

    public double getStudentDiscountFinePerDay() {
        return studentDiscountFinePerDay;
    }
}
