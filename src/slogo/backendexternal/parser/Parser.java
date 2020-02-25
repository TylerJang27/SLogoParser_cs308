package slogo.backendexternal.parser;

import java.util.Stack;
import slogo.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import slogo.commands.controlcommands.Constant;

public class Parser {
  private static final String RESOURCES_PACKAGE = Parser.class.getPackageName() + ".resources.";
  private List<slogo.commands.Command> commandHistory;
  private List<slogo.commands.Command> newCommands;
  private Map<String, List<String>> myCommands;
  private CommandFactory commandFactory;
  private Stack<Command> currentCommands;

  public Parser(){ this("English");}

  public Parser(String language){
    myCommands = new HashMap<String, List<String>>();
    newCommands = new ArrayList<Command>();
    commandHistory = new ArrayList<Command>();
    commandFactory = new CommandFactory();
    currentCommands = new Stack<Command>();
    setLanguage(language);
  }

  public void parseLine(String line){
    Stack<String> components = new Stack<>();
    Stack<Double> constants = new Stack<>();
    String[] inputs = line.split(" ");

    for(String input : inputs){
      components.push(input);
    }

    while(components.size() > 0){
      Stack<Command> commands = new Stack<>();
      String current = components.pop();
      if(Input.Constant.matches(current)){
        commands.add(new Constant((double) Integer.parseInt(current)));
      }
      else if(Input.Command.matches(current)){
        commands.add(commandFactory.makeCommand(current, currentCommands, myCommands));
      }
      else if(Input.Variable.matches(current)){
        commands.add(handleVariable(current, constants, currentCommands));
      }
      else if(Input.ListStart.matches(current)){
        commands.addAll(handleList(components));
      }

      else if(Input.GroupStart.matches(current)){}

      // DO NOTHING

      else if(Input.Comment.matches(current)){ continue;}
      else if(Input.Whitespace.matches(current)){ continue;}
      else if(Input.GroupEnd.matches(current)){ continue;}
      else if(Input.Newline.matches(current)){ continue;}

      System.out.println(current);
      currentCommands.addAll(commands);
      System.out.println(currentCommands.size());
    }
    while(currentCommands.size() > 0){
      newCommands.add(currentCommands.pop());
    }
  }

  private List<Command> handleList(Stack<String> components) {
    List<Command> commands = new ArrayList<>();
    while(components.size() > 0){
      String current = components.pop();
      if(Input.ListEnd.matches(current)){
        break;
      }
    }
    return commands;
  }

  private Command handleVariable(String variable, Stack<Double> constants, Stack<Command> previousCommands) {
    Command command = null;
    return command;
  }

  public List<slogo.commands.Command> sendCommands(){
    commandHistory.addAll(newCommands);
    List<slogo.commands.Command> toSend = new ArrayList<>(newCommands);
    newCommands.clear();
    return toSend;
  }

  private void setLanguage(String lang){
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + lang);
    System.out.println(RESOURCES_PACKAGE + lang);
    for (String key : Collections.list(resources.getKeys())) {
      String translation = resources.getString(key);
      myCommands.put(key, Arrays.asList(translation.split("\\|")));
    }
  }
}
