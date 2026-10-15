
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicateNames {

    public static void main(String[] args) {

        // EXPLAIN: Create a list containing duplicate names.
        List<String> names = new ArrayList<>();

        names.add("Tariro");
        names.add("Rudo");
        names.add("Farai");
        names.add("Tariro");
        names.add("Nyasha");
        names.add("Rudo");
        names.add("Farai");

        // EXPLAIN: Display the original list with duplicates.
        System.out.println("Original names: " + names);

        // EXPLAIN: LinkedHashSet removes duplicates
        // while preserving the order of first appearance.
        Set<String> uniqueNames = new LinkedHashSet<>(names);

        // EXPLAIN: Display the names without duplicates.
        System.out.println("Names without duplicates: " + uniqueNames);
    }
}
