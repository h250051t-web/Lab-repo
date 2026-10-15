
class Student {
    // EXPLAIN: private fields protect the student's data
    private String name;
    private int marks;

    // EXPLAIN: constructor sets the name and validates the marks
    public Student(String name, int marks) {
        this.name = name;
        setMarks(marks);
    }

    // EXPLAIN: getter returns the student's name
    public String getName() {
        return name;
    }

    // EXPLAIN: getter returns the student's marks
    public int getMarks() {
        return marks;
    }

    // EXPLAIN: setter prevents marks outside the range 0 to 100
    public void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException(
                "Marks must be between 0 and 100"
            );
        }
        this.marks = marks;
    }

    // EXPLAIN: calculates and returns the grade from the marks
    // The grade is NOT stored in a separate field
    public String getGrade() {
        if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 50) {
            return "C";
        } else {
            return "F";
        }
    }
}

public class StudentExercise {
    public static void main(String[] args) {
        // EXPLAIN: create a student with 85 marks
        Student student = new Student("Tawanda", 85);

        System.out.println("Name: " + student.getName());
        System.out.println("Marks: " + student.getMarks());

        // EXPLAIN: getGrade() calculates the grade when called
        System.out.println("Grade: " + student.getGrade());

        // EXPLAIN: test that invalid marks are rejected
        try {
            student.setMarks(120);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // EXPLAIN: the original valid marks remain unchanged
        System.out.println("Final marks: " + student.getMarks());
        System.out.println("Final grade: " + student.getGrade());
    }
}
