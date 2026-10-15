
class CounterCircle {
    // EXPLAIN: static means this variable is shared by all circle objects
    private static int count = 0;

    double radius;

    // EXPLAIN: initialise the radius and increase the shared counter
    CounterCircle(double radius) {
        this.radius = radius;
        count++;
    }

    // EXPLAIN: use the other constructor to avoid incrementing count twice
    CounterCircle() {
        this(1.0);
    }

    // EXPLAIN: static getter returns the total number of objects created
    static int getCount() {
        return count;
    }
}

public class CircleCounterExercise {
    public static void main(String[] args) {

        CounterCircle c1 = new CounterCircle(5.0);
        CounterCircle c2 = new CounterCircle();
        CounterCircle c3 = new CounterCircle(2.5);

        System.out.println("Circle 1 radius: " + c1.radius);
        System.out.println("Circle 2 radius: " + c2.radius);
        System.out.println("Circle 3 radius: " + c3.radius);

        System.out.println("Total circles created: " + CounterCircle.getCount());
    }
}
