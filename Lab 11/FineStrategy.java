// EXPLAIN: STRATEGY PATTERN - Allows swapping different overdue penalty algorithms at runtime.
public interface FineStrategy {
    double calculateFine(long overdueDays);
    String getStrategyName();
}

// Concrete Strategy: Standard rate calculation ($1.00/day)
class StandardFineStrategy implements FineStrategy {
    private final double ratePerDay;

    public StandardFineStrategy(double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public double calculateFine(long overdueDays) {
        if (overdueDays <= 0) return 0.0;
        return overdueDays * ratePerDay;
    }

    @Override
    public String getStrategyName() {
        return "Standard Rate ($" + String.format("%.2f", ratePerDay) + "/day)";
    }
}

// Concrete Strategy: Student discount rate (e.g. 50% discount)
class StudentDiscountFineStrategy implements FineStrategy {
    private final double ratePerDay;

    public StudentDiscountFineStrategy(double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public double calculateFine(long overdueDays) {
        if (overdueDays <= 0) return 0.0;
        return overdueDays * ratePerDay;
    }

    @Override
    public String getStrategyName() {
        return "Student Discount Rate ($" + String.format("%.2f", ratePerDay) + "/day)";
    }
}

// Concrete Strategy: VIP / Grace period waiver strategy
class VipWaiverFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(long overdueDays) {
        return 0.0; // Fines waived
    }

    @Override
    public String getStrategyName() {
        return "VIP Courtesy Waiver ($0.00)";
    }
}
