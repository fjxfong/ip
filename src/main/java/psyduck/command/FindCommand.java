package psyduck.command;

import psyduck.Ui;
import psyduck.PsyduckException;
import psyduck.storage.Storage;
import psyduck.task.Deadline;
import psyduck.task.Event;
import psyduck.task.Task;
import psyduck.task.DateParser;
import psyduck.tasklist.TaskList;

import java.time.LocalDate;

/**
 * Command to find tasks on a specific date.
 */
public class FindCommand extends Command {
    private final LocalDate searchDate;

    /**
     * Creates a new FindCommand by parsing the date.
     *
     * @param input The full user input string.
     * @throws PsyduckException If the date is invalid or missing.
     */
    public FindCommand(String input) throws PsyduckException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new PsyduckException("OOPS!!! Please specify a date to search for.\n" +
                    "Usage: find <date in yyyy-MM-dd format>");
        }

        String dateStr = input.substring(5).trim();
        this.searchDate = DateParser.parseDate(dateStr);

        if (searchDate == null) {
            throw new PsyduckException("OOPS!!! Invalid date format.\n" +
                    "Supported formats: yyyy-MM-dd, dd/MM/yyyy, MM/dd/yyyy, MMM dd yyyy\n" +
                    "Example: find 2024-12-25");
        }
    }

    /**
     * Executes the command by finding and displaying matching tasks.
     *
     * @param taskList The psyduck.task list to search.
     * @param ui The Ui instance for displaying results.
     * @param storage The Storage instance (not used).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getByDate() != null && deadline.getByDate().equals(searchDate)) {
                    result.append(count++).append(".").append(task).append("\n");
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFromDate() != null && event.getToDate() != null
                        && !event.getFromDate().isAfter(searchDate)
                        && !event.getToDate().isBefore(searchDate)) {
                    result.append(count++).append(".").append(task).append("\n");
                }
            }
        }

        ui.showFindResults(result.toString(), DateParser.formatForDisplay(searchDate));
    }
}
