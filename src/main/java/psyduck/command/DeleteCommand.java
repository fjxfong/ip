package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;

/**
 * Command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new DeleteCommand by parsing the task index.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the input is invalid.
     */
    public DeleteCommand(String input) throws PsyduckException {
        if (input.length() <= 7 || input.substring(7).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify which task to delete!\n" +
                    "Usage: delete <task number>");
        }
        this.taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
    }

    /**
     * Executes the command by deleting the task and saving.
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
        Task deletedTask = taskList.delete(taskIndex);
        storage.save(taskList.getTasks());
        ui.showDeletedTask(deletedTask.toString(), taskList.size());
    }
}
