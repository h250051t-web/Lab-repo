
class Rectangle {
    double width;
    double height;

    // EXPLAIN: constructor initialises the rectangle's dimensions
    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // EXPLAIN: area is width multiplied by height
    double area() {
        return width * height;
    }

    // EXPLAIN: perimeter is twice the sum of width and height
    double perimeter() {
        return 2 * (width + height);
    }
}

public class RectangleExercise {
    public static void main(String[] args) {

        // EXPLAIN: an array stores references to five Rectangle objects
        Rectangle[] rectangles = {
            new Rectangle(4, 5),
            new Rectangle(8, 3),
            new Rectangle(6, 6),
            new Rectangle(10, 4),
            new Rectangle(2, 7)
        };

        // EXPLAIN: start by assuming the first rectangle has the largest area
        Rectangle largest = rectangles[0];

        // EXPLAIN: enhanced for loop visits each rectangle in the array
        for (Rectangle rectangle : rectangles) {
            System.out.println(
                "Width: " + rectangle.width
                + ", Height: " + rectangle.height
                + ", Area: " + rectangle.area()
                + ", Perimeter: " + rectangle.perimeter()
            );

            // EXPLAIN: replace largest when a rectangle has a greater area
            if (rectangle.area() > largest.area()) {
                largest = rectangle;
            }
        }

        System.out.println("Largest rectangle:");
        System.out.println("Width: " + largest.width);
        System.out.println("Height: " + largest.height);
        System.out.println("Area: " + largest.area());
    }
}


class ReferenceRectangle {
    double width;
    double height;

    // EXPLAIN: constructor sets the rectangle's initial dimensions
    ReferenceRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

