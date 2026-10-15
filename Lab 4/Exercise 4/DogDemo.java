
class Dog extends Animal {

    // EXPLAIN: This constructor does not specify
    // which Animal constructor should be called.
    public Dog() {
    }
}

public class DogDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(dog.getName());
    }
}
