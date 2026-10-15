
class Child extends Parent {

    // EXPLAIN: This field hides the field in Parent.
    public String message = "Message from Child";

    // EXPLAIN: This method overrides Parent's display() method.
    @Override
    public void display() {
        System.out.println("Method from Child");
    }
}

public class FieldMethodDemo {

    public static void main(String[] args) {

        // EXPLAIN: A Parent reference points to a Child object.
        Parent reference = new Child();

        System.out.println("Field: " + reference.message);

        // EXPLAIN: Java executes the overridden method in Child.
        reference.display();
    }
}
