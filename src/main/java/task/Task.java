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
     * Returns the status icon representing the completion state.
     * "X" indicates completed, blank indicates not completed.
     *
     * @return Status icon of string
     */
    public String statusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return Task description with completion status.
     */
    @Override
    public String toString() {
        return "[" + statusIcon() + "] " + description;
    }
}
