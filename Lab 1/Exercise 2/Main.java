class Car {

    // Stores the brand of the car, for example Toyota or Honda.
    String brand;

    // Stores the year the car was manufactured.
    int year;

    // Stores the distance the car has travelled in kilometres.
    double mileage;

    // This method checks whether the car is antique.
    // It returns true if the car is more than 25 calendar years old.
    boolean isAntique() {

        // Get the current year, subtract the car's manufacturing year,
        // and check whether the difference is greater than 25.
        return java.time.Year.now().getValue() - year > 25;
    }

    // This method displays the details of a car.
    void display() {

        // Print the brand of the car.
        System.out.println("Brand: " + brand);

        // Print the manufacturing year.
        System.out.println("Year: " + year);

        // Print the mileage followed by the unit km.
        System.out.println("Mileage: " + mileage + " km");

        // Call isAntique() and print its true or false result.
        System.out.println("Antique: " + isAntique());

        // Print a separator to make the output easier to read.
        System.out.println("--------------------");
    }
}

// Main is the class that contains the program's entry point.
public class Main {

    // Java begins executing the program from this main method.
    public static void main(String[] args) {

        // Create the first Car object using the Car class.
        Car car1 = new Car();

        // Assign values to the first car's fields.
        car1.brand = "Toyota";
        car1.year = 2018;
        car1.mileage = 75000;

        // Create the second Car object.
        Car car2 = new Car();

        // Assign values to the second car's fields.
        car2.brand = "Honda";
        car2.year = 2022;
        car2.mileage = 35000;

        // Create the third Car object.
        Car car3 = new Car();

        // Assign values to the third car's fields.
        car3.brand = "Nissan";
        car3.year = 1995;
        car3.mileage = 180000;

        // Call display() to show the Toyota's details and antique status.
        car1.display();

        // Call display() to show the Honda's details and antique status.
        car2.display();

        // Call display() to show the Nissan's details and antique status.
        car3.display();
    }
}
