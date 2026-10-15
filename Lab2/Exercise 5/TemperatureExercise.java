
class Temperature {

    private double celsius;

    // EXPLAIN: private constructor prevents direct object creation from outside
    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    // EXPLAIN: factory method creates a Temperature from Celsius
    public static Temperature fromCelsius(double celsius) {
        return new Temperature(celsius);
    }

    // EXPLAIN: convert Fahrenheit to Celsius before creating the object
    public static Temperature fromFahrenheit(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5.0 / 9.0;
        return new Temperature(celsius);
    }

    // EXPLAIN: return the stored temperature in Celsius
    public double getCelsius() {
        return celsius;
    }

    // EXPLAIN: convert the stored Celsius value to Fahrenheit
    public double getFahrenheit() {
        return (celsius * 9.0 / 5.0) + 32;
    }
}

public class TemperatureExercise {
    public static void main(String[] args) {

        // EXPLAIN: create temperature objects using the static factory methods
        Temperature t1 = Temperature.fromCelsius(25.0);
        Temperature t2 = Temperature.fromFahrenheit(98.6);

        System.out.printf("Temperature 1: %.1f Celsius%n", t1.getCelsius());
        System.out.printf("Temperature 1: %.1f Fahrenheit%n", t1.getFahrenheit());

        System.out.printf("Temperature 2: %.1f Celsius%n", t2.getCelsius());
        System.out.printf("Temperature 2: %.1f Fahrenheit%n", t2.getFahrenheit());
    }
}
