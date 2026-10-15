
import java.util.List;

public class StudentsAboveAverage {

    // EXPLAIN: A record stores each student's name and marks.
    record Student(String name, double marks) {}

    public static void main(String[] args) {

        // EXPLAIN: Create a list containing five Student records.
        List<Student> students = List.of(
            new Student("Tariro", 65),
            new Student("Farai", 80),
            new Student("Rudo", 90),
            new Student("Kuda", 58),
            new Student("Nyasha", 72)
        );

        // EXPLAIN: Calculate the average marks of the entire class.
        double average = students.stream()
            .mapToDouble(Student::marks)
            .average()
            .orElse(0);

        System.out.printf("Class average: %.2f%n", average);

        // EXPLAIN: Create a stream from the student list.
        // filter() keeps students whose marks exceed the average.
        // map() extracts their names.
        // sorted() arranges the names alphabetically.
        // forEach() prints each name.
        System.out.println("Students above the class average:");

        students.stream()
            .filter(student -> student.marks() > average)
            .map(Student::name)
            .sorted()
            .forEach(System.out::println);
    }
}
