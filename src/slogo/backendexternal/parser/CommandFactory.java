package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.commands.Command;
import slogo.commands.turtlecommands.Backward;
import slogo.commands.turtlecommands.Forward;
import slogo.commands.turtlecommands.Home;

public class CommandFactory {

  public CommandFactory(){}

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

  private String validateCommand(String current, Map<String, List<String>> myCommands) {
    for(String key : myCommands.keySet()){
      if(myCommands.get(key).contains(current)){
        return key;
      }
    }
    return "";
  }

  public Command buildCommand(String key, List<Double> inputs){
    if(key == "Forward"){
      //return new Forward(inputs.get(0));
    }
    if(key == "Backward"){
      //return new Backward(inputs.get(0));
    }
    if(key == "Home"){
      //return new Home();
    }
    //return new Home();
    return null;
  }
}
