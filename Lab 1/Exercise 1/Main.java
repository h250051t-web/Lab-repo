public class Main {
    public static void main(String[] args) {
        Car car1 = new Car();
        car1.brand = "Toyota";
        car1.year = 2018;
        car1.mileage = 75000;

        Car car2 = new Car();
        car2.brand = "Honda";
        car2.year = 2022;
        car2.mileage = 35000;

        Car car3 = new Car();
        car3.brand = "Nissan";
        car3.year = 1995;
        car3.mileage = 180000;

        car1.display();
        car2.display();
        car3.display();
    }
}
class Car {
    String brand;
    int year;
    double mileage;

    void display() {
        System.out.println("Brand: " + brand);
        System.out.println("Year: " + year);
        System.out.println("Mileage: " + mileage + " km");
        System.out.println("--------------------");
    }
}
