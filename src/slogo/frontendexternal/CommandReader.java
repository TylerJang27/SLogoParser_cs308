package slogo.frontendexternal;

import java.awt.TextArea;

/**
 * This class creates a Command object that will be sent to the backend for parsing
 * @author Shruthi Kumar
 */
public class CommandReader extends TextArea {

  private String myCommand;

  /**
   * Constructor for command reader
   * @param command : command
   */
  public CommandReader(String command) {
    myCommand = command;
  }

  /**
   * Returns the command as a string
   * @return myCommand: string representation of command
   */
  public String getMyCommand() {
    return myCommand;
  }

  /**
   * Sets the command to a new command value
   * @param newCommand : the new command
   */
  public void setMyCommand(String newCommand) {
    myCommand = newCommand;
  }
}
