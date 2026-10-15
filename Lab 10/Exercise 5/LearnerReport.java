
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LearnerReport {

    // EXPLAIN: A record stores the learner's name and marks.
    record Learner(String name, int marks) {}

    // EXPLAIN: Assign a grade band based on the learner's marks.
    static String gradeBand(Learner learner) {
        if (learner.marks() >= 80) {
            return "A";
        } else if (learner.marks() >= 60) {
            return "B";
        } else {
            return "C";
        }
    }

    public static void main(String[] args) {

        // EXPLAIN: Identify the input CSV file.
        Path inputFile = Path.of("learners.csv");

        try {
            // EXPLAIN: Read all lines from the CSV file.
            List<String> lines = Files.readAllLines(inputFile);

            // EXPLAIN: Skip the header and convert each row into a Learner.
            List<Learner> learners = lines.stream()
                .skip(1)
                .filter(line -> !line.isBlank())
                .map(line -> {
                    String[] data = line.split(",", -1);

                    if (data.length != 2 || data[0].isBlank()) {
                        throw new IllegalArgumentException(
                            "Invalid CSV row: " + line
                        );
                    }

                    String name = data[0].trim();
                    int marks = Integer.parseInt(data[1].trim());

                    if (marks < 0 || marks > 100) {
                        throw new IllegalArgumentException(
                            "Marks must be between 0 and 100: " + line
                        );
                    }

                    return new Learner(name, marks);
                })
                .toList();

            // EXPLAIN: Group learners into A, B, and C using groupingBy().
            Map<String, List<Learner>> groups = learners.stream()
                .collect(Collectors.groupingBy(LearnerReport::gradeBand));

            // EXPLAIN: Create a separate report for each grade band.
            for (String band : Arrays.asList("A", "B", "C")) {
                List<Learner> group = groups.getOrDefault(
                    band, List.of()
                );

                // EXPLAIN: Prepare the report heading and learner details.
                List<String> report = group.stream()
                    .map(learner ->
                        learner.name() + " - " + learner.marks()
                    )
                    .collect(Collectors.toList());

                report.add(0, "Grade " + band + " Learners");
                report.add("Total learners: " + group.size());

                // EXPLAIN: Write the report to its own text file.
                Path outputFile = Path.of("grade_" + band + "_report.txt");
                Files.write(outputFile, report);

                System.out.println(
                    "Created " + outputFile + " (" + group.size()
                    + " learners)"
                );
            }

        } catch (IOException e) {
            // EXPLAIN: Handle file-reading and file-writing errors.
            System.out.println("File error: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            // EXPLAIN: Report invalid CSV data or marks.
            System.out.println("Invalid learner data: " + e.getMessage());
        }
    }
}
