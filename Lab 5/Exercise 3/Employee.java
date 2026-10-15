
public class Employee {

    private final String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;

        // EXPLAIN: The superclass constructor initializes
        // the common employee details.
        System.out.println("Employee constructor: " + name);
    }

    public String getName() {
        return name;
    }

    public double calculatePay() {
        // EXPLAIN: By default, pay equals the base salary.
        return baseSalary;
    }

    public void describe() {
        System.out.printf("%s earns %.2f%n",
                name, calculatePay());
    }
}
