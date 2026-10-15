
public class InheritanceDemo {
    public static void main(String[] args) {

        // EXPLAIN: Create a Student object.
        Student student =
            new Student("Rudo", 21, "Computer Science");

        // EXPLAIN: Create a Teacher object.
        Teacher teacher =
            new Teacher("Mr. Moyo", 40, "Mathematics");

        System.out.println("STUDENT DETAILS:");
        student.display();
        System.out.println("Course: " + student.getCourse());

        System.out.println();

        System.out.println("TEACHER DETAILS:");
        teacher.display();
        System.out.println("Subject: " + teacher.getSubject());
    }
}
