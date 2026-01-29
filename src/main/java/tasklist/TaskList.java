package tasklist;

import java.util.ArrayList;
import task.*;

/**
 * Manages a collection of tasks and provides operations to modify them
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a new task to the list.
     *
     * @param description Description of the task.
     */
    public void add(String description) {
        tasks.add(new Task(description));
    }

    /**
     * Adds a new to-do task to the list.
     *
     * @param description Description of the task.
     */
    public void addToDo(String description) {
        tasks.add(new ToDo(description));
    }

    /**
     * Adds a new deadline task to the list.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    /**
     * Adds a new event task to the list.
     *
     * @param description Description of the task.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    /**
     * Marks the specified task as completed.
     *
     * @param index Zero-based index of the task.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Marks the specified task as not completed.
     *
     * @param index Zero-based index of the task.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Zero-based index of the task to delete.
     * @return The deleted task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a formatted list of all task.
     *
     * @return Tasks formatted as a numbered list.
     */
    public String list() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< tasks.size(); i++) {
            sb.append(i + 1).append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the number of task in the list.
     *
     * @return Number of task
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index
     *
     * @param index Zero-based index.
     * @return Task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
