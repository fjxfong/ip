package psyduck;

/**
 * Represents the types of commands that psyduck.Psyduck can process.
 */
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    UNKNOWN
}
