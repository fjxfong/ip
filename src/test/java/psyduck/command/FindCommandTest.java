package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the FindCommand class.
 */
public class FindCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addDeadline("return book", "2024-12-15");
        taskList.addEvent("meeting", "2024-12-14", "2024-12-16");
        taskList.addToDo("borrow book"); // ToDo has no date, should not match
        ui = new Ui();
        storage = new Storage("./data/test_find_command.txt");
    }

    @Test
    public void testFindMatchesDeadline() throws PsyduckException {
        // Should not throw; deadline matches 2024-12-15
        Command command = new FindCommand("find 2024-12-15");
        command.execute(taskList, ui, storage);
    }

    @Test
    public void testFindMatchesEventWithinRange() throws PsyduckException {
        // Event spans 2024-12-14 to 2024-12-16, so 2024-12-15 is within range
        Command command = new FindCommand("find 2024-12-15");
        command.execute(taskList, ui, storage);
    }

    @Test
    public void testFindNoMatch() throws PsyduckException {
        // No tasks on 2025-01-01
        Command command = new FindCommand("find 2025-01-01");
        command.execute(taskList, ui, storage);
    }

    @Test
    public void testFindMissingDateThrowsException() {
        assertThrows(PsyduckException.class, () -> new FindCommand("find"));
    }

    @Test
    public void testFindInvalidDateThrowsException() {
        assertThrows(PsyduckException.class, () -> new FindCommand("find not-a-date"));
    }

    @Test
    public void testIsNotExitCommand() throws PsyduckException {
        Command command = new FindCommand("find 2024-12-15");
        assertFalse(command.isExit());
    }
}
