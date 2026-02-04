package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.task.Task;
import psyduck.tasklist.TaskList;

/**
 * Command to search for tasks by keyword in description.
 */
public class SearchCommand extends Command {
    private final String keyword;

    /**
     * Creates a new SearchCommand with the given keyword.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the keyword is empty.
     */
    public SearchCommand(String input) throws PsyduckException {
        if (input.length() <= 6 || input.substring(6).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify a keyword to search for.\n"
                    + "Usage: search <keyword>");
        }
        this.keyword = input.substring(7).trim().toLowerCase();
    }

    /**
     * Executes the command by searching for tasks containing the keyword.
     *
     * @param taskList The task list to search.
     * @param ui The Ui instance for displaying results.
     * @param storage The Storage instance (not used).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                result.append(count++).append(".").append(task).append("\n");
            }
        }

        ui.showSearchResults(result.toString(), keyword);
    }
}