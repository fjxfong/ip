package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.task.Task;
import psyduck.tasklist.TaskList;

import java.util.ArrayList;

/**
 * Command to find tasks by tag.
 */
public class FindTagCommand extends Command {
    private final String tag;

    /**
     * Creates a new FindTagCommand.
     *
     * @param input The full user input string.
     * @throws PsyduckException If no tag is provided.
     */
    public FindTagCommand(String input) throws PsyduckException {
        // Format: "findtag #work" or "findtag work"
        String[] parts = input.split(" ");

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new PsyduckException(
                    "OOPS!!! Please specify a tag to search for.\n" +
                            "Usage: findtag <#tag>"
            );
        }

        // Extract tag (remove # if present)
        String rawTag = parts[1];
        this.tag = rawTag.startsWith("#") ? rawTag.substring(1) : rawTag;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = findTasksWithTag(taskList);

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks found with tag: #" + tag);
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append(i + 1).append(". ")
                        .append(matchingTasks.get(i)).append("\n");
            }

            ui.showMessage(
                    "Tasks with tag #" + tag + ":",
                    result.toString()
            );
        }
    }

    @Override
    public String executeForGui(TaskList taskList, Storage storage) {
        ArrayList<Task> matchingTasks = findTasksWithTag(taskList);

        if (matchingTasks.isEmpty()) {
            return "No tasks found with tag: #" + tag;
        } else {
            StringBuilder result = new StringBuilder();
            result.append("Tasks with tag #").append(tag).append(":\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append(i + 1).append(". ")
                        .append(matchingTasks.get(i)).append("\n");
            }

            return result.toString();
        }
    }

    private ArrayList<Task> findTasksWithTag(TaskList taskList) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.hasTag(tag)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
