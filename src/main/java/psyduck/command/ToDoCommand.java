package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

/**
 * Command to add a new ToDo task.
 */
public class ToDoCommand extends Command {
    private static final String COMMAND_WORD = "todo";
    private static final int COMMAND_WORD_LENGTH = 4;

    private final String description;

    /**
     * Creates a new ToDoCommand with the given description.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the description is empty.
     */
    public ToDoCommand(String input) throws PsyduckException {
        if (input.length() <= COMMAND_WORD_LENGTH
                || input.substring(COMMAND_WORD_LENGTH).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a todo cannot be empty.\n"
                    + "Usage: todo <description>");
        }
        this.description = input.substring(COMMAND_WORD_LENGTH + 1).trim();
    }

    /**
     * Executes the command by adding a ToDo task and saving.
     *
     * @param taskList The task list to add the task to.
     * @param ui The Ui instance for displaying the result.
     * @param storage The Storage instance for saving.
     * @throws PsyduckException If saving fails.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PsyduckException {
        taskList.addToDo(description);
        storage.save(taskList.getTasks());
        ui.showAddedTask(taskList.get(taskList.size() - 1).toString(), taskList.size());
    }
}