package psyduck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that parses dates from multiple common formats.
 */
public class DateParser {
    /** List of supported date input formats. */
    private static final List<DateTimeFormatter> SUPPORTED_FORMATS = new ArrayList<>() {{
        add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));    // 2024-12-15
        add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));    // 15/12/2024
        add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));    // 12/15/2024
        add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));    // 15-12-2024
        add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));    // 2024/12/15
        add(DateTimeFormatter.ofPattern("d/M/yyyy"));      // 5/3/2024
        add(DateTimeFormatter.ofPattern("MMM dd yyyy"));   // Dec 15 2024
        add(DateTimeFormatter.ofPattern("dd MMM yyyy"));   // 15 Dec 2024
    }};

    /** Formatter for displaying dates. */
    public static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** Formatter for psyduck.storage. */
    public static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Attempts to parse a date string using multiple supported formats.
     *
     * @param dateStr Date string to parse.
     * @return Parsed LocalDate, or null if no format matches.
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        dateStr = dateStr.trim();

        for (DateTimeFormatter formatter : SUPPORTED_FORMATS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Try next format
            }
        }

        return null; // No format matched
    }

    /**
     * Formats a LocalDate for display.
     *
     * @param date LocalDate to format.
     * @return Formatted date string, or "Invalid date" if null.
     */
    public static String formatForDisplay(LocalDate date) {
        return date != null ? date.format(OUTPUT_FORMATTER) : "Invalid date";
    }

    /**
     * Formats a LocalDate for psyduck.storage.
     *
     * @param date LocalDate to format.
     * @return Formatted date string for psyduck.storage, or empty string if null.
     */
    public static String formatForStorage(LocalDate date) {
        return date != null ? date.format(STORAGE_FORMATTER) : "";
    }
}