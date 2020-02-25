package slogo.backendexternal.parser;

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


  public Parser(){ this("English");}

  public Parser(String language){
    myCommands = new HashMap<String, List<String>>();
    newCommands = new ArrayList<Command>();
    commandHistory = new ArrayList<Command>();
    setLanguage(language);
  }

  public void parseLine(String line){
    List<Map<String, List<Double>>> completeCommands = new ArrayList<Map<String, List<Double>>>();
    List<Map<String, List<Double>>> unfinishedCommands = new ArrayList<Map<String, List<Double>>>();
    Map<String, List<Double>> currentCommand = new HashMap<String, List<Double>>();
    String currentKey = "";
    int countInputs = 1;
    String[] inputs = line.split(" ");
    for(String input : inputs){
      if(Input.Command.matches(input)){
        if(currentCommand.size() > 0){
          unfinishedCommands.add(currentCommand);
          currentCommand.clear();
        }
        currentKey = input;
        currentCommand.put(input, new ArrayList<Double>());
      }
      if(Input.Constant.matches(input)){
        if(currentCommand.size() > 0){
          currentCommand.put(currentKey, new ArrayList(Arrays.asList(Integer.parseInt(input)/1.0)));
          completeCommands.add(currentCommand);
        }
      }
    }
    for(int i = 0; i < completeCommands.size(); i++){
      System.out.println(i);
      System.out.println(completeCommands.get(i));
      newCommands.add(CommandFactory.makeCommand(completeCommands.get(i), myCommands));
    }
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
