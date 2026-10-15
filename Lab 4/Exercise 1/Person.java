
public class Person {

    // EXPLAIN: These fields store the person's basic information.
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // EXPLAIN: This method displays information shared by all persons.
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}
