package psyduck.task;

import java.time.LocalDate;

/**
 * Represents a psyduck.task that start at a specific date/time and ends at a specific date/time
 */
public class Event extends Task {
    private LocalDate from; // Start time of the event
    private LocalDate to; // End time of the event

    /**
     * Creates a new event psyduck.task with the given description and time period.
     *
     * @param description Description of the psyduck.task.
     * @param from Start date of the event in yyyy-MM-dd format.
     * @param to End date of the event in yyyy-MM-dd format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateParser.parseDate(from);
        this.to = DateParser.parseDate(to);
    }

    /**
     * Creates a new event psyduck.task with LocalDate objects.
     *
     * @param description Description of the psyduck.task.
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
        return DateParser.formatForDisplay(from);
    }

    /**
     * Returns the end date of the event as a formatted string.
     *
     * @return End date string in MMM dd yyyy format.
     */
    public String getTo() {
        return DateParser.formatForDisplay(to);
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
     * Returns the start date in psyduck.storage format (yyyy-MM-dd).
     *
     * @return Start date string for psyduck.storage.
     */
    public String getFromForStorage() {
        return DateParser.formatForStorage(from);
    }

    /**
     * Returns the end date in psyduck.storage format (yyyy-MM-dd).
     *
     * @return End date string for psyduck.storage.
     */
    public String getToForStorage() {
        return DateParser.formatForStorage(to);
    }

    /**
     * Returns a formatted string representation of the event psyduck.task.
     *
     * @return Formatted psyduck.task string including time period.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
