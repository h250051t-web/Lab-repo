
class Employee {
    // EXPLAIN: shared counter used to generate unique employee IDs
    private static int nextId = 1;

    int employeeId;
    String name;
    double salary;
    String department;

    // EXPLAIN: first overloaded constructor accepts only a name
    Employee(String name) {
        this.employeeId = nextId++;
        this.name = name;
        this.salary = 0.0;
        this.department = "Not assigned";
    }

    // EXPLAIN: second overloaded constructor accepts name and salary
    Employee(String name, double salary) {
        this.employeeId = nextId++;
        this.name = name;
        this.salary = salary;
        this.department = "Not assigned";
    }

    // EXPLAIN: third overloaded constructor accepts all employee details
    Employee(String name, double salary, String department) {
        this.employeeId = nextId++;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // EXPLAIN: copy constructor copies details but generates a new ID
    Employee(Employee other) {
        this.employeeId = nextId++;
        this.name = other.name;
        this.salary = other.salary;
        this.department = other.department;
    }

    // EXPLAIN: display the details of an employee
    void display() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Department: " + department);
        System.out.println("--------------------");
    }
}

public class EmployeeExercise {
    public static void main(String[] args) {

        // EXPLAIN: create employees using different overloaded constructors
        Employee e1 = new Employee("Tawanda");
        Employee e2 = new Employee("Rudo", 850.00);
        Employee e3 = new Employee("Tinashe", 1200.00, "IT");

        // EXPLAIN: create a new employee by copying e3's details
        Employee e4 = new Employee(e3);

        e1.display();
        e2.display();
        e3.display();
        e4.display();
    }
}
