package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

/**
 * Command to add a new Event psyduck.task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates a new EventCommand by parsing the input.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the input is invalid.
     */
    public EventCommand(String input) throws PsyduckException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of an event cannot be empty.\n" +
                    "Usage: event <description> /from <date> /to <date>");
        }

        String details = input.substring(6).trim();
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new PsyduckException("OOPS!!! Please specify the event time with /from and /to.\n" +
                    "Usage: event <description> /from <date> /to <date>");
        }

        if (fromIndex >= toIndex) {
            throw new PsyduckException("OOPS!!! /from must come before /to.\n" +
                    "Usage: event <description> /from <date> /to <date>");
        }

        this.description = details.substring(0, fromIndex).trim();
        if (description.isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of an event cannot be empty.\n" +
                    "Usage: event <description> /from <date> /to <date>");
        }

        this.from = details.substring(fromIndex + 5, toIndex).trim();
        if (from.isEmpty()) {
            throw new PsyduckException("OOPS!!! The start date cannot be empty.\n" +
                    "Usage: event <description> /from <date> /to <date>");
        }

        this.to = details.substring(toIndex + 3).trim();
        if (to.isEmpty()) {
            throw new PsyduckException("OOPS!!! The end date cannot be empty.\n" +
                    "Usage: event <description> /from <date> /to <date>");
        }
    }

    /**
     * Executes the command by adding an Event psyduck.task and saving.
     *
     * @param taskList The psyduck.task list to add the psyduck.task to.
     * @param ui The Ui instance for displaying the result.
     * @param storage The Storage instance for saving.
     * @throws PsyduckException If saving fails.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PsyduckException {
        taskList.addEvent(description, from, to);
        storage.save(taskList.getTasks());
        ui.showAddedTask(taskList.get(taskList.size() - 1).toString(), taskList.size());
    }
}
