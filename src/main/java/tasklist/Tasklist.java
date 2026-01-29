package tasklist;

import java.util.ArrayList;
import task.Task;

/**
 * Manages a collection of tasks and provides operations to modify them
 */
public class Tasklist {
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
     * Returns a formatted list of all task.
     *
     * @return Tasks formatted as a numbered list.
     */
    public String list() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
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
