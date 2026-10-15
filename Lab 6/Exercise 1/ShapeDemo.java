
public class ShapeDemo {

    public static void main(String[] args) {

        // EXPLAIN: A Shape reference can hold different
        // subclass objects.
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(6, 4);
        Shape square = new Square(4);

        System.out.printf("Circle area = %.2f%n", circle.area());
        System.out.printf("Circle perimeter = %.2f%n",
                circle.perimeter());

        System.out.printf("Rectangle area = %.2f%n", rectangle.area());
        System.out.printf("Rectangle perimeter = %.2f%n",
                rectangle.perimeter());

        System.out.printf("Square area = %.2f%n", square.area());
        System.out.printf("Square perimeter = %.2f%n",
                square.perimeter());
    }
}
