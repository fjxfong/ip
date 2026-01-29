package task;

/**
 * Represents a task that start at a specific date/time and ends at a specific date/time
 */
public class Event extends Task {
    private String from; // Start time of the event
    private String to; // End time of the event

    /**
     * Creates a new event task with the given description and time period.
     *
     * @param desc Description of the task
     * @param from Start time of the event
     * @param to End time of the event
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return Formatted task string including time period.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
