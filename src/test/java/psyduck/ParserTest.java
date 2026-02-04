package psyduck;

import org.junit.jupiter.api.Test;
import psyduck.command.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Parser class.
 */
public class ParserTest {

    @Test
    public void testParseByeCommand() throws PsyduckException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
        assertTrue(command.isExit());
    }

    @Test
    public void testParseListCommand() throws PsyduckException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
        assertFalse(command.isExit());
    }

    @Test
    public void testParseToDoCommand() throws PsyduckException {
        Command command = Parser.parse("todo borrow book");
        assertInstanceOf(ToDoCommand.class, command);
    }

    @Test
    public void testParseDeadlineCommand() throws PsyduckException {
        Command command = Parser.parse("deadline return book /by 2024-12-15");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParseEventCommand() throws PsyduckException {
        Command command = Parser.parse("event meeting /from 2024-12-20 /to 2024-12-21");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() throws PsyduckException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() throws PsyduckException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseDeleteCommand() throws PsyduckException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseFindCommand() throws PsyduckException {
        Command command = Parser.parse("find book");
        assertInstanceOf(SearchCommand.class, command);
    }

    @Test
    public void testParseFindDateCommand() throws PsyduckException {
        Command command = Parser.parse("finddate 2024-12-15");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    public void testParseUnknownCommand() {
        assertThrows(PsyduckException.class, () -> Parser.parse("blah"));
    }

    @Test
    public void testParseEmptyInput() {
        assertThrows(PsyduckException.class, () -> Parser.parse(""));
    }

    @Test
    public void testParseToDoEmptyDescription() {
        assertThrows(PsyduckException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void testParseDeadlineMissingBy() {
        assertThrows(PsyduckException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void testParseEventMissingFrom() {
        assertThrows(PsyduckException.class, () -> Parser.parse("event meeting /to 2024-12-21"));
    }

    @Test
    public void testParseEventMissingTo() {
        assertThrows(PsyduckException.class, () -> Parser.parse("event meeting /from 2024-12-20"));
    }

    @Test
    public void testParseMarkMissingNumber() {
        assertThrows(PsyduckException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void testParseUnmarkMissingNumber() {
        assertThrows(PsyduckException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void testParseDeleteMissingNumber() {
        assertThrows(PsyduckException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void testParseFindMissingKeyword() {
        assertThrows(PsyduckException.class, () -> Parser.parse("find"));
    }

    @Test
    public void testParseFindDateMissingDate() {
        assertThrows(PsyduckException.class, () -> Parser.parse("finddate"));
    }

    @Test
    public void testParseFindDateInvalidDate() {
        assertThrows(PsyduckException.class, () -> Parser.parse("finddate not-a-date"));
    }
}