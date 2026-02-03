package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import storage.Storage;
import tasklist.TaskList;

/**
 * Abstract base class for all commands.
 * Each command encapsulates a single user action.
 */
public abstract class Command {
    /** Whether this command signals the app to exit. */
    protected boolean isExit;

    /**
     * Creates a new Command.
     * By default, commands do not trigger an exit.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command.
     *
     * @param taskList The task list to operate on.
     * @param ui The Ui instance for user interaction.
     * @param storage The Storage instance for data persistence.
     * @throws PsyduckException If the command encounters an error.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws PsyduckException;

    /**
     * Returns whether this command signals the app to exit.
     *
     * @return True if this command is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }
}
