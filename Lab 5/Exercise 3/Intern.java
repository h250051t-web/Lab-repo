
public class Intern extends Employee {

    public Intern(String name, double stipend) {
        super(name, stipend);
    }

    @Override
    public double calculatePay() {
        // EXPLAIN: An intern receives a fixed stipend.
        return baseSalary;
    }
}
