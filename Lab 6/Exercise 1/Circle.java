
class Circle extends Shape {

    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    // EXPLAIN: Area of a circle = pi * radius * radius.
    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    // EXPLAIN: Circumference = 2 * pi * radius.
    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}
