
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDemo {
    public static void main(String[] args) {

        // EXPLAIN: Create a list to store Student objects.
        List<Student> students = new ArrayList<>();

        students.add(new Student("Tendai", 75));
        students.add(new Student("Amanda", 82));
        students.add(new Student("Brian", 68));
        students.add(new Student("Chipo", 90));

        // EXPLAIN: Sort using Comparable, which sorts by name.
        Collections.sort(students);

        System.out.println("Students sorted by name:");
        for (Student student : students) {
            System.out.println(student);
        }

        // EXPLAIN: Sort using Comparator, which sorts by marks.
        Collections.sort(students, new MarksComparator());

        System.out.println("\nStudents sorted by marks:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
