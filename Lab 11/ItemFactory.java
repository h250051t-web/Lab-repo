// EXPLAIN: FACTORY PATTERN - Decouples item creation logic from caller code and file loading.
public class ItemFactory {

    // EXPLAIN: Factory method creating concrete LibraryItem instances based on type string.
    public static LibraryItem createItem(String type, String id, String title, String extra1, String extra2) {
        if (type == null) {
            throw new IllegalArgumentException("Item type cannot be null.");
        }

        return switch (type.trim().toUpperCase()) {
            case "BOOK" -> {
                // extra1 = author, extra2 = isbn
                yield new Book(id, title, extra1, extra2);
            }
            case "DVD" -> {
                // extra1 = director, extra2 = duration (minutes)
                int duration;
                try {
                    duration = Integer.parseInt(extra2.trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("DVD duration must be a valid number: " + extra2, e);
                }
                yield new Dvd(id, title, extra1, duration);
            }
            default -> throw new IllegalArgumentException("Unsupported item type: " + type + ". Supported types are BOOK and DVD.");
        };
    }
}
