package slogo.backendexternal.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import slogo.backendexternal.backendexceptions.InvalidArgumentException;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.controlcommands.Constant;

public class CommandFactory {

  private static final double X_MAX = 500; //TODO: TAKE FROM THE FRONT-END
  private static final double Y_MAX = 250;

  private String currentMode;
  private Map<String, String> myCommands = new HashMap<>();
  private List<String> myMovementCommands;
  private Map<String, Integer> counts;

  public CommandFactory(Map<String, List<String>> commands){
    currentMode = "Toroidal";
    var resources = ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "CommandFactory");
    for(String key:resources.keySet()){
      myCommands.put(key, resources.getString(key));
    }
    myMovementCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "MovementCommand").getKeys());
    fillCounts();
  }

  public Command makeCommand(String command, Stack<Command> previous, Stack<List<Command>> listCommands, Map<String, List<String>> myCommands) throws InvalidArgumentException{
    String formalCommand = validateCommand(command, myCommands);
    List<Command> commands = new ArrayList<>();
    int count = getCount(formalCommand);
    if(previous.size() + listCommands.size() < count){ //TODO: TYLER EDITED
      throw new InvalidArgumentException(String.format("Incorrect number of arguments for command %s", command));
    }
    while(commands.size() < count){
      if(previous.size() > 0){
        commands.add(previous.pop());
      }
      else{
        break;
      }
    }
    return buildCommand(formalCommand, commands, listCommands);
  }

  //TODO Replace the following if else tree with reflection - will make much cleaner

  public Command buildCommand(String key, List<Command> commands, Stack<List<Command>> listCommands) throws InvalidCommandException{
    try {
      Class<?> c = Class.forName(myCommands.get(key));
      List<Object> obj = new ArrayList<>();
      for(int i = 0; i<getCount(key) && !commands.isEmpty(); i++) obj.add(commands.get(i));

      if(myMovementCommands.contains(key)){
        obj.add(X_MAX);
        obj.add(Y_MAX);
        obj.add(currentMode);
      }

      if(key.equals("Repeat")||key.equals("If")||key.equals("IfElse")){
        obj.add(listCommands.pop());
      }

      if(key.equals("IfElse")) obj.add(listCommands.pop());

      if (key.equals("Tell")) {
        if (listCommands.isEmpty()) {
          obj.clear();
          obj.add(List.of(commands.get(0)));
        } else {
          obj.add(listCommands.pop());
        }
      }
      Object[] objArray = obj.toArray();
      Class<?> params[] = findParameter(objArray);
      return (Command)c.getDeclaredConstructor(params).newInstance(objArray);
    } catch (InvalidCommandException | NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new InvalidCommandException(e, "Command could not be found.");
    }
  }

  public Command makeConstant(String current) {
    return new Constant(Integer.parseInt(current));
  }

  public void setMode(String mode){
    currentMode = mode;
  }

  private String validateCommand(String current, Map<String, List<String>> myCommands) throws InvalidCommandException {
    for (String key : myCommands.keySet()) {
      if (myCommands.get(key).contains(current)) {
        return key;
      }
    }
    throw new InvalidCommandException(current);
  }

  private void fillCounts(){
    counts = new HashMap<>();
    var resources = ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "CommandCounter");
    for(String key:resources.keySet()){
      counts.put(key, Integer.parseInt(resources.getString(key)));
    }
  }

  private int getCount(String command){
    return counts.get(command);
  }

  private Class<?>[] findParameter(Object[] objArray){
    Class<?> params[] = new Class[objArray.length];
    for (int i = 0; i < objArray.length; i++) {
      if (objArray[i] instanceof Double) {
        params[i] = Double.TYPE;
      } else if (objArray[i] instanceof String) {
        params[i] = String.class;
      } else if (objArray[i] instanceof Command) {
        params[i] = Command.class;
      } else if (objArray[i] instanceof List) {
        params[i] = List.class;
      } else if (objArray[i] instanceof Consumer) {
        params[i] = Consumer.class;
      }
    }
    return params;
  }
}
