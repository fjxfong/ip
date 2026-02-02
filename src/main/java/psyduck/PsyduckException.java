package psyduck;

/**
 * Represents exceptions specific to psyduck.Psyduck chatbot.
 */
public class PsyduckException extends Exception {
    /**
     * Creates a new psyduck.PsyduckException with the given message.
     *
     * @param message Error message to display.
     */
    public PsyduckException(String message) {
        super(message);
    }
}
