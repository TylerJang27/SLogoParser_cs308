package slogo.backendexternal.parser;

import java.util.Iterator;
import java.util.Stack;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.view.Display;
import slogo.view.MainView;

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
  }


  public void parseLine(String line){
    lastLine = line;
    commandHistory.add(line);
    String[] inputs = line.split(" ");
    for(String input : inputs){
      currentComponents.push(input);
      //System.out.println(input);
    }
    currentCommands.addAll(parseComponents(currentComponents));
    while(currentCommands.size() > 0){
      newCommands.add(currentCommands.pop());
    }
  }

  public List<Command> sendCommands(){
    List<Command> toSend = new ArrayList<>(newCommands);
    newCommands.clear();
    return toSend;
  }

  public Stack<Command> parseComponents(Stack<String> components) throws InvalidCommandException {
    Stack<Command> currentCommand = new Stack<>();
    Stack<List<Command>> listCommands = new Stack<>();
    List<Command> currentList = new ArrayList<>();
    boolean inList = false;
    while (components.size() > 0) {
      Stack<Command> commands = new Stack<>();
      String current = components.pop();
      if (Input.Constant.matches(current)) {
        commands.add(commandFactory.makeConstant(current));
      } else if (Input.Make.matches(current)) {
        if (currentCommand.size() > 0) {
          commands.add(variableFactory.makeVariable(currentCommand.pop()));
        }
      } else if (Input.Set.matches(current)) {
        if (currentCommand.size() > 0) {
          commands.add(variableFactory.setVariable(currentCommand.pop()));
        }
      } else if (Input.Command.matches(current)) {
        if (functionFactory.hasFunction(current)) {
          commands.add(functionFactory.runFunction(current, currentCommand));
        } else {
          System.out.println(current);
          System.out.println(currentCommand.size());
          System.out.println(listCommands.size());
          commands.add(commandFactory.makeCommand(current, currentCommand, listCommands, myCommands));
        }
      } else if (Input.Variable.matches(current)) {
        if (variableFactory.handleVariable(current)) {
          commands.add(variableFactory.getVariable(current));
        }
      } else if (Input.ListEnd.matches(current)) {
        inList = true;
        currentList.clear();
        if (checkFunction(components)) {
          commands.add(functionFactory.handleFunction(components));
          inList = false;
        }
      } else if(Input.ListStart.matches(current)){
        inList = false;
        listCommands.add(currentList);
        currentCommand.clear();
//        currentCommand.add(currentList);
      }

      if(inList) {
        currentList.addAll(commands);
      }
      currentCommand.addAll(commands);
    }
    return currentCommand;
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

  public void setView(MainView d){commandFactory.setView(d);}
}
