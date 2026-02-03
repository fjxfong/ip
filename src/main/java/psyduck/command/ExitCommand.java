package psyduck.command;

import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Creates a new ExitCommand.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Executes the exit command by displaying the goodbye message.
     *
     * @param taskList The psyduck.task list (not used).
     * @param ui The Ui instance for displaying the exit message.
     * @param storage The Storage instance (not used).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }
}
