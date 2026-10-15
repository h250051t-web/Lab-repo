
public class InheritanceDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create an ordinary Person object.
        Person person = new Person("Tendai", 25);

        System.out.println("Person Details:");
        person.display();

        System.out.println();

        // EXPLAIN: Create a Student object with an additional course.
        Student student = new Student(
                "Rudo", 21, "Computer Science");

        System.out.println("Student Details:");

        // EXPLAIN: Student inherits and uses Person's display() method.
        student.display();

        // EXPLAIN: Display the field specific to Student.
        System.out.println("Course: " + student.getCourse());
    }
}
