
public class Person {
    protected String name;
    protected int age;

    // EXPLAIN: Initialise the common properties of a person.
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // EXPLAIN: This method is inherited by Student and Teacher.
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}
