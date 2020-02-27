package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.commands.Command;
import slogo.commands.controlcommands.Function;
import slogo.commands.controlcommands.MakeUserInstruction;
import slogo.commands.controlcommands.Variable;

public class FunctionFactory {

  private Map<String, Function> functionMap;
  private List<Variable> functionVariables;
  private List<Command> functionCommands;
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;

  public FunctionFactory(){
    functionMap = new HashMap<>();
    functionVariables = new ArrayList<>();
    functionCommands = new ArrayList<>();
    commandFactory = new CommandFactory();
    variableFactory = new VariableFactory();
  }

  public Function getFunction(String funcName){
    if(functionMap.containsKey(funcName)){
      return functionMap.get(funcName);
    }
    return null;
  }

  public MakeUserInstruction alterFunction(String funcName, Command command){
    return new MakeUserInstruction(functionMap.get(funcName), functionVariables, functionCommands);
  }


  public MakeUserInstruction handleFunction(Stack<String> components){
    fillVariables(components);
    fillCommands(components);
    String func = getName(components);
    return new MakeUserInstruction(functionMap.get(func), functionVariables, functionCommands);
  }

  private void fillVariables(Stack<String> components) {
    while(components.size() > 0){
      String current = components.pop();

    }
  }

  private void fillCommands(Stack<String> components){
    while(components.size() > 0){

    }
  }

  private String getName(Stack<String> components){
    while(components.size() > 0){
      String current = components.pop();
      if(Input.Command.matches(current)){
        return current;
      }
    }
    return "";
  }


}
