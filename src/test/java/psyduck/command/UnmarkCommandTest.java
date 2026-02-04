package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the UnmarkCommand class.
 */
public class UnmarkCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addToDo("borrow book");
        taskList.markTask(0);
        ui = new Ui();
        storage = new Storage("./data/test_unmark_command.txt");
    }

    @Test
    public void testExecuteUnmarksTask() throws PsyduckException {
        Command command = new UnmarkCommand("unmark 1");
        command.execute(taskList, ui, storage);
        assertFalse(taskList.get(0).isDone());
    }

    @Test
    public void testUnmarkOutOfRangeThrowsException() throws PsyduckException {
        Command command = new UnmarkCommand("unmark 99");
        assertThrows(PsyduckException.class, () -> command.execute(taskList, ui, storage));
    }

    @Test
    public void testUnmarkMissingNumberThrowsException() {
        assertThrows(PsyduckException.class, () -> new UnmarkCommand("unmark"));
    }

    @Test
    public void testUnmarkInvalidNumberThrowsException() {
        assertThrows(NumberFormatException.class, () -> new UnmarkCommand("unmark abc"));
    }
}