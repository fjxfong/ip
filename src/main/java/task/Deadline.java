package task;

/**
 * Represents a task that need to be done before a specific date/time.
 */
public class Deadline extends Task{

    private String by; // Deadline of the task

    /**
     * Creats a new deadline task with the given description and deadline.
     *
     * @param desc Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Deadline string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return Formatted task string including the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() +" (by: " + by + ")";
    }
}
