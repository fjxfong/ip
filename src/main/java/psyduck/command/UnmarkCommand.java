package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import storage.Storage;
import tasklist.TaskList;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new UnmarkCommand by parsing the task index.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the input is invalid.
     */
    public UnmarkCommand(String input) throws PsyduckException {
        if (input.length() <= 7 || input.substring(7).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify which task to unmark!\n" +
                    "Usage: unmark <task number>");
        }
        this.taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
    }

    /**
     * Executes the command by unmarking the task and saving.
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
        taskList.unmarkTask(taskIndex);
        storage.save(taskList.getTasks());
        ui.showUnmarkedTask(taskList.get(taskIndex).toString());
    }
}
