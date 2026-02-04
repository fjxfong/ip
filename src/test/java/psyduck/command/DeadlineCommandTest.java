package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the DeadlineCommand class.
 */
public class DeadlineCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/test_deadline_command.txt");
    }

    @Test
    public void testExecuteAddsDeadline() throws PsyduckException {
        Command command = new DeadlineCommand("deadline return book /by 2024-12-15");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("[D][ ] return book (by: Dec 15 2024)", taskList.get(0).toString());
    }

    @Test
    public void testEmptyDescriptionThrowsException() {
        assertThrows(PsyduckException.class, () -> new DeadlineCommand("deadline"));
    }

    @Test
    public void testMissingByThrowsException() {
        assertThrows(PsyduckException.class, () -> new DeadlineCommand("deadline return book"));
    }

    @Test
    public void testEmptyByThrowsException() {
        assertThrows(PsyduckException.class, () -> new DeadlineCommand("deadline return book /by"));
    }

    @Test
    public void testIsNotExitCommand() throws PsyduckException {
        Command command = new DeadlineCommand("deadline return book /by 2024-12-15");
        assertFalse(command.isExit());
    }
}
