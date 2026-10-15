
class Square extends Shape {

    private double side;

    Square(double side) {
        this.side = side;
    }

    // EXPLAIN: Square area = side * side.
    @Override
    double area() {
        return side * side;
    }

    // EXPLAIN: A square has four equal sides.
    @Override
    double perimeter() {
        return 4 * side;
    }
}
