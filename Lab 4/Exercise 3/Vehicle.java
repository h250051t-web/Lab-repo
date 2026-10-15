
public class Vehicle {

    // EXPLAIN: Store the common property of every vehicle.
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;

        // EXPLAIN: Shows that the Vehicle constructor runs.
        System.out.println("Vehicle constructor: " + brand);
    }
}
