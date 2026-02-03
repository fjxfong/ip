package psyduck.task;

import java.time.LocalDate;

/**
 * Represents a psyduck.task that need to be done before a specific date/time.
 */
public class Deadline extends Task{

    private LocalDate by; // Deadline of the psyduck.task

    /**
     * Creates a new deadline psyduck.task with the given description and deadline.
     *
     * @param desc Description of the psyduck.task.
     * @param by Deadline of the psyduck.task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = DateParser.parseDate(by);
    }

    /**
     * Creates a new deadline psyduck.task with a LocalDate object.
     *
     * @param description Description of the psyduck.task.
     * @param by Deadline as LocalDate.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the psyduck.task as a formatted string.
     *
     * @return Deadline string in MMM dd yyyy format.
     */
    public String getBy() {
        return DateParser.formatForDisplay(by);
    }

    /**
     * Returns the deadline of the psyduck.task as a LocalDate.
     *
     * @return Deadline as LocalDate.
     */
    public LocalDate getByDate() {
        return by;
    }

    /**
     * Returns the deadline in psyduck.storage format (yyyy-MM-dd).
     *
     * @return Deadline string for psyduck.storage.
     */
    public String getByForStorage() {
        return DateParser.formatForStorage(by);
    }

    /**
     * Returns a formatted string representation of the deadline psyduck.task.
     *
     * @return Formatted psyduck.task string including deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
