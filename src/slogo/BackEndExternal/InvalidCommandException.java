package slogo.BackEndExternal;

/**
 * Class for formatting the types of exceptions that may occur when an invalid command is parsed.
 * <p>
 * Class based mainly on XMLException.java from spike_simulation by Robert C. Duvall
 * https://coursework.cs.duke.edu/compsci308_2020spring/spike_simulation/blob/master/src/xml/XMLException.java
 *
 * @author Tyler Jang
 */
public class InvalidCommandException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * Create an Exception based on an issue during parsing
     *
     * @param message a String representing the message to be displayed, including %d, %f, or %s for customizable formatted items
     * @param values additional arguments representing items to fill the String.
     */
    public InvalidCommandException(String message, Object... values) {
        super(String.format(message, values));
    }

    /**
     * Create an Exception based on a caught exception with a different message.
     *
     * @param cause the caught Exception
     * @param message a String representing the message to be displayed, including %d, %f, or %s for customizable formatted items
     * @param values additional arguments representing items to fill the String.
     */
    public InvalidCommandException(Throwable cause, String message, Object... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     *
     * @param cause the caught Exception
     */
    public InvalidCommandException(Throwable cause) {
        super(cause);
    }
}
