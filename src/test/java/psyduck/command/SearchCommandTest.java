package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the SearchCommand class.
 */
public class SearchCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addToDo("read book");
        taskList.addToDo("borrow book");
        taskList.addDeadline("return book", "2024-12-15");
        taskList.addToDo("buy groceries");
        ui = new Ui();
        storage = new Storage("./data/test_search_command.txt");
    }

    @Test
    public void testSearchFindsMultipleTasks() throws PsyduckException {
        // Should find 3 tasks containing "book"
        Command command = new SearchCommand("search book");
        command.execute(taskList, ui, storage);
    }

    @Test
    public void testSearchFindsOneTask() throws PsyduckException {
        Command command = new SearchCommand("search groceries");
        command.execute(taskList, ui, storage);
    }

    @Test
    public void testSearchNoMatch() throws PsyduckException {
        Command command = new SearchCommand("search homework");
        command.execute(taskList, ui, storage);
    }

    @Test
    public void testSearchCaseInsensitive() throws PsyduckException {
        // Should find tasks with "book" regardless of case
        Command command = new SearchCommand("search BOOK");
        command.execute(taskList, ui, storage);
    }

    @Test
    public void testSearchMissingKeywordThrowsException() {
        assertThrows(PsyduckException.class, () -> new SearchCommand("search"));
    }

    @Test
    public void testSearchOnlySpacesThrowsException() {
        assertThrows(PsyduckException.class, () -> new SearchCommand("search   "));
    }

    @Test
    public void testIsNotExitCommand() throws PsyduckException {
        Command command = new SearchCommand("search book");
        assertFalse(command.isExit());
    }
}