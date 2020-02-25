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
import java.util.regex.Pattern;

public class Parser {
  private static final String RESOURCES_PACKAGE = Parser.class.getPackageName() + ".resources.";
  private List<slogo.commands.Command> commandHistory;
  private List<slogo.commands.Command> newCommands;
  private Map<String, List<String>> myCommands;
  private CommandFactory commandFactory;


  public Parser(){ this("English");}

  public Parser(String language){
    myCommands = new HashMap<String, List<String>>();
    newCommands = new ArrayList<Command>();
    commandHistory = new ArrayList<Command>();
    commandFactory = new CommandFactory();
    setLanguage(language);
  }

  public void parseLine(String line){
    Stack<String> components = new Stack<>();
    Stack<Command> commands = new Stack<>();
    Stack<Double> constants = new Stack<>();
    String currentKey = "";
    int countInputs = 1;
    String[] inputs = line.split(" ");
    for(String input : inputs){
      components.push(input);
    }
    while(components.size() > 0){
      String current = components.pop();
      if(Input.Constant.matches(current)){
        constants.push((double) Integer.parseInt(current));
      }
      if(Input.Command.matches(current)){
        commands.addAll(handleCommands(current, constants, commands));
      }
      if(Input.Variable.matches(current)){
        commands.addAll(handleVariables(current, constants, commands));
      }
      if(Input.ListStart.matches(current)){
        commands.addAll(handleList(components));
      }
      if(Input.GroupStart.matches(current)){
        commands.addAll(handleGroup(components));
      }
      // DO NOTHING
      if(Input.Comment.matches(current)){ continue;}
      if(Input.Whitespace.matches(current)){ continue;}
      if(Input.GroupEnd.matches(current)){ continue;}
      if(Input.Newline.matches(current)){ continue;}

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

  private List<Command> handleGroup(Stack<String> components) {
    List<Command> commands = new ArrayList<>();
    while(components.size() > 0){
      String current = components.pop();

      if(Input.GroupEnd.matches(current)){
        break;
      }

    }
    return commands;
  }

  private List<Command> handleCommands(String command, Stack<Double> constants, Stack<Command> previousCommands) {
    List<Command> commands = new ArrayList<>();

    return commands;
  }

  private List<Command> handleVariables(String variable, Stack<Double> constants, Stack<Command> previousCommands) {
    List<Command> commands = new ArrayList<>();

    return commands;
  }



  public List<slogo.commands.Command> sendCommands(){
    commandHistory.addAll(newCommands);
    List<slogo.commands.Command> toSend = newCommands;
    newCommands.clear();
    return toSend;
  }

  private void setLanguage(String lang){
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + lang);
    System.out.println(RESOURCES_PACKAGE + lang);
    for (String key : Collections.list(resources.getKeys())) {
      String translation = resources.getString(key);
      myCommands.put(key, Arrays.asList(translation.split("|")));
    }
  }

  public void printCurrentCommands(){
    for(Command c : newCommands){
      System.out.println(c);
    }
  }
}
