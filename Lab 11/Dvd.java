// EXPLAIN: Concrete subclass of LibraryItem representing a DVD item with a 7-day loan window.
public class Dvd extends LibraryItem {
    private final String director;
    private final int durationMinutes;

    public Dvd(String id, String title, String director, int durationMinutes) {
        super(id, title);
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Director cannot be null or blank.");
        }
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be a positive integer.");
        }
        this.director = director.trim();
        this.durationMinutes = durationMinutes;
    }

    @Override
    public int loanPeriodDays() {
        return 7; // DVD loan period: 7 days
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    public String getDirector() {
        return director;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return String.format("%s | Director: %s | Duration: %d mins",
                super.toString(), director, durationMinutes);
    }
}
