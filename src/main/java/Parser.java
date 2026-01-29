/**
 * Handles parsing of user input commands.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command type.
     *
     * @param input User input string.
     * @return The command type.
     */
    public static CommandType parseCommand(String input) {
        if (input.isEmpty()) {
            return CommandType.UNKNOWN;
        }

        String command = input.split(" ")[0].toLowerCase();

        return switch (command) {
            case "bye" -> CommandType.BYE;
            case "list" -> CommandType.LIST;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "delete" -> CommandType.DELETE;
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            default -> CommandType.UNKNOWN;
        };
    }
}
