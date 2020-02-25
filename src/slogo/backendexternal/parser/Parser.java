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
    Stack<String> commands = new Stack<String>();
    Stack<Double> constants = new Stack<Double>();
    String currentKey = "";
    int countInputs = 1;
    String[] inputs = line.split(" ");
    for(String input : inputs){
      if(Input.Command.matches(input)){
        commands.push(input);
      }
      if(Input.Constant.matches(input)){
          constants.push(new Double(Integer.parseInt(input)));
      }
    }
    newCommands = commandFactory.makeCommands(commands, constants, myCommands);
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
}
