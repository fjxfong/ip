package psyduck.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Event class.
 */
public class EventTest {

    @Test
    public void testToStringValidDates() {
        Event event = new Event("meeting", "2024-12-20", "2024-12-21");
        assertEquals("[E][ ] meeting (from: Dec 20 2024 to: Dec 21 2024)", event.toString());
    }

    @Test
    public void testToStringMarked() {
        Event event = new Event("meeting", "2024-12-20", "2024-12-21");
        event.mark();
        assertEquals("[E][X] meeting (from: Dec 20 2024 to: Dec 21 2024)", event.toString());
    }

    @Test
    public void testToStringInvalidFrom() {
        Event event = new Event("meeting", "invalid", "2024-12-21");
        assertEquals("[E][ ] meeting (from: Invalid date to: Dec 21 2024)", event.toString());
    }

    @Test
    public void testToStringInvalidTo() {
        Event event = new Event("meeting", "2024-12-20", "invalid");
        assertEquals("[E][ ] meeting (from: Dec 20 2024 to: Invalid date)", event.toString());
    }

    @Test
    public void testGetFromDate() {
        Event event = new Event("meeting", "2024-12-20", "2024-12-21");
        assertEquals(LocalDate.of(2024, 12, 20), event.getFromDate());
    }

    @Test
    public void testGetToDate() {
        Event event = new Event("meeting", "2024-12-20", "2024-12-21");
        assertEquals(LocalDate.of(2024, 12, 21), event.getToDate());
    }

    @Test
    public void testGetFromForStorage() {
        Event event = new Event("meeting", "2024-12-20", "2024-12-21");
        assertEquals("2024-12-20", event.getFromForStorage());
    }

    @Test
    public void testGetToForStorage() {
        Event event = new Event("meeting", "2024-12-20", "2024-12-21");
        assertEquals("2024-12-21", event.getToForStorage());
    }

    @Test
    public void testConstructorWithLocalDate() {
        LocalDate from = LocalDate.of(2024, 12, 20);
        LocalDate to = LocalDate.of(2024, 12, 21);
        Event event = new Event("meeting", from, to);
        assertEquals("Dec 20 2024", event.getFrom());
        assertEquals("Dec 21 2024", event.getTo());
    }

    @Test
    public void testSameDayEvent() {
        Event event = new Event("meeting", "2024-12-20", "2024-12-20");
        assertEquals(event.getFromDate(), event.getToDate());
    }
}