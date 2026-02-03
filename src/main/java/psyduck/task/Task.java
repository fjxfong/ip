package psyduck.task;

/**
 * Represents a single psyduck.task with a description and completion status.
 */
public class Task {
    protected String description; // Description of psyduck.task
    protected boolean isDone; // Indicates whether the psyduck.task has been completed

    /**
     * Creates a new psyduck.task with the given description.
     *
     * @param description Description of the psyduck.task.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the psyduck.task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the psyduck.task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns whether the psyduck.task is done.
     *
     * @return True if psyduck.task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * returns the description of the psyduck.task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon representing the completion state.
     * "X" indicates completed, blank indicates not completed.
     *
     * @return Status icon of string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a formatted string representation of the psyduck.task.
     *
     * @return Task description with completion status.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
