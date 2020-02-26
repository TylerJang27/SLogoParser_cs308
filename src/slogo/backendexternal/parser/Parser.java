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
import slogo.commands.controlcommands.Function;
import slogo.commands.controlcommands.MakeVariable;
import slogo.commands.controlcommands.Variable;

public class Parser {
  private static final String RESOURCES_PACKAGE = Parser.class.getPackageName() + ".resources.";
  private List<slogo.commands.Command> commandHistory;
  private List<slogo.commands.Command> newCommands;
  private Map<String, List<String>> myCommands;
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;
  private FunctionFactory functionFactory;
  private Stack<Command> currentCommands;

  public Parser(){ this("English");}

  public Parser(String language){
    myCommands = new HashMap<String, List<String>>();
    newCommands = new ArrayList<Command>();
    commandHistory = new ArrayList<Command>();
    commandFactory = new CommandFactory();
    variableFactory = new VariableFactory();
    functionFactory = new FunctionFactory();
    currentCommands = new Stack<Command>();
    setLanguage(language);
  }

  public void parseLine(String line){
    String[] inputs = line.split(" ");
    Stack<String> components = new Stack<>();
    for(String input : inputs){
      components.push(input);
    }
    parseComponents(components);
    while(currentCommands.size() > 0){
      newCommands.add(currentCommands.pop());
    }
  }

  public List<slogo.commands.Command> sendCommands(){
    commandHistory.addAll(newCommands);
    List<slogo.commands.Command> toSend = new ArrayList<>(newCommands);
    newCommands.clear();
    return toSend;
  }

  public void parseComponents(Stack<String> components){
    while(components.size() > 0){
      Stack<Command> commands = new Stack<>();
      String current = components.pop();

      if(Input.Constant.matches(current)){
        commands.add(new Constant((double) Integer.parseInt(current)));
      }
      else if(Input.Make.matches(current)){
        if(currentCommands.size() > 0){
          variableFactory.makeVariable(current, currentCommands.pop());
        }
      }
      else if(Input.Set.matches(current)){
        if(currentCommands.size() > 0){
          variableFactory.setVariable(current, currentCommands.pop());
        }
      }
      else if(Input.Command.matches(current)){
        commands.add(commandFactory.makeCommand(current, currentCommands, myCommands));
      }
      else if(Input.Variable.matches(current)){
        commands.add(variableFactory.getVariable(current));
      }
      else if(Input.ListEnd.matches(current)){
        commands.add(handleList(components));
      }

      // DO NOTHING
      else if(Input.GroupStart.matches(current)){ continue;}
      else if(Input.Comment.matches(current)){ continue;}
      else if(Input.Whitespace.matches(current)){ continue;}
      else if(Input.GroupEnd.matches(current)){ continue;}
      else if(Input.Newline.matches(current)){ continue;}

      currentCommands.addAll(commands);
    }
  }

  public Command handleList(Stack<String> components) {
    Command command = null;
    while(components.size() > 0){
      String current = components.pop();

      if(Input.ListStart.matches(current)){
        break;
      }

      Stack<Command> commands = new Stack<>();

      if(Input.Constant.matches(current)){
        commands.add(new Constant((double) Integer.parseInt(current)));
      }
      else if(Input.Make.matches(current)){
        if(currentCommands.size() > 0){
          variableFactory.makeVariable(current, currentCommands.pop());
        }
      }
      else if(Input.Set.matches(current)){
        if(currentCommands.size() > 0){
          variableFactory.setVariable(current, currentCommands.pop());
        }
      }
      else if(Input.Command.matches(current)){
        commands.add(commandFactory.makeCommand(current, currentCommands, myCommands));
      }
      else if(Input.Variable.matches(current)){
        commands.add(variableFactory.getVariable(current));
      }
      else if(Input.ListEnd.matches(current)){
        commands.add(handleList(components));
      }

    }

    return command;
  }

  private void setLanguage(String lang){
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + lang);
    for (String key : Collections.list(resources.getKeys())) {
      String translation = resources.getString(key);
      myCommands.put(key, Arrays.asList(translation.split("\\|")));
    }
  }
}
