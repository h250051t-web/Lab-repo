
public class Temperature {

    // EXPLAIN: Store the temperature internally in Kelvin.
    private double kelvin;

    // EXPLAIN: The constructor accepts a temperature in Kelvin.
    public Temperature(double kelvin) {
        setKelvin(kelvin);
    }

    // EXPLAIN: Kelvin cannot be lower than absolute zero (0 K).
    public void setKelvin(double kelvin) {
        if (Double.isNaN(kelvin) || Double.isInfinite(kelvin)
                || kelvin < 0) {
            throw new IllegalArgumentException(
                    "Temperature cannot be below absolute zero.");
        }

        this.kelvin = kelvin;
    }

    // EXPLAIN: Return Celsius without changing the stored Kelvin value.
    public double getCelsius() {
        return kelvin - 273.15;
    }

    // EXPLAIN: Convert Kelvin to Fahrenheit for the caller.
    public double getFahrenheit() {
        return (kelvin - 273.15) * 9.0 / 5.0 + 32;
    }

    // EXPLAIN: Allow the caller to read the original Kelvin value.
    public double getKelvin() {
        return kelvin;
    }

    // EXPLAIN: Demonstrate the class and its conversion methods.
    public static void main(String[] args) {
        Temperature temperature = new Temperature(300.0);

        System.out.printf("Temperature in Kelvin: %.2f K%n",
                temperature.getKelvin());

        System.out.printf("Temperature in Celsius: %.2f C%n",
                temperature.getCelsius());

        System.out.printf("Temperature in Fahrenheit: %.2f F%n",
                temperature.getFahrenheit());

        // EXPLAIN: Negative Kelvin is rejected.
        try {
            temperature.setKelvin(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid temperature: " + e.getMessage());
        }

        // EXPLAIN: The original temperature remains unchanged after rejection.
        System.out.printf("Kelvin after invalid update: %.2f K%n",
                temperature.getKelvin());
    }
}
