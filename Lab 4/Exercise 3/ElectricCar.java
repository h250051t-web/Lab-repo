
public class ElectricCar extends Car {

    private int batteryCapacity;

    public ElectricCar(String brand, String model,
                       int batteryCapacity) {

        // EXPLAIN: Calls the Car constructor first.
        super(brand, model);

        this.batteryCapacity = batteryCapacity;

        // EXPLAIN: Shows that the ElectricCar constructor runs.
        System.out.println(
            "ElectricCar constructor: "
            + batteryCapacity + " kWh"
        );
    }

    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println(
            "Battery capacity: " + batteryCapacity + " kWh"
        );
    }
}
