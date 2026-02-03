package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

/**
 * Command to add a new Deadline psyduck.task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Creates a new DeadlineCommand by parsing the input.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the input is invalid.
     */
    public DeadlineCommand(String input) throws PsyduckException {
        if (input.length() <= 8 || input.substring(8).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        String details = input.substring(9).trim();
        int byIndex = details.indexOf("/by");

        if (byIndex == -1) {
            throw new PsyduckException("OOPS!!! Please specify the deadline with /by.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        this.description = details.substring(0, byIndex).trim();
        if (description.isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a deadline cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        if (byIndex + 3 >= details.length()) {
            throw new PsyduckException("OOPS!!! The deadline date cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }

        this.by = details.substring(byIndex + 3).trim();
        if (by.isEmpty()) {
            throw new PsyduckException("OOPS!!! The deadline date cannot be empty.\n" +
                    "Usage: deadline <description> /by <date in yyyy-MM-dd format>");
        }
    }

    /**
     * Executes the command by adding a Deadline psyduck.task and saving.
     *
     * @param taskList The psyduck.task list to add the psyduck.task to.
     * @param ui The Ui instance for displaying the result.
     * @param storage The Storage instance for saving.
     * @throws PsyduckException If saving fails.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PsyduckException {
        taskList.addDeadline(description, by);
        storage.save(taskList.getTasks());
        ui.showAddedTask(taskList.get(taskList.size() - 1).toString(), taskList.size());
    }
}
