
 // EXPLAIN: A record is a concise way to create an immutable data carrier.
record Student(String name, int age) {
}

public class StudentRecordDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create two Student records with identical component values.
        Student student1 = new Student("Tariro", 21);
        Student student2 = new Student("Tariro", 21);

        // EXPLAIN: Printing a record uses its automatically generated toString().
        System.out.println("Student details: " + student1);

        // EXPLAIN: equals() compares the record components, not just object identity.
        System.out.println("Records are equal: " + student1.equals(student2));

        // EXPLAIN: name() is the automatically generated accessor for the name component.
        System.out.println("Student name: " + student1.name());

        // EXPLAIN: age() reads the age component.
        System.out.println("Student age: " + student1.age());
    }
}
