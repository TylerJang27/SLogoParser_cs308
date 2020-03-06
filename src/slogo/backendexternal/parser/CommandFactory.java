package slogo.backendexternal.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import slogo.backendexternal.backendexceptions.InvalidArgumentException;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.controlcommands.Constant;
import slogo.view.Display;

public class CommandFactory {

  private static final double X_MAX = 500; //TODO: TAKE FROM THE FRONT-END
  private static final double Y_MAX = 250;

  private String currentMode;
  private Map<String, String> myCommands = new HashMap<>();
  private List<String> myMovementCommands;
  private Map<String, Integer> counts = new HashMap<>();
  private Map<String, Integer> myControlCommands = new HashMap<>();
  private Display myDisplay;
  private List<String> mySupplierCommands;
  private List<String> myRunnableCommands;
  private List<String> myConsumerCommands;


  public CommandFactory(Map<String, List<String>> commands){
    currentMode = "toroidal";
    //myCounts = new CommandCounter();
    fillCounts();
    setGeneralCommands();
    setMovementCommands();
    setControlCommands();
    setSRCCommands();
  }


  private void setControlCommands(){
    var resources2 = ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "ControlCommand");
    for(String key:resources2.keySet()){
      myControlCommands.put(key, Integer.parseInt(resources2.getString(key)));
    }
  }

  private void setGeneralCommands(){
    var resources = ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "CommandFactory");
    for(String key:resources.keySet()){
      myCommands.put(key, resources.getString(key));
    }
  }
  private void setMovementCommands(){
    myMovementCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "MovementCommand").getKeys());
  }

  private void setSRCCommands(){
    myConsumerCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "ConsumerCommand").getKeys());
    myRunnableCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "RunnableCommand").getKeys());
    mySupplierCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "SupplierCommand").getKeys());
  }

  public void setDisplay(Display display){
    myDisplay = display;
  }




  public Command makeCommand(String command, Stack<Command> previous, Stack<List<Command>> listCommands, Map<String, List<String>> myCommands) throws InvalidArgumentException{
    String formalCommand = validateCommand(command, myCommands);
    List<Command> commands = new ArrayList<>();
    System.out.println(previous.size());
    int count = getCount(formalCommand);

    if(previous.size() < count){
      throw new InvalidArgumentException(String.format("Incorrect number of arguments for command %s", command));
    }

    while(commands.size() < count){
      if(previous.size() > 0){
        commands.add(previous.pop());
      }
    }
    return buildCommand(formalCommand, commands, listCommands);
  }

  //TODO Replace the following if else tree with reflection - will make much cleaner

  public Command buildCommand(String key, List<Command> commands, Stack<List<Command>> listCommands) throws InvalidCommandException {
    try {
      List<Object> obj = new ArrayList<>();

      for (int i = 0; i < getCount(key); i++) obj.add(commands.get(i));
      if (myMovementCommands.contains(key)) obj.addAll(new ArrayList<>(Arrays.asList(X_MAX, Y_MAX, currentMode)));
      if (myControlCommands.keySet().contains(key)) for (int i = 0; i < myControlCommands.get(key); i++) obj.add(listCommands.pop());


      if(myRunnableCommands.contains(key)) {
        Runnable z = () -> {
          try {
            this.getClass().getDeclaredMethod(key);
          } catch (NoSuchMethodException e) {
            throw new InvalidCommandException("Command could not be found.");
          }
        };
        obj.add(z);
      }

//      if(mySupplierCommands.contains(key)) {
//        Supplier<Integer> z = ()->this.getClass().getDeclaredMethod(key);
//        obj.add(z);
//      }


      Object[] objArray = obj.toArray();
      Class<?> params[] = findParameter(objArray);

      for(Object o:objArray) System.out.println(o);
      return (Command) Class.forName(myCommands.get(key)).getDeclaredConstructor(params).newInstance(objArray);

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new InvalidCommandException("Command could not be found.");
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
      } else if (objArray[i] instanceof Supplier) {
        params[i] = Supplier.class;
      }else if (objArray[i] instanceof Runnable) {
        params[i] = Runnable.class;
      }
    }
    return params;
  }

  private void ClearScreen(){
    Runnable r = ()-> {
      try {
        myDisplay.getMainView().getTurtle().getClass().getDeclaredMethod("clearScreen");
      } catch (NoSuchMethodException e) {
        throw new InvalidCommandException("Command could not be found.");
      }
    };
    r.run();
  }

//  private Integer PenColor(){
//    Supplier<Integer> r = myDisplay.getMainView().getToolBar().getClass()::getPenColor;
//    return r.get();
//  }
//
//  private Integer Shape(){
//    Supplier<Integer> r = myDisplay.getMainView().getToolBar().getClass()::getShape;
//    return r.get();
//  }
}
