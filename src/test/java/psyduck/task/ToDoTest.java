package psyduck.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ToDo class.
 */
public class ToDoTest {

    @Test
    public void testToStringNotDone() {
        ToDo todo = new ToDo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    public void testToStringDone() {
        ToDo todo = new ToDo("borrow book");
        todo.mark();
        assertEquals("[T][X] borrow book", todo.toString());
    }

    @Test
    public void testMarkAndUnmark() {
        ToDo todo = new ToDo("borrow book");
        assertFalse(todo.isDone());

        todo.mark();
        assertTrue(todo.isDone());

        todo.unmark();
        assertFalse(todo.isDone());
    }

    @Test
    public void testGetDescription() {
        ToDo todo = new ToDo("borrow book");
        assertEquals("borrow book", todo.getDescription());
    }
}