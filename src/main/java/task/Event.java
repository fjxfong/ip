package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that start at a specific date/time and ends at a specific date/time
 */
public class Event extends Task {
    private LocalDate from; // Start time of the event
    private LocalDate to; // End time of the event
    /** Formatter for parsing input dates (yyyy-MM-dd). */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /** Formatter for displaying dates (MMM dd yyyy). */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a new event task with the given description and time period.
     *
     * @param description Description of the task.
     * @param from Start date of the event in yyyy-MM-dd format.
     * @param to End date of the event in yyyy-MM-dd format.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.from = LocalDate.parse(from);
            } catch (DateTimeParseException ex) {
                this.from = null;
            }
        }
        try {
            this.to = LocalDate.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.to = LocalDate.parse(to);
            } catch (DateTimeParseException ex) {
                this.to = null;
            }
        }
    }

    /**
     * Creates a new event task with LocalDate objects.
     *
     * @param description Description of the task.
     * @param from Start date as LocalDate.
     * @param to End date as LocalDate.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the event as a formatted string.
     *
     * @return Start date string in MMM dd yyyy format.
     */
    public String getFrom() {
        return from != null ? from.format(OUTPUT_FORMATTER) : "Invalid date";
    }

    /**
     * Returns the end date of the event as a formatted string.
     *
     * @return End date string in MMM dd yyyy format.
     */
    public String getTo() {
        return to != null ? to.format(OUTPUT_FORMATTER) : "Invalid date";
    }

    /**
     * Returns the start date as LocalDate.
     *
     * @return Start date as LocalDate.
     */
    public LocalDate getFromDate() {
        return from;
    }

    /**
     * Returns the end date as LocalDate.
     *
     * @return End date as LocalDate.
     */
    public LocalDate getToDate() {
        return to;
    }

    /**
     * Returns the start date in storage format (yyyy-MM-dd).
     *
     * @return Start date string for storage.
     */
    public String getFromForStorage() {
        return from != null ? from.format(INPUT_FORMATTER) : "";
    }

    /**
     * Returns the end date in storage format (yyyy-MM-dd).
     *
     * @return End date string for storage.
     */
    public String getToForStorage() {
        return to != null ? to.format(INPUT_FORMATTER) : "";
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return Formatted task string including time period.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
