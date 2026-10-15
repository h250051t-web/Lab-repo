
public class FlyableDemo {

    public static void main(String[] args) {

        // EXPLAIN: An interface reference can hold
        // objects of different implementing classes.
        Flyable[] flyingObjects = {
            new Bird(),
            new Aeroplane()
        };

        // EXPLAIN: The correct fly() implementation
        // runs for each actual object.
        for (Flyable object : flyingObjects) {
            object.fly();
        }
    }
}
