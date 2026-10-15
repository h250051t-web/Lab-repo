
class ReferenceRectangle {
    double width;
    double height;

    // EXPLAIN: constructor sets the rectangle's initial dimensions
    ReferenceRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

public class ReferenceExercise {
    public static void main(String[] args) {

        // EXPLAIN: r1 refers to a new Rectangle object
        ReferenceRectangle r1 = new ReferenceRectangle(5, 10);

        // EXPLAIN: r2 receives the reference held by r1, not a separate object
        ReferenceRectangle r2 = r1;

        // EXPLAIN: changing the width through r2 affects the same object
        r2.width = 20;

        System.out.println("r1 width: " + r1.width);
        System.out.println("r2 width: " + r2.width);
    }
}
