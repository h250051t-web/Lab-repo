
public class Student implements Comparable<Student> {
    private String name;
    private double marks;

    // EXPLAIN: The constructor initializes the student's details.
    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    // EXPLAIN: These methods return the student's details.
    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    // EXPLAIN: Define the natural ordering by student name.
    @Override
    public int compareTo(Student other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // EXPLAIN: Return a readable representation of a student.
    @Override
    public String toString() {
        return name + " - " + marks + " marks";
    }
}
