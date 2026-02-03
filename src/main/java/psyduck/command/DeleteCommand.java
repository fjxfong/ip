package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.task.Task;
import psyduck.tasklist.TaskList;

/**
 * Command to delete a psyduck.task from the list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a new DeleteCommand by parsing the psyduck.task index.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the input is invalid.
     */
    public DeleteCommand(String input) throws PsyduckException {
        if (input.length() <= 7 || input.substring(7).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify which psyduck.task to delete!\n" +
                    "Usage: delete <psyduck.task number>");
        }
        this.taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
    }

    /**
     * Executes the command by deleting the psyduck.task and saving.
     *
     * @param taskList The psyduck.task list to modify.
     * @param ui The Ui instance for displaying the result.
     * @param storage The Storage instance for saving.
     * @throws PsyduckException If the psyduck.task index is invalid or saving fails.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PsyduckException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new PsyduckException("OOPS!!! Task number " + (taskIndex + 1) +
                    " doesn't exist! You have " + taskList.size() + " psyduck.task(s).");
        }
        Task deletedTask = taskList.delete(taskIndex);
        storage.save(taskList.getTasks());
        ui.showDeletedTask(deletedTask.toString(), taskList.size());
    }
}
