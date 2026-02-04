package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the EventCommand class.
 */
public class EventCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/test_event_command.txt");
    }

    @Test
    public void testExecuteAddsEvent() throws PsyduckException {
        Command command = new EventCommand("event meeting /from 2024-12-20 /to 2024-12-21");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("[E][ ] meeting (from: Dec 20 2024 to: Dec 21 2024)", taskList.get(0).toString());
    }

    @Test
    public void testEmptyDescriptionThrowsException() {
        assertThrows(PsyduckException.class, () -> new EventCommand("event"));
    }

    @Test
    public void testMissingFromThrowsException() {
        assertThrows(PsyduckException.class, () -> new EventCommand("event meeting /to 2024-12-21"));
    }

    @Test
    public void testMissingToThrowsException() {
        assertThrows(PsyduckException.class, () -> new EventCommand("event meeting /from 2024-12-20"));
    }

    @Test
    public void testFromAfterToThrowsException() {
        assertThrows(PsyduckException.class, () ->
                new EventCommand("event meeting /to 2024-12-21 /from 2024-12-20"));
    }

    @Test
    public void testEmptyFromThrowsException() {
        assertThrows(PsyduckException.class, () ->
                new EventCommand("event meeting /from /to 2024-12-21"));
    }

    @Test
    public void testEmptyToThrowsException() {
        assertThrows(PsyduckException.class, () ->
                new EventCommand("event meeting /from 2024-12-20 /to"));
    }

    @Test
    public void testIsNotExitCommand() throws PsyduckException {
        Command command = new EventCommand("event meeting /from 2024-12-20 /to 2024-12-21");
        assertFalse(command.isExit());
    }
}