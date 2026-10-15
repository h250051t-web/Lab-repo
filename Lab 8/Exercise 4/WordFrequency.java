
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordFrequency {

    public static void main(String[] args) {

        // EXPLAIN: Scanner reads the paragraph entered by the user.
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a paragraph:");
        String paragraph = scanner.nextLine();

        // EXPLAIN: Convert the paragraph to lowercase so that
        // Java and java are counted as the same word.
        paragraph = paragraph.toLowerCase();

        // EXPLAIN: Replace punctuation with spaces and split
        // the paragraph into individual words.
        String[] words = paragraph.replaceAll("[^a-z0-9\\s]", " ")
                                  .trim()
                                  .split("\\s+");

        // EXPLAIN: Map stores each word as a key and its
        // frequency as the value.
        Map<String, Integer> frequency = new HashMap<>();

        // EXPLAIN: Count how many times each word appears.
        if (!paragraph.isBlank()) {
            for (String word : words) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        // EXPLAIN: Copy the map entries into a list so
        // that they can be sorted.
        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        // EXPLAIN: Sort by frequency from highest to lowest.
        // If frequencies are equal, sort alphabetically.
        entries.sort(
            Comparator.<Map.Entry<String, Integer>>
                comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey)
        );

        // EXPLAIN: Print each word and its frequency.
        System.out.println("\nWord frequencies:");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        scanner.close();
    }
}
