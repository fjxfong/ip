package psyduck.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the DateParser utility class.
 */
public class DateParserTest {

    @Test
    public void testParseYyyMmDd() {
        LocalDate result = DateParser.parseDate("2024-12-15");
        assertEquals(LocalDate.of(2024, 12, 15), result);
    }

    @Test
    public void testParseDdSlashMmSlashYyyy() {
        LocalDate result = DateParser.parseDate("15/12/2024");
        assertEquals(LocalDate.of(2024, 12, 15), result);
    }

    @Test
    public void testParseMmSlashDdSlashYyyy() {
        LocalDate result = DateParser.parseDate("12/15/2024");
        assertEquals(LocalDate.of(2024, 12, 15), result);
    }

    @Test
    public void testParseMmmDdYyyy() {
        LocalDate result = DateParser.parseDate("Dec 15 2024");
        assertEquals(LocalDate.of(2024, 12, 15), result);
    }

    @Test
    public void testParseDdMmmYyyy() {
        LocalDate result = DateParser.parseDate("15 Dec 2024");
        assertEquals(LocalDate.of(2024, 12, 15), result);
    }

    @Test
    public void testParseInvalidDate() {
        LocalDate result = DateParser.parseDate("not-a-date");
        assertNull(result);
    }

    @Test
    public void testParseEmptyString() {
        LocalDate result = DateParser.parseDate("");
        assertNull(result);
    }

    @Test
    public void testParseNull() {
        LocalDate result = DateParser.parseDate(null);
        assertNull(result);
    }

    @Test
    public void testParseLeapYear() {
        LocalDate result = DateParser.parseDate("2024-02-29");
        assertEquals(LocalDate.of(2024, 2, 29), result);
    }

    @Test
    public void testParseInvalidLeapYear() {
        // 2023 is not a leap year, Feb 29 is invalid
        // Java's lenient parsing will adjust to Feb 28
        LocalDate result = DateParser.parseDate("2023-02-29");
        // The date parser should still parse it (leniently to Feb 28)
        assertNotNull(result);
        assertEquals(LocalDate.of(2023, 2, 28), result);
    }

    @Test
    public void testFormatForDisplay() {
        LocalDate date = LocalDate.of(2024, 12, 15);
        assertEquals("Dec 15 2024", DateParser.formatForDisplay(date));
    }

    @Test
    public void testFormatForDisplayNull() {
        assertEquals("Invalid date", DateParser.formatForDisplay(null));
    }

    @Test
    public void testFormatForStorage() {
        LocalDate date = LocalDate.of(2024, 12, 15);
        assertEquals("2024-12-15", DateParser.formatForStorage(date));
    }

    @Test
    public void testFormatForStorageNull() {
        assertEquals("", DateParser.formatForStorage(null));
    }
}
