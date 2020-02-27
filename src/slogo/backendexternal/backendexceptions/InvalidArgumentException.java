package slogo.backendexternal.backendexceptions;

public class InvalidArgumentException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  /**
   * Create an Exception based on an issue during parsing
   *
   * @param message a String representing the message to be displayed, including %d, %f, or %s for customizable formatted items
   * @param values additional arguments representing items to fill the String.
   */
  public InvalidArgumentException(String message, Object... values) {
    super(String.format(message, values));
  }

  /**
   * Create an Exception based on a caught exception with a different message.
   *
   * @param cause the caught Exception
   * @param message a String representing the message to be displayed, including %d, %f, or %s for customizable formatted items
   * @param values additional arguments representing items to fill the String.
   */
  public InvalidArgumentException(Throwable cause, String message, Object... values) {
    super(String.format(message, values), cause);
  }

  /**
   * Create an exception based on a caught exception, with no additional message.
   *
   * @param cause the caught Exception
   */
  public InvalidArgumentException(Throwable cause) {
    super(cause);
  }

}
