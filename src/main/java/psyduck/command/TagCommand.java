package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.task.Task;
import psyduck.tasklist.TaskList;

/**
 * Command to add or remove tags from tasks.
 */
public class TagCommand extends Command {
    private final int taskIndex;
    private final String tag;
    private final boolean isRemove;

    /**
     * Creates a new TagCommand.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the input format is invalid.
     */
    public TagCommand(String input) throws PsyduckException {
        // Format: "tag 1 #work" or "untag 1 #work"
        String[] parts = input.split(" ");

        if (parts.length < 3) {
            throw new PsyduckException(
                    "OOPS!!! Invalid format.\n" +
                            "Usage: tag <task number> <#tag>\n" +
                            "       untag <task number> <#tag>"
            );
        }

        this.isRemove = parts[0].equalsIgnoreCase("untag");

        try {
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new PsyduckException("OOPS!!! Please provide a valid task number!");
        }

        // Extract tag (remove # if present)
        String rawTag = parts[2];
        this.tag = rawTag.startsWith("#") ? rawTag.substring(1) : rawTag;

        if (tag.isEmpty()) {
            throw new PsyduckException("OOPS!!! Tag cannot be empty!");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PsyduckException {
        validateTaskIndex(taskList);

        Task task = taskList.get(taskIndex);

        if (isRemove) {
            task.removeTag(tag);
            storage.save(taskList.getTasks());
            ui.showMessage(
                    "Removed tag from task:",
                    "  " + task.toString()
            );
        } else {
            task.addTag(tag);
            storage.save(taskList.getTasks());
            ui.showMessage(
                    "Added tag to task:",
                    "  " + task.toString()
            );
        }
    }

    @Override
    public String executeForGui(TaskList taskList, Storage storage) throws PsyduckException {
        validateTaskIndex(taskList);

        Task task = taskList.get(taskIndex);

        if (isRemove) {
            task.removeTag(tag);
            storage.save(taskList.getTasks());
            return "Removed tag from task:\n  " + task.toString();
        } else {
            task.addTag(tag);
            storage.save(taskList.getTasks());
            return "Added tag to task:\n  " + task.toString();
        }
    }

    private void validateTaskIndex(TaskList taskList) throws PsyduckException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new PsyduckException(
                    "OOPS!!! Task number " + (taskIndex + 1) +
                            " doesn't exist! You have " + taskList.size() + " task(s)."
            );
        }
    }
}