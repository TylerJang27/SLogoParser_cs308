package slogo.frontendexternal;

public class CommandReader {

  private String myCommand;

  public CommandReader(String command) {
    myCommand = command;
  }

  public String getMyCommand() {
    return myCommand;
  }

  public void setMyCommand(String newCommand) {
    myCommand = newCommand;
  }
}
