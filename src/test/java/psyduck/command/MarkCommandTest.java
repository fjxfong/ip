package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MarkCommand class.
 */
public class MarkCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addToDo("borrow book");
        taskList.addToDo("read book");
        ui = new Ui();
        storage = new Storage("./data/test_mark_command.txt");
    }

    @Test
    public void testExecuteMarksTask() throws PsyduckException {
        Command command = new MarkCommand("mark 1");
        command.execute(taskList, ui, storage);
        assertTrue(taskList.get(0).isDone());
    }

    @Test
    public void testMarkOutOfRangeThrowsException() throws PsyduckException {
        Command command = new MarkCommand("mark 99");
        assertThrows(PsyduckException.class, () -> command.execute(taskList, ui, storage));
    }

    @Test
    public void testMarkZeroThrowsException() throws PsyduckException {
        Command command = new MarkCommand("mark 0");
        assertThrows(PsyduckException.class, () -> command.execute(taskList, ui, storage));
    }

    @Test
    public void testMarkMissingNumberThrowsException() {
        assertThrows(PsyduckException.class, () -> new MarkCommand("mark"));
    }

    @Test
    public void testMarkInvalidNumberThrowsException() {
        assertThrows(NumberFormatException.class, () -> new MarkCommand("mark abc"));
    }
}
