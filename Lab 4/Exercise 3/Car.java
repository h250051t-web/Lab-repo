
public class Car extends Vehicle {

    protected String model;

    public Car(String brand, String model) {
        // EXPLAIN: Initialise the Vehicle part first.
        super(brand);

        this.model = model;

        // EXPLAIN: Shows that the Car constructor runs.
        System.out.println("Car constructor: " + model);
    }
}
