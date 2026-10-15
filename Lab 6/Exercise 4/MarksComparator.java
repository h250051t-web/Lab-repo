
import java.util.Comparator;

public class MarksComparator implements Comparator<Student> {

    // EXPLAIN: Compare two students using their marks.
    @Override
    public int compare(Student first, Student second) {
        return Double.compare(first.getMarks(), second.getMarks());
    }
}
