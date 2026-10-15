
public class InitBlocksExercise {

    // EXPLAIN: the first static block runs once when the class is initialised
    static {
        System.out.println("Static block 1");
    }

    // EXPLAIN: the second static block runs after the first static block
    static {
        System.out.println("Static block 2");
    }

    // EXPLAIN: the first instance block runs whenever an object is created
    {
        System.out.println("Instance block 1");
    }

    // EXPLAIN: the second instance block runs after the first instance block
    {
        System.out.println("Instance block 2");
    }

    // EXPLAIN: the constructor runs after both instance blocks
    InitBlocksExercise() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {

        System.out.println("Creating first object");
        InitBlocksExercise obj1 = new InitBlocksExercise();

        System.out.println("Creating second object");
        InitBlocksExercise obj2 = new InitBlocksExercise();
    }
}
