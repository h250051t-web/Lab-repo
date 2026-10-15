
public class Student extends Person {
    private String course;

    // EXPLAIN: super(name, age) calls the Person constructor.
    public Student(String name, int age, String course) {
        super(name, age);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}
