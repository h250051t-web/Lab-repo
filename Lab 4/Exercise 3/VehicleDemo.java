
public class VehicleDemo {

    public static void main(String[] args) {

        // EXPLAIN: Create an ElectricCar object.
        // Java executes the parent constructors first.
        ElectricCar car =
            new ElectricCar("Tesla", "Model 3", 75);

        System.out.println();

        // EXPLAIN: Display the inherited and specialised data.
        car.displayDetails();
    }
}

