package psyduck;

import psyduck.command.Command;
import storage.Storage;
import tasklist.TaskList;

/**
 * Runs the Psyduck task management chatbot application.
 * Main entry point of the application.
 */
public class Psyduck {
    private static final String FILE_PATH = "./data/psyduck.txt";
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Creates a new Psyduck instance, loading any saved tasks.
     *
     * @param filePath Path to the data file.
     */
    public Psyduck(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (PsyduckException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main application loop.
     * Reads user input, parses it into commands, and executes them.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (PsyduckException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("OOPS!!! Please provide a valid task number!");
            } catch (Exception e) {
                ui.showError("OOPS!!! Something went wrong: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Psyduck(FILE_PATH).run();
    }
}