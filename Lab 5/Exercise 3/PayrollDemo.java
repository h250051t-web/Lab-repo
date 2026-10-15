
public class PayrollDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create different types of employees.
        Employee[] employees = {
            new Employee("Kudzai", 2000.00),
            new Manager("Nyasha", 3500.00, 800.00),
            new Developer("Tatenda", 3000.00, 10),
            new Intern("Rudo", 600.00)
        };

        double totalPayroll = 0.0;

        // EXPLAIN: The same payroll loop processes every
        // employee type without checking its specific class.
        for (Employee employee : employees) {
            double pay = employee.calculatePay();

            System.out.printf("%s earns %.2f%n",
                    employee.getName(), pay);

            totalPayroll += pay;
        }

        // EXPLAIN: Display the combined payroll amount.
        System.out.printf("Total payroll: %.2f%n", totalPayroll);
    }
}
