package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javafx.scene.chart.XYChart;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.controlcommands.Constant;
import slogo.commands.turtlecommands.Backward;
import slogo.commands.turtlecommands.Forward;
import slogo.commands.turtlecommands.Home;

public class CommandFactory {

  private static final double X_MAX = 100.0;
  private static final double Y_MAX = 100.0;

  private String currentMode;
  private CommandCount myCounts;

  public CommandFactory(){
    currentMode = "normal";
    myCounts = new CommandCount();
  }


  public Command buildCommand(String key, List<Double> inputs, List<Command> recursiveCalls){
    List<Command> commands = new ArrayList<>();
    for(double d : inputs){
      commands.add(new Constant(d));
    }
    commands.addAll(recursiveCalls);
    if(key == "Forward"){
      return new Forward(commands.get(0), X_MAX, Y_MAX, currentMode);
    }
    if(key == "Backward"){
      return new Backward(commands.get(0), X_MAX, Y_MAX, currentMode);
    }
    if(key == "Home"){
      return new Home(X_MAX, Y_MAX, currentMode);
    }
    return null;
  }

  public Command makeCommand(String command, Stack<Double> constants, Stack<Command> previousCommands, Map<String, List<String>> myCommands) {
    String formalCommand = validateCommand(command, myCommands);
    List<Double> inputs = new ArrayList<>();
    List<Command> recursiveCalls = new ArrayList<>();
    int count = myCounts.getCount(formalCommand);
    while(inputs.size() < count){
      if(constants.size() > 0){
        inputs.add(constants.pop());
      }
      else{
        recursiveCalls.add(previousCommands.pop());
      }
    }
    return buildCommand(formalCommand, inputs, recursiveCalls);
  }

  public void setMode(String mode){
    currentMode = mode;
  }

  private String validateCommand(String current, Map<String, List<String>> myCommands) throws InvalidCommandException {
    try {
      for (String key : myCommands.keySet()) {
        if (myCommands.get(key).contains(current)) {
          System.out.println(key);
          return key;
        }
      }
    } catch (Exception e) {
      throw new InvalidCommandException(String.format("The command %s could not be found", current));
    }
    return null;
  }

}
