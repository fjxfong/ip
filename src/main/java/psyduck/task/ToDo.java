package psyduck.task;

/**
 * Represents a psyduck.task without any date or time constraint.
 */
public class ToDo extends Task{
    /**
     * Creats a new to-do psyduck.task with the given description.
     *
     * @param desc Description of the psyduck.task.
     */
    public ToDo(String desc) {
        super(desc);
    }

    /**
     * Returns a formatted string representation of the to-do psyduck.task.
     *
     * @return Formatted psyduck.task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
