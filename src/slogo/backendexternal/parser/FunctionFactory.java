package slogo.backendexternal.parser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.controlcommands.Function;
import slogo.commands.controlcommands.MakeUserInstruction;
import slogo.commands.controlcommands.RunFunction;
import slogo.commands.controlcommands.Variable;

public class FunctionFactory {

  private Map<String, Function> functionMap;
  private List<Variable> functionVariables;
  private List<Command> functionCommands;
  private VariableFactory variableFactory;

  public FunctionFactory(){
    functionMap = new HashMap<>();
    functionVariables = new ArrayList<>();
    functionCommands = new ArrayList<>();
    variableFactory = new VariableFactory();
  }

  public boolean hasFunction(String funcName){
    return functionMap.containsKey(funcName);
  }

  public RunFunction runFunction(String funcName, Stack<Command> commands){
    List<Command> variableValues = new ArrayList<>();
    Function func = functionMap.get(funcName);
    while(variableValues.size() < func.getNumVars()){
      variableValues.add(commands.pop());
    }
    //TODO: GIVE SUPPLIER AND CONSUMER
    return new RunFunction(functionMap.get(funcName), variableValues);
  }

  public MakeUserInstruction handleFunction(Stack<String> components){
    String func = getName(components);
    functionMap.put(func, new Function());
    Stack<String> executed = getFunctionCommands(components);
    fillVariables(components);
    fillCommands(executed);
    return new MakeUserInstruction(functionMap.get(func), functionVariables, functionCommands);
  }

  private Stack<String> getFunctionCommands(Stack<String> components){
    Stack<String> commandStack = new Stack<>();
    while(components.size() > 0){
      String next = components.pop();
      if(Input.ListStart.matches(next)){
        break;
      }
      commandStack.add(next);
    }
    return reverseStack(commandStack);
  }

  private Stack<String> reverseStack(Stack<String> revStack) {
    Stack<String> retStack = new Stack<>();
    while (revStack.size() > 0) {
      retStack.add(revStack.pop());
    }
    return retStack;
  }

  private void fillVariables(Stack<String> components) {
    functionVariables.clear();
    while(components.size() > 0){
      String current = components.pop();
      if(Input.ListStart.matches(current)){
        break;
      }
      if(Input.Variable.matches(current)){
        variableFactory.handleVariable(current);
        functionVariables.add(variableFactory.getVariable(current));
      }
    }
  }

  private void fillCommands(Stack<String> components){
    functionCommands.clear();
    Stack<Command> newCommands = new Stack<>();
    newCommands.addAll(parseComponentsFunction(components));
    while(newCommands.size() > 0){
      functionCommands.add(newCommands.pop());
    }
  }

  private String getName(Stack<String> components){
    Iterator<String> iterator = components.iterator();
    while(iterator.hasNext()){
      String current = iterator.next();
      if(Input.Command.matches(current)){
        components.remove(current);
        return current;
      }
    }
    return "";
  }

  private Stack<Command> parseComponentsFunction(Stack<String> components){
    Stack<Command> currentCommand = new Stack<>();
    Stack<List<Command>> listCommands = new Stack<>();
    while(components.size() > 0){
      Stack<Command> commands = new Stack<>();
      String current = components.pop();
      String controlType = "";
      if(Input.ListStart.matches(current)){
        break;
      }
      try{
        Method checkType = Parser.class.getDeclaredMethod("getInputType", String.class);
        controlType = (String) checkType.invoke(new Parser(), current);
        Method control = Parser.class.getDeclaredMethod(controlType, String.class, Stack.class, Stack.class, Stack.class, List.class);
        Command newCommand = (Command) control.invoke(new Parser(variableFactory), current, commands, listCommands, currentCommand, new ArrayList<>());
        commands.add(newCommand);
        currentCommand.add(newCommand);
      }catch(Exception e){
        throw new InvalidCommandException(current);
      }
    }

    return currentCommand;
  }

  public List<String> getFunctionString() {
    List<String> ret = new ArrayList<String>();
    for(String s : functionMap.keySet()){
      StringBuilder builder = new StringBuilder();
      Function f = functionMap.get(s);
      int variables = f.getNumVars();
      builder.append(s + " [ ");
      for(int i = 0; i < variables; i++){
        builder.append(":var" + i + " ");
      }
      builder.append(" ] ");
      ret.add(builder.toString());
    }
    return ret;
  }
}
