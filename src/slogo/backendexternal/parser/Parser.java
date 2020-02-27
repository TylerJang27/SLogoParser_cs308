package slogo.backendexternal.parser;

<<<<<<< HEAD
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Iterator;
import java.util.Stack;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;

=======
>>>>>>> origin/master
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
<<<<<<< HEAD
=======
import java.util.regex.Pattern;
>>>>>>> origin/master

public class Parser {
  private static final String DEFAULT_ERROR_MESSAGE = "The last command could not be recognized. "
      + "Please check spelling and try again. Reason:";
  private static final String RESOURCES_PACKAGE = Parser.class.getPackageName() + ".resources.";
<<<<<<< HEAD
  private List<String> commandHistory;
  private List<slogo.commands.Command> newCommands;
  private Map<String, List<String>> myCommands;
  private CommandFactory commandFactory;
  private VariableFactory variableFactory;
  private FunctionFactory functionFactory;
  private Stack<Command> currentCommands;
  private Stack<String> currentComponents;
=======
  private static final ResourceBundle COMMAND_SYNTAX =
      ResourceBundle.getBundle(Parser.class.getPackageName() + ".resources.Syntax.properties");

  private List<slogo.commands.Command> commandHistory;
  private List<slogo.commands.Command> newCommands;
  private Map<String, List<String>> myCommands;

>>>>>>> origin/master

  public Parser(){ this("English");}

  public Parser(String language){
<<<<<<< HEAD
    myCommands = new HashMap<String, List<String>>();
    setLanguage(language);
    newCommands = new ArrayList<Command>();
    commandHistory = new ArrayList<String>();
    commandFactory = new CommandFactory(myCommands);
    variableFactory = new VariableFactory();
    functionFactory = new FunctionFactory(myCommands);
    currentCommands = new Stack<Command>();
    currentComponents = new Stack<>();
=======
    setLanguage(language);
>>>>>>> origin/master
  }


  public void parseLine(String line){
<<<<<<< HEAD
    commandHistory.add(line);

=======
    List<Map<String, List<Double>>> completeCommands = new ArrayList<Map<String, List<Double>>>();
    List<Map<String, List<Double>>> unfinishedCommands = new ArrayList<Map<String, List<Double>>>();
    Map<String, List<Double>> currentCommand = new HashMap<String, List<Double>>();
    String currentKey = "";
    int countInputs = 1;
>>>>>>> origin/master
    String[] inputs = line.split(" ");
    for(String input : inputs){
<<<<<<< HEAD
      currentComponents.push(input);
    }

    currentCommands.addAll(parseComponents(currentComponents));

    while(currentCommands.size() > 0){
      newCommands.add(currentCommands.pop());
=======
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
        }
      }
    }
    for(int i = 0; i < completeCommands.size(); i++){
      System.out.println(i);
      System.out.println(completeCommands.get(i));
      newCommands.add(CommandFactory.makeCommand(completeCommands.get(i), myCommands));
>>>>>>> origin/master
    }

  }

  public List<slogo.commands.Command> sendCommands(){
<<<<<<< HEAD
    List<slogo.commands.Command> toSend = new ArrayList<>(newCommands);
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
      }

      if(inList) {
        currentCommand.addAll(commands);
        currentList.addAll(commands);
      }
      else{
        currentCommand.addAll(commands);
      }
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

  public List<String> getCommandHistory(){
    return commandHistory;
=======
    commandHistory.addAll(newCommands);
    List<slogo.commands.Command> toSend = newCommands;
    newCommands.clear();
    return toSend;
>>>>>>> origin/master
  }

  public void setLanguage(String lang){
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + lang);
    for (String key : Collections.list(resources.getKeys())) {
      String translation = resources.getString(key);
      myCommands.put(key, Arrays.asList(translation.split("|")));
    }
  }

  public void addError(String message){
    commandHistory.add(DEFAULT_ERROR_MESSAGE);
    commandHistory.add(message);
  }

  public String getVariableString() {
    return variableFactory.getVariableString();
  }
}
