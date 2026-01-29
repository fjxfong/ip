package task;

/**
 * Represents a task without any date or time constraint.
 */
public class ToDo extends Task{
    /**
     * Creats a new to-do task with the given description.
     *
     * @param desc Description of the task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns a formatted string representation of the to-do task.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
