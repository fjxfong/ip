package psyduck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.PsyduckException;
import psyduck.Ui;
import psyduck.storage.Storage;
import psyduck.tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ToDoCommand class.
 */
public class ToDoCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/test_todo_command.txt");
    }

    @Test
    public void testExecuteAddsTask() throws PsyduckException {
        Command command = new ToDoCommand("todo borrow book");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] borrow book", taskList.get(0).toString());
    }

    @Test
    public void testEmptyDescriptionThrowsException() {
        assertThrows(PsyduckException.class, () -> new ToDoCommand("todo"));
    }

    @Test
    public void testOnlySpacesThrowsException() {
        assertThrows(PsyduckException.class, () -> new ToDoCommand("todo   "));
    }

    @Test
    public void testIsNotExitCommand() throws PsyduckException {
        Command command = new ToDoCommand("todo borrow book");
        assertFalse(command.isExit());
    }
}