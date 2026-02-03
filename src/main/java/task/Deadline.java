package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that need to be done before a specific date/time.
 */
public class Deadline extends Task{

    private LocalDate by; // Deadline of the task
    /** Formatter for parsing input dates (yyyy-MM-dd). */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /** Formatter for displaying dates (MMM dd yyyy). */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a new deadline task with the given description and deadline.
     *
     * @param desc Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.by = LocalDate.parse(by);
            } catch (DateTimeParseException ex) {
                // if still fails, store as null
                this.by = null;
            }
        }
    }

    /**
     * Creates a new deadline task with a LocalDate object.
     *
     * @param description Description of the task.
     * @param by Deadline as LocalDate.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task as a formatted string.
     *
     * @return Deadline string in MMM dd yyyy format.
     */
    public String getBy() {
        return by != null ? by.format(OUTPUT_FORMATTER) : "Invalid date";
    }

    /**
     * Returns the deadline of the task as a LocalDate.
     *
     * @return Deadline as LocalDate.
     */
    public LocalDate getByDate() {
        return by;
    }

    /**
     * Returns the deadline in storage format (yyyy-MM-dd).
     *
     * @return Deadline string for storage.
     */
    public String getByForStorage() {
        return by != null ? by.format(INPUT_FORMATTER) : "";
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return Formatted task string including deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
