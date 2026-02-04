package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the DeleteCommand class.
 */
public class DeleteCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addToDo("borrow book");
        taskList.addToDo("read book");
        ui = new Ui();
        storage = new Storage("./data/test_delete_command.txt");
    }

    @Test
    public void testExecuteDeletesTask() throws PsyduckException {
        Command command = new DeleteCommand("delete 1");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] read book", taskList.get(0).toString());
    }

    @Test
    public void testDeleteOutOfRangeThrowsException() throws PsyduckException {
        Command command = new DeleteCommand("delete 99");
        assertThrows(PsyduckException.class, () -> command.execute(taskList, ui, storage));
    }

    @Test
    public void testDeleteZeroThrowsException() throws PsyduckException {
        Command command = new DeleteCommand("delete 0");
        assertThrows(PsyduckException.class, () -> command.execute(taskList, ui, storage));
    }

    @Test
    public void testDeleteMissingNumberThrowsException() {
        assertThrows(PsyduckException.class, () -> new DeleteCommand("delete"));
    }

    @Test
    public void testDeleteInvalidNumberThrowsException() {
        assertThrows(NumberFormatException.class, () -> new DeleteCommand("delete abc"));
    }
}
