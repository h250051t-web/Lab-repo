
 // EXPLAIN: An enum defines a fixed set of named constants.
enum Level {
    LOW,
    MEDIUM,
    HIGH
}

public class LevelDemo {

    public static void main(String[] args) {

        // EXPLAIN: Store one of the enum values in a variable.
        Level currentLevel = Level.MEDIUM;

        // EXPLAIN: The switch selects advice based on the current level.
        switch (currentLevel) {
            case LOW ->
                System.out.println("Advice: You can proceed normally.");

            case MEDIUM ->
                System.out.println("Advice: Be careful and stay alert.");

            case HIGH ->
                System.out.println("Advice: Take immediate action!");

        }
    }
}
