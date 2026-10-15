
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveAndReadFile {

    public static void main(String[] args) {

        // EXPLAIN: Defines the file where the user's text will be saved.
        Path path = Path.of("myNotes.txt");

        Scanner scanner = new Scanner(System.in);

        // EXPLAIN: Stores the five lines entered by the user.
        List<String> lines = new ArrayList<>();

        System.out.println("Enter five lines of text:");

        // EXPLAIN: Repeats five times to collect five lines.
        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            lines.add(scanner.nextLine());
        }

        // EXPLAIN: Writes all five lines into the text file.
        try {
            Files.write(path, lines);

            System.out.println("\nText saved successfully!");

            // EXPLAIN: Reads all lines back from the saved file.
            List<String> savedLines = Files.readAllLines(path);

            System.out.println("\nContents of the file:");

            // EXPLAIN: Displays each line that was read.
            for (String line : savedLines) {
                System.out.println(line);
            }

            System.out.println(
                "\nTotal lines read: " + savedLines.size()
            );

        } catch (IOException e) {
            // EXPLAIN: Displays an error if reading or writing fails.
            System.out.println("File error: " + e.getMessage());
        }

        scanner.close();
    }
}
