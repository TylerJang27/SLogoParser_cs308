package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
  private Parser parser;
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;

  public FunctionFactory(){
    functionMap = new HashMap<>();
    functionVariables = new ArrayList<>();
    functionCommands = new ArrayList<>();
    parser = new Parser();
    commandFactory = new CommandFactory();
    variableFactory = new VariableFactory();
  }

  public boolean hasFunction(String funcName){
    return functionMap.containsKey(funcName);
  }

  public Function getFunction(String funcName){
    return functionMap.get(funcName);
  }

  public MakeUserInstruction handleFunction(Stack<String> components){
    String func = getName(components);
    fillVariables(components);
    fillCommands(components);
    return new MakeUserInstruction(functionMap.get(func), functionVariables, functionCommands);
  }

  private void fillVariables(Stack<String> components) {
    while(components.size() > 0){
      String current = components.pop();
      if(Input.Variable.matches(current)){
        variableFactory.handleVariable(current);
        functionVariables.add(variableFactory.getVariable(current));
      }
    }
  }

  private void fillCommands(Stack<String> components){
    Stack<Command> newCommands = new Stack<Command>();
    newCommands.addAll(parser.parseComponents(components));
    while(newCommands.size() > 0){
      functionCommands.add(newCommands.pop());
    }
  }

  private String getName(Stack<String> components){
    Iterator<String> iter = components.iterator();
    while(iter.hasNext()){
      String current = iter.next();
      if(Input.Command.matches(current)){
        components.remove(current);
        return current;
      }
    }
    return "";
  }


}
