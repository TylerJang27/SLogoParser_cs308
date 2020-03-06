package slogo.backendexternal.parser;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
  private List<String> commandHistory;
  private List<slogo.commands.Command> newCommands;
  private Map<String, List<String>> myCommands;
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;
  private FunctionFactory functionFactory;
  private Stack<Command> currentCommands;
  private Stack<String> currentComponents;
  private String lastLine;
  private boolean inList;
  private ResourceBundle controlTypes;
  private ResourceBundle parserMethods;

  public Parser(){ this(new Translator());}

  public Parser(Translator translator){
    myCommands = translator.getCurrentCommands();
    newCommands = new ArrayList<>();
    commandHistory = new ArrayList<>();
    commandFactory = new CommandFactory(myCommands);
    variableFactory = new VariableFactory();
    functionFactory = new FunctionFactory(myCommands);
    currentCommands = new Stack<>();
    currentComponents = new Stack<>();
    controlTypes = ResourceBundle.getBundle(Parser.class.getPackageName() + ".resources." + "Syntax");
    parserMethods = ResourceBundle.getBundle(Parser.class.getPackageName() + ".resources." + "Parser");
    inList = false;
  }

  public void parseLine(String line){
    lastLine = line;
    commandHistory.add(line);
    String[] inputs = line.split(" ");
    for(String input : inputs){
      currentComponents.push(input);
    }
    currentCommands.addAll(parseComponents());
    while(currentCommands.size() > 0){
      newCommands.add(currentCommands.pop());
    }
  }

  public List<Command> sendCommands(){
    List<Command> toSend = new ArrayList<>(newCommands);
    newCommands.clear();
    return toSend;
  }

  public Stack<Command> parseComponents() throws InvalidCommandException {
    Stack<Command> currentCommand = new Stack<>();
    Stack<List<Command>> listCommands = new Stack<>();
    List<Command> currentList = new ArrayList<>();
    while (currentComponents.size() > 0) {
      Stack<Command> commands = new Stack<>();
      String current = currentComponents.pop();
      System.out.println(current);
      String controlType = getInputType(current);
      System.out.println(controlType);
      try{
        Method control;
        if(controlType.equals(parserMethods.getString("ListEnd")) || controlType.equals(parserMethods.getString("ListStart"))){
          control = Parser.class.getDeclaredMethod(controlType, Stack.class, Stack.class, List.class);
          System.out.println(control);
          control.invoke(this, commands, listCommands, currentList);
        }
        else{
          control = Parser.class.getDeclaredMethod(controlType, String.class, Stack.class, Stack.class, Stack.class, List.class);
          System.out.println(control);
          if(inList){
            currentList.add((Command) control.invoke(this, current, commands, listCommands, currentCommand, currentList));
          }else{
            commands.add((Command) control.invoke(this, current, commands, listCommands, currentCommand, currentList));
          }
        }
      }catch(Exception e){
        throw new InvalidCommandException(current);
      }
      if(!inList){
        currentCommand.addAll(commands);
      }
    }
    return currentCommand;
  }

  private String getInputType(String current) {
    for(String key : controlTypes.keySet()){
      Pattern regex = Pattern.compile(controlTypes.getString(key), Pattern.CASE_INSENSITIVE);
      if(regex.matcher(current).matches()){
        return parserMethods.getString(key);
      }
    }
    return current;
  }

  public Map<String, List<String>> getCommands(){
    return myCommands;
  }

  public String getVariableString() {
    return variableFactory.getVariableString();
  }

  public String getLastLine() { return lastLine; }

  public void setMode(String mode){ commandFactory.setMode(mode); }

  public void setLanguage(Translator translator) {
    myCommands = translator.getCurrentCommands();
  }

  private Command Constant(String current, Stack<Command> commands, Stack<List<Command>> listCommands,
      Stack<Command> currentCommand, List<Command> currentList){
      return commandFactory.makeConstant(current);
  }

  private Command Make(String current, Stack<Command> commands, Stack<List<Command>> listCommands,
      Stack<Command> currentCommand, List<Command> currentList){
      return variableFactory.makeVariable(currentCommand.pop());
  }

  private Command Set(String current, Stack<Command> commands, Stack<List<Command>> listCommands,
      Stack<Command> currentCommand, List<Command> currentList){
      return variableFactory.setVariable(currentCommand.pop());
  }

  private Command Command(String current, Stack<Command> commands, Stack<List<Command>> listCommands,
      Stack<Command> currentCommand, List<Command> currentList){
    if (functionFactory.hasFunction(current)) {
      return functionFactory.runFunction(current, currentCommand);
    } else {
      if(inList){
        currentCommand.add(currentList.get(0));
        currentList.remove(0);
      }
      return commandFactory.makeCommand(current, currentCommand, listCommands, myCommands);
    }
  }

  private Command Variable(String current, Stack<Command> commands, Stack<List<Command>> listCommands,
      Stack<Command> currentCommand, List<Command> currentList){
    if (variableFactory.handleVariable(current)) {
      return variableFactory.getVariable(current);
    }
    return variableFactory.getVariable(current);
  }

  private void ListEnd(Stack<Command> commands, Stack<List<Command>> listCommands, List<Command> currentList){
    inList = true;
    currentList.clear();
    if (checkFunction(currentComponents)) {
      commands.add(functionFactory.handleFunction(currentComponents));
      inList = false;
    }
  }

  private void ListStart(Stack<Command> commands, Stack<List<Command>> listCommands, List<Command> currentList){
    inList = false;
    listCommands.add(currentList);
  }

  private boolean checkFunction(Stack<String> components) {
    Iterator<String> iter = components.iterator();
    while(iter.hasNext()){
      String current = iter.next();
      if(Input.TO.matches(current)){
        components.remove(current);
        return true;
      }
    }
    return false;
  }
}
