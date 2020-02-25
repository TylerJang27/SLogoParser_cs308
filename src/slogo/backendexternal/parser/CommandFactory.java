package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.turtlecommands.Backward;
import slogo.commands.turtlecommands.Forward;
import slogo.commands.turtlecommands.Home;

public class CommandFactory {

  public CommandFactory(){}

  //Problem:
  //This only works when there are only single variable jawns called sequentially
  //so like, barely ever :)

  public List<Command> makeCommands(Stack<String> commands, Stack<Double> inputs, Map<String, List<String>> myCommands) {
    List<Command> newCommands = new ArrayList<Command>();
    Map<String, List<Double>> currentCommand = new HashMap<String, List<Double>>();
    while(commands.size() > 0){
      String currentString = commands.pop();
      String key = validateCommand(currentString, myCommands);
      if(!key.equals("")){
        List<Double> currentInputs = new ArrayList<Double>();
        while(currentInputs.size() < 1){
          currentInputs.add(inputs.pop());
        }
        newCommands.add(buildCommand(key, inputs));
      }
    }
    return newCommands;
  }

  private String validateCommand(String current, Map<String, List<String>> myCommands) throws InvalidCommandException {
    try {
      for (String key : myCommands.keySet()) {
        System.out.println(current);
        System.out.println(key);
        System.out.println(myCommands.get(key));
        if (myCommands.get(key).contains(current)) {
          System.out.println("LOOK I FOUND IT YAY");
          return key;
        }
      }
    } catch (Exception e) {
      throw new InvalidCommandException(String.format("The command %s could not be found", current));
    }
    return null;
  }

  public Command buildCommand(String key, List<Double> inputs){
    if(key == "Forward"){
//      return new Forward(, 100, 100, "IDK");
    }
    if(key == "Backward"){
//      return new Backward(inputs.get(0)), 100, 100, "IDK";
    }
    if(key == "Home"){
      return new Home(100, 100, "normal");
    }
    return null;
  }
}
