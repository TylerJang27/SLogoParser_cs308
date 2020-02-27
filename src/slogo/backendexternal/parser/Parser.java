package slogo.backendexternal.parser;

import java.security.spec.RSAOtherPrimeInfo;
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
    setLanguage(language);
    newCommands = new ArrayList<Command>();
    commandHistory = new ArrayList<String>();
    commandFactory = new CommandFactory();
    variableFactory = new VariableFactory();
    functionFactory = new FunctionFactory(myCommands);
    currentCommands = new Stack<Command>();
    currentComponents = new Stack<>();
  }


  public void parseLine(String line){
    commandHistory.add(line);

    String[] inputs = line.split(" ");

    for(String input : inputs){
      currentComponents.push(input);
    }

    currentCommands.addAll(parseComponents(currentComponents));

    while(currentCommands.size() > 0){
      newCommands.add(currentCommands.pop());
    }

  }

  public List<slogo.commands.Command> sendCommands(){
    List<slogo.commands.Command> toSend = new ArrayList<>(newCommands);
    newCommands.clear();
    return toSend;
  }

  public Stack<Command> parseComponents(Stack<String> components){

    Stack<Command> currentCommand = new Stack<>();


    while(components.size() > 0){
      Stack<Command> commands = new Stack<>();

      String current = components.pop();

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
        if(functionFactory.hasFunction(current)){
          commands.add(functionFactory.getFunction(current));
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

      else if(Input.ListEnd.matches(current)){
        if(checkFunction(components)){
          System.out.println("okay we got a function");
          commands.add(functionFactory.handleFunction(components));
        }
        System.out.println("exiting functoin making jawn");
      }

      currentCommand.addAll(commands);
    }

    return currentCommand;
  }

  private boolean checkFunction(Stack<String> components) {
    System.out.println("Checking function");
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

  public List<String> getCommandHistory(){
    return commandHistory;
  }

  public void setLanguage(String lang){
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + lang);
    for (String key : Collections.list(resources.getKeys())) {
      String translation = resources.getString(key);
      myCommands.put(key, Arrays.asList(translation.split("\\|")));
    }
  }

  public String getVariableString() {
    return variableFactory.getVariableString();
  }
}
