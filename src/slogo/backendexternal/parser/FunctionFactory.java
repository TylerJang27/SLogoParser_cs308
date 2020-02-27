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
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;
  private Map<String, List<String>> myCommands;

  public FunctionFactory(Map<String, List<String>> commands){
    functionMap = new HashMap<>();
    functionVariables = new ArrayList<>();
    functionCommands = new ArrayList<>();
    commandFactory = new CommandFactory();
    variableFactory = new VariableFactory();
    myCommands = commands;
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
    newCommands.addAll(parseComponentsFunction(components));
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

  public Stack<Command> parseComponentsFunction(Stack<String> components){
    Stack<Command> currentCommand = new Stack<>();
    while(components.size() > 0){
      Stack<Command> commands = new Stack<>();
      String current = components.pop();
      if(Input.ListStart.matches(current)){
        break;
      }
      if(Input.Constant.matches(current)){
        commands.add(commandFactory.makeConstant(current));
      }
      else if(Input.Make.matches(current)){
        if(currentCommand.size() > 0){
          commands.add(variableFactory.makeVariable(currentCommand.pop()));
        }
      }
      else if(Input.Set.matches(current)){
        if(currentCommand.size() > 0){
          commands.add(variableFactory.setVariable(currentCommand.pop()));
        }
      }
      else if(Input.Command.matches(current)){
        if(this.hasFunction(current)){
          commands.add(this.getFunction(current));
        }
        else{
          commands.add(commandFactory.makeCommand(current, currentCommand, myCommands));
        }
      }
      else if(Input.Variable.matches(current)){
        if(variableFactory.handleVariable(current)){
          commands.add(variableFactory.getVariable(current));
        }
      }
      currentCommand.addAll(commands);
    }
    return currentCommand;
  }


}
