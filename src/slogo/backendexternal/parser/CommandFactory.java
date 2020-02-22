package slogo.backendexternal.parser;

import java.util.List;
import java.util.Map;
import slogo.commands.Command;
import slogo.commands.turtlecommands.Backward;
import slogo.commands.turtlecommands.Forward;
import slogo.commands.turtlecommands.Home;

//NOT COMPLETE
public class CommandFactory {
  private List<Command> POSSIBLE_COMMANDS;

  public static Command makeCommand(Map<String, List<Double>> components, Map<String, List<String>> myCommands) {
    String command = "";
    for(String key : components.keySet()){
      command = key;
    }
    for(String key : myCommands.keySet()){
      if(myCommands.get(key).indexOf(command) > -1){
        return buildCommand(key, components.get(command));
      }
    }
    return null;
  }

  public static Command buildCommand(String key, List<Double> inputs){
    if(key == "Forward"){
      return new Forward(inputs.get(0));
    }
    if(key == "Backward"){
      return new Backward(inputs.get(0));
    }
    if(key == "Home"){
      return new Home();
    }
    return new Home();
  }
}