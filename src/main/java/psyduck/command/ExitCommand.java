package psyduck.command;

import javafx.application.Platform;
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

    /**
     * Execute exit command for GUI by closing the application.
     *
     * @param taskList The task list to operate on (not used).
     * @param storage The Storage instance (not used).
     * @return String message for successful exit.
     */
    @Override
    public String executeForGui(TaskList taskList, Storage storage) {
        // Close the JavaFX application after a short delay
        // so the goodbye message can be displayed first
        new Thread(() -> {
            try {
                Thread.sleep(1500); // Wait 1.5 seconds
                Platform.exit(); // Close the JavaFX application
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return "Bye. Hope to see you again soon!";
    }
}
