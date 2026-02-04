package psyduck;

import psyduck.command.Command;
import psyduck.command.FindCommand;
import psyduck.command.ToDoCommand;
import psyduck.command.DeadlineCommand;
import psyduck.command.EventCommand;
import psyduck.command.DeleteCommand;
import psyduck.command.ExitCommand;
import psyduck.command.ListCommand;
import psyduck.command.MarkCommand;
import psyduck.command.UnmarkCommand;
import psyduck.command.SearchCommand;

/**
 * Handles parsing of user input commands and creates the appropriate Command Object.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding Command.
     *
     * @param input The full user input string.
     * @return The parsed Command object.
     * @throws PsyduckException If the command is unknown or input is invalid.
     */
    public static Command parse(String input) throws PsyduckException {
        if (input.isEmpty()) {
            throw new PsyduckException("OOPS!!! Please enter a command!");
        }

        String commandWord = input.split(" ")[0].toLowerCase();

        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(input);
            case "unmark" -> new UnmarkCommand(input);
            case "delete" -> new DeleteCommand(input);
            case "todo" -> new ToDoCommand(input);
            case "deadline" -> new DeadlineCommand(input);
            case "event" -> new EventCommand(input);
            case "find" -> new SearchCommand(input);
            case "finddate" -> new FindCommand(input);
            default -> throw new PsyduckException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        };
    }
}
