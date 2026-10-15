
public class Manager extends Employee {

    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        this.bonus = bonus;

        // EXPLAIN: The manager receives the common
        // employee details and an additional bonus.
        System.out.println("Manager constructor: " + name);
    }

    @Override
    public double calculatePay() {
        // EXPLAIN: A manager's pay includes salary and bonus.
        return super.calculatePay() + bonus;
    }
}
