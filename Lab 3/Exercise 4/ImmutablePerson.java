
public final class ImmutablePerson {

    // EXPLAIN: Private fields prevent direct access from outside the class.
    // EXPLAIN: Final means these fields cannot be reassigned after initialization.
    private final String name;
    private final int age;

    // EXPLAIN: The constructor initializes the object's data when it is created.
    public ImmutablePerson(String name, int age) {

        // EXPLAIN: Reject a missing or blank name.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        // EXPLAIN: Reject a negative age.
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }

        this.name = name;
        this.age = age;
    }

    // EXPLAIN: Getters allow users to read the data without changing it.
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // EXPLAIN: This method provides a readable representation of the object.
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    // EXPLAIN: The main method demonstrates how the immutable object works.
    public static void main(String[] args) {

        ImmutablePerson person = new ImmutablePerson("Tendai", 22);

        System.out.println(person);
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        // EXPLAIN: There are no setter methods to change the person's data.
        System.out.println("The person's data cannot be changed using setters.");
    }
}
