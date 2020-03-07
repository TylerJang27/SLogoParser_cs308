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
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;
  private Map<String, List<String>> myCommands;

  public FunctionFactory(Map<String, List<String>> commands){
    functionMap = new HashMap<>();
    functionVariables = new ArrayList<>();
    functionCommands = new ArrayList<>();
    myCommands = commands;
    commandFactory = new CommandFactory(myCommands);
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
    fillCommands(components);
    fillVariables(components);
    return new MakeUserInstruction(functionMap.get(func), functionVariables, functionCommands);
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
        System.out.println("Variables Being Made in Function:");
        System.out.println(variableFactory.getVariableString());
      }
    }
  }

  private void fillCommands(Stack<String> components){
    functionCommands.clear();
    Stack<Command> newCommands = new Stack<Command>();
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
    Stack<List<Command>> listCommands = new Stack();

    while(components.size() > 0){
      Stack<Command> commands = new Stack<>();
      String current = components.pop();
      String controlType = "";
      if(Input.ListStart.matches(current)){
        break;
      }
      try{
        System.out.println("FUNCTION FACTORY");
        Method checkType = Parser.class.getDeclaredMethod("getInputType", String.class);
        controlType = (String) checkType.invoke(new Parser(), current);
        System.out.println(controlType);
        Method control = Parser.class.getDeclaredMethod(controlType, String.class, Stack.class, Stack.class, Stack.class, List.class);
        System.out.println(control);
        commands.add((Command) control.invoke(new Parser(), current, commands, listCommands, currentCommand, new ArrayList<>()));
      }catch(Exception e){
        throw new InvalidCommandException(current);
      }
    }
    return currentCommand;
  }

  public Function buildFunction(String key, List<Command> commands, Stack<List<Command>> listCommands){
    return new Function();
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
