
class Circle {
    double radius;

    // EXPLAIN: constructor initialises the radius using the supplied value
    Circle(double radius) {
        this.radius = radius;
    }

    // EXPLAIN: no-argument constructor sets the default radius to 1.0
    Circle() {
        this(1.0);
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

public class CircleExercise {
    public static void main(String[] args) {

        Circle c1 = new Circle(5.0);
        Circle c2 = new Circle();

        System.out.println("Circle 1 radius: " + c1.radius);
        System.out.println("Circle 1 area: " + c1.area());

        System.out.println("Circle 2 radius: " + c2.radius);
        System.out.println("Circle 2 area: " + c2.area());
    }
}
