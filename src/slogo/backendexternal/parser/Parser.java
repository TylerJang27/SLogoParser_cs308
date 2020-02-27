package slogo.backendexternal.parser;

import java.util.Iterator;
import java.util.Stack;
import slogo.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Parser {
  private static final String RESOURCES_PACKAGE = Parser.class.getPackageName() + ".resources.";
  private List<String> commandHistory;
  private List<slogo.commands.Command> newCommands;
  private Map<String, List<String>> myCommands;
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;
  private FunctionFactory functionFactory;
  private Stack<Command> currentCommands;
  private Stack<String> currentComponents;

  public Parser(){ this("English");}

  public Parser(String language){
    myCommands = new HashMap<String, List<String>>();
    newCommands = new ArrayList<Command>();
    commandHistory = new ArrayList<String>();
    commandFactory = new CommandFactory();
    variableFactory = new VariableFactory();
    functionFactory = new FunctionFactory();
    currentCommands = new Stack<Command>();
    currentComponents = new Stack<>();
    setLanguage(language);
  }

  public void parseLine(String line){
    commandHistory.add(line);

    String[] inputs = line.split(" ");

    for(String input : inputs){
      currentComponents.push(input);
    }

    parseComponents(currentComponents);

    while(currentCommands.size() > 0){
      newCommands.add(currentCommands.pop());
    }

  }

  public List<slogo.commands.Command> sendCommands(){
    List<slogo.commands.Command> toSend = new ArrayList<>(newCommands);
    newCommands.clear();
    return toSend;
  }

  private void parseComponents(Stack<String> components){
    while(components.size() > 0){
      Stack<Command> commands = new Stack<>();
      String current = components.pop();
      if(Input.Constant.matches(current)){
        commands.add(commandFactory.makeConstant(current));
      }
      else if(Input.Make.matches(current)){
        if(currentCommands.size() > 0){
          commands.add(variableFactory.makeVariable(currentCommands.pop()));
        }
      }
      else if(Input.Set.matches(current)){
        if(currentCommands.size() > 0){
          commands.add(variableFactory.setVariable(currentCommands.pop()));
        }
      }
      else if(Input.Command.matches(current)){
        commands.add(commandFactory.makeCommand(current, currentCommands, myCommands));
      }
      else if(Input.Variable.matches(current)){
        if(variableFactory.handleVariable(current)){
          commands.add(variableFactory.getVariable(current));
        }
      }
      else if(Input.ListEnd.matches(current)){
        if(checkFunction(components)){
          commands.add(functionFactory.handleFunction(components));
        }
        else{
          continue;
        }
      }
      currentCommands.addAll(commands);
    }
  }

  private boolean checkFunction(Stack<String> components) {
    Iterator<String> iter = components.iterator();
    while(iter.hasNext()){
      String current = iter.next();
      if(Input.TO.matches(current)){
        return true;
      }
    }
    return false;
  }

  public List<String> getCommandHistory(){
    return commandHistory;
  }

  private void setLanguage(String lang){
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + lang);
    for (String key : Collections.list(resources.getKeys())) {
      String translation = resources.getString(key);
      myCommands.put(key, Arrays.asList(translation.split("\\|")));
    }
  }

}
