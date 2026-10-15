
class Student {
    // EXPLAIN: private fields prevent direct access from outside the class
    private String name;
    private int marks;

    public Student(String name, int marks) {
        this.name = name;

        // EXPLAIN: use the setter to validate the initial marks
        setMarks(marks);
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        // EXPLAIN: marks must be between 0 and 100
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException(
                "Marks must be between 0 and 100"
            );
        }

        this.marks = marks;
    }
}

public class StudentExercise {
    public static void main(String[] args) {
        // EXPLAIN: create a Student object with valid marks
        Student student = new Student("Tawanda", 85);

        System.out.println("Name: " + student.getName());
        System.out.println("Marks: " + student.getMarks());

        // EXPLAIN: test how the setter handles invalid marks
        try {
            student.setMarks(120);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // EXPLAIN: invalid marks do not replace the valid marks
        System.out.println("Final marks: " + student.getMarks());
    }
}
