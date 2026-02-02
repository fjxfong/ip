package task;

/**
 * Represents a single task with a description and completion status.
 */
public class Task {
    protected String description; // Description of task
    protected boolean isDone; // Indicates whether the task has been completed

    /**
     * Creates a new task with the given description.
     *
     * @param description Description of the task.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return True if task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * returns the description of the task.
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
     * Returns a formatted string representation of the task.
     *
     * @return Task description with completion status.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
