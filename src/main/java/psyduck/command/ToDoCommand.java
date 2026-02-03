package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import storage.Storage;
import tasklist.TaskList;

/**
 * Command to add a new ToDo task.
 */
public class ToDoCommand extends Command {
    private final String description;

    /**
     * Creates a new ToDoCommand with the given description.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the description is empty.
     */
    public ToDoCommand(String input) throws PsyduckException {
        if (input.length() <= 4 || input.substring(4).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! The description of a todo cannot be empty.\n" +
                    "Usage: todo <description>");
        }
        this.description = input.substring(5).trim();
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
