package psyduck.tasklist;

import java.util.ArrayList;
import psyduck.task.Task;
import psyduck.task.Event;
import psyduck.task.ToDo;
import psyduck.task.Deadline;

/**
 * Manages a collection of tasks and provides operations to modify them
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a new empty Tasklist
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a Tasklist with existing tasks.
     *
     * @param tasks ArrayList of psyduck.task to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the internal Arraylist of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new psyduck.task to the list.
     *
     * @param description Description of the psyduck.task.
     */
    public void add(String description) {
        tasks.add(new Task(description));
    }

    /**
     * Adds a new to-do psyduck.task to the list.
     *
     * @param description Description of the psyduck.task.
     */
    public void addToDo(String description) {
        tasks.add(new ToDo(description));
    }

    /**
     * Adds a new deadline psyduck.task to the list.
     *
     * @param description Description of the psyduck.task.
     * @param by Deadline of the psyduck.task.
     */
    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    /**
     * Adds a new event psyduck.task to the list.
     *
     * @param description Description of the psyduck.task.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    /**
     * Marks the specified psyduck.task as completed.
     *
     * @param index Zero-based index of the psyduck.task.
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Marks the specified psyduck.task as not completed.
     *
     * @param index Zero-based index of the psyduck.task.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Deletes the psyduck.task at the specified index.
     *
     * @param index Zero-based index of the psyduck.task to delete.
     * @return The deleted psyduck.task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a formatted list of all psyduck.task.
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
     * Returns the number of psyduck.task in the list.
     *
     * @return Number of psyduck.task
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the psyduck.task at the specified index
     *
     * @param index Zero-based index.
     * @return Task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
