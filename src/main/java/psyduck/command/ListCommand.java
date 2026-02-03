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
}
