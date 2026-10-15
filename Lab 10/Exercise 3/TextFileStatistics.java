
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFileStatistics {
    public static void main(String[] args) {

        // EXPLAIN: Specify the name of the text file to read.
        Path path = Path.of("sample.txt");

        try {
            // EXPLAIN: Read all lines from the file into a List.
            List<String> lines = Files.readAllLines(path);

            int lineCount = lines.size();
            int wordCount = 0;
            int characterCount = 0;

            // EXPLAIN: Process each line to count words and characters.
            for (String line : lines) {

                // EXPLAIN: Count characters, including spaces and punctuation.
                // Line-ending characters are not included.
                characterCount += line.length();

                // EXPLAIN: Ignore blank lines when counting words.
                if (!line.isBlank()) {
                    String[] words = line.trim().split("\\s+");
                    wordCount += words.length;
                }
            }

            // EXPLAIN: Display the calculated statistics.
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + characterCount);

        } catch (IOException e) {
            // EXPLAIN: Handle problems such as a missing or unreadable file.
            System.out.println("File error: " + e.getMessage());
        }
    }
}
