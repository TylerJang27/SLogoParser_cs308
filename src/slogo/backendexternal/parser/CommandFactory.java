package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.controlcommands.Constant;
import slogo.commands.queriescommands.Heading;
import slogo.commands.queriescommands.IsPenDown;
import slogo.commands.queriescommands.IsShowing;
import slogo.commands.queriescommands.XCoordinate;
import slogo.commands.queriescommands.YCoordinate;
import slogo.commands.turtlecommands.Backward;
import slogo.commands.turtlecommands.ClearScreen;
import slogo.commands.turtlecommands.Forward;
import slogo.commands.turtlecommands.HideTurtle;
import slogo.commands.turtlecommands.Home;
import slogo.commands.turtlecommands.Left;
import slogo.commands.turtlecommands.PenDown;
import slogo.commands.turtlecommands.PenUp;
import slogo.commands.turtlecommands.Right;
import slogo.commands.turtlecommands.SetHeading;
import slogo.commands.turtlecommands.SetPosition;
import slogo.commands.turtlecommands.SetTowards;
import slogo.commands.turtlecommands.ShowTurtle;

public class CommandFactory {

  private static final double X_MAX = 100.0;
  private static final double Y_MAX = 100.0;

  private String currentMode;
  private CommandCounter myCounts;

  public CommandFactory(){
    currentMode = "toroidal";
    myCounts = new CommandCounter();
  }

  public Command makeCommand(String command, Stack<Command> previous, Map<String, List<String>> myCommands) {
    String formalCommand = validateCommand(command, myCommands);
    List<Command> commands = new ArrayList<>();
    int count = myCounts.getCount(formalCommand);
    while(commands.size() < count){
      if(previous.size() > 0){
        commands.add(previous.pop());
      }
    }
    return buildCommand(formalCommand, commands);
  }

  public Command buildCommand(String key, List<Command> commands){
    if(key.equals("Backward")){
      return new Backward(commands.get(0), X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("ClearScreen")){
      return new ClearScreen(X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("Forward")){
      return new Forward(commands.get(0), X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("HideTurtle")){
      return new HideTurtle();
    }
    if(key.equals("Home")){
      return new Home(X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("Left")){
      return new Left(commands.get(0));
    }
    if(key.equals("PenDown")){
      return new PenDown();
    }
    if(key.equals("PenUp")){
      return new PenUp();
    }
    if(key.equals("Right")){
      return new Right(commands.get(0));
    }
    if(key.equals("SetHeading")){
      return new SetHeading(commands.get(0));
    }
    if(key.equals("SetPosition")){
      return new SetPosition(commands.get(0), commands.get(1), X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("SetTowards")){
      return new SetTowards(commands.get(0), commands.get(1));
    }
    if(key.equals("ShowTurtle")){
      return new ShowTurtle();
    }
    if(key.equals("Heading")){
      return new Heading();
    }
    if(key.equals("IsPenDown")){
      return new IsPenDown();
    }
    if(key.equals("IsShowing")){
      return new IsShowing();
    }
    else if(key.equals("XCoordinate")){
      return new XCoordinate();
    }
    else if(key.equals("YCoordinate")){
      return new YCoordinate();
    }
    return null;
  }

  public void setMode(String mode){
    currentMode = mode;
  }

  private String validateCommand(String current, Map<String, List<String>> myCommands) throws InvalidCommandException {
    try {
      for (String key : myCommands.keySet()) {
        if (myCommands.get(key).contains(current)) {
          return key;
        }
      }
    } catch (Exception e) {
      throw new InvalidCommandException(String.format("The command %s could not be found", current));
    }
    return null;
  }

}
