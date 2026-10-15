
class Rectangle extends Shape {

    private double width;
    private double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // EXPLAIN: Rectangle area = width * height.
    @Override
    double area() {
        return width * height;
    }

    // EXPLAIN: Rectangle perimeter = 2 * (width + height).
    @Override
    double perimeter() {
        return 2 * (width + height);
    }
}
