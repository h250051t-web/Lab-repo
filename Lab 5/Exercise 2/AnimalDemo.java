
public class AnimalDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create different animal objects and store them
        // in an array whose declared type is Animal.
        Animal[] animals = {
            new Cat(),
            new Cow(),
            new Duck()
        };

        // EXPLAIN: The loop visits every animal in the array.
        for (Animal animal : animals) {

            // EXPLAIN: Java calls the sound() implementation
            // belonging to the actual object at runtime.
            animal.sound();
        }
    }
}
