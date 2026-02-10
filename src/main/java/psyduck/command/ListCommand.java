package psyduck.command;

import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks.
     *
     * @param taskList The psyduck.task list to display.
     * @param ui The Ui instance for displaying tasks.
     * @param storage The Storage instance (not used).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.list());
    }

    /**
     * Executes the list command for GUI by displaying all tasks.
     *
     * @param taskList The task list to operate on.
     * @param storage The Storage instance for data persistence.
     * @return String message for successful execution.
     */
    @Override
    public String executeForGui(TaskList taskList, Storage storage) {
        if (taskList.size() == 0) {
            return "Your task list is empty! Add some tasks to get started.";
        } else {
            return "Here are the tasks in your list:\n" + taskList.list();
        }
    }
}
