
public class Student extends Person {

    // EXPLAIN: Student has an additional field that Person does not have.
    private String course;

    public Student(String name, int age, String course) {

        // EXPLAIN: Initialize the inherited fields using the Person constructor.
        super(name, age);

        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}
