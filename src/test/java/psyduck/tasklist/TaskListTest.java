package psyduck.tasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psyduck.task.Task;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TaskList class.
 */
public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddToDo() {
        taskList.addToDo("borrow book");
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] borrow book", taskList.get(0).toString());
    }

    @Test
    public void testAddDeadline() {
        taskList.addDeadline("return book", "2024-12-15");
        assertEquals(1, taskList.size());
        assertEquals("[D][ ] return book (by: Dec 15 2024)", taskList.get(0).toString());
    }

    @Test
    public void testAddEvent() {
        taskList.addEvent("meeting", "2024-12-20", "2024-12-21");
        assertEquals(1, taskList.size());
        assertEquals("[E][ ] meeting (from: Dec 20 2024 to: Dec 21 2024)", taskList.get(0).toString());
    }

    @Test
    public void testMarkTask() {
        taskList.addToDo("borrow book");
        taskList.markTask(0);
        assertTrue(taskList.get(0).isDone());
        assertEquals("[T][X] borrow book", taskList.get(0).toString());
    }

    @Test
    public void testUnmarkTask() {
        taskList.addToDo("borrow book");
        taskList.markTask(0);
        taskList.unmarkTask(0);
        assertFalse(taskList.get(0).isDone());
        assertEquals("[T][ ] borrow book", taskList.get(0).toString());
    }

    @Test
    public void testDeleteTask() {
        taskList.addToDo("borrow book");
        taskList.addToDo("read book");
        Task deleted = taskList.delete(0);
        assertEquals(1, taskList.size());
        assertEquals("borrow book", deleted.getDescription());
        assertEquals("[T][ ] read book", taskList.get(0).toString());
    }

    @Test
    public void testDeleteLastTask() {
        taskList.addToDo("borrow book");
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testListEmpty() {
        assertEquals("", taskList.list());
    }

    @Test
    public void testListMultipleTasks() {
        taskList.addToDo("borrow book");
        taskList.addDeadline("return book", "2024-12-15");
        String expected = "1.[T][ ] borrow book\n"
                + "2.[D][ ] return book (by: Dec 15 2024)\n";
        assertEquals(expected, taskList.list());
    }

    @Test
    public void testSize() {
        assertEquals(0, taskList.size());
        taskList.addToDo("task 1");
        assertEquals(1, taskList.size());
        taskList.addToDo("task 2");
        assertEquals(2, taskList.size());
    }

    @Test
    public void testGetOutOfBounds() {
        taskList.addToDo("borrow book");
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(5));
    }

    @Test
    public void testDeleteOutOfBounds() {
        taskList.addToDo("borrow book");
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.delete(5));
    }
}