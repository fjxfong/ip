package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import storage.Storage;
import tasklist.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new MarkCommand by parsing the task index.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the input is invalid.
     */
    public MarkCommand(String input) throws PsyduckException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify which task to mark!\n" +
                    "Usage: mark <task number>");
        }
        this.taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
    }

    /**
     * Executes the command by marking the task and saving.
     *
     * @param taskList The task list to modify.
     * @param ui The Ui instance for displaying the result.
     * @param storage The Storage instance for saving.
     * @throws PsyduckException If the task index is invalid or saving fails.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PsyduckException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new PsyduckException("OOPS!!! Task number " + (taskIndex + 1) +
                    " doesn't exist! You have " + taskList.size() + " task(s).");
        }
        taskList.markTask(taskIndex);
        storage.save(taskList.getTasks());
        ui.showMarkedTask(taskList.get(taskIndex).toString());
    }
}