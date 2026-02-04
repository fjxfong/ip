package psyduck.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Deadline class.
 */
public class DeadlineTest {

    @Test
    public void testToStringValidDate() {
        Deadline deadline = new Deadline("return book", "2024-12-15");
        assertEquals("[D][ ] return book (by: Dec 15 2024)", deadline.toString());
    }

    @Test
    public void testToStringMarked() {
        Deadline deadline = new Deadline("return book", "2024-12-15");
        deadline.mark();
        assertEquals("[D][X] return book (by: Dec 15 2024)", deadline.toString());
    }

    @Test
    public void testToStringInvalidDate() {
        Deadline deadline = new Deadline("return book", "invalid");
        assertEquals("[D][ ] return book (by: Invalid date)", deadline.toString());
    }

    @Test
    public void testGetByDate() {
        Deadline deadline = new Deadline("return book", "2024-12-15");
        assertEquals(LocalDate.of(2024, 12, 15), deadline.getByDate());
    }

    @Test
    public void testGetByDateInvalid() {
        Deadline deadline = new Deadline("return book", "invalid");
        assertNull(deadline.getByDate());
    }

    @Test
    public void testGetByForStorage() {
        Deadline deadline = new Deadline("return book", "2024-12-15");
        assertEquals("2024-12-15", deadline.getByForStorage());
    }

    @Test
    public void testGetByForStorageInvalidDate() {
        Deadline deadline = new Deadline("return book", "invalid");
        assertEquals("", deadline.getByForStorage());
    }

    @Test
    public void testConstructorWithLocalDate() {
        LocalDate date = LocalDate.of(2024, 12, 15);
        Deadline deadline = new Deadline("return book", date);
        assertEquals("Dec 15 2024", deadline.getBy());
        assertEquals(date, deadline.getByDate());
    }
}
