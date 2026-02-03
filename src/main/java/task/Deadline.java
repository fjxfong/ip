package task;

import java.time.LocalDate;

/**
 * Represents a task that need to be done before a specific date/time.
 */
public class Deadline extends Task{

    private LocalDate by; // Deadline of the task

    /**
     * Creates a new deadline task with the given description and deadline.
     *
     * @param desc Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = DateParser.parseDate(by);
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
        return DateParser.formatForDisplay(by);
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
        return DateParser.formatForStorage(by);
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
