
public class Developer extends Employee {

    private int overtimeHours;
    private static final double OVERTIME_RATE = 25.0;

    public Developer(String name, double baseSalary,
                     int overtimeHours) {
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculatePay() {
        // EXPLAIN: A developer receives the base salary
        // plus payment for overtime hours.
        return baseSalary + overtimeHours * OVERTIME_RATE;
    }
}
