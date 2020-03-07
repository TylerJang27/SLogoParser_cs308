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
import slogo.frontendexternal.TurtleView;
import slogo.view.MainView;

public class CommandFactory {

  private static final double X_MAX = 500; //TODO: TAKE FROM THE FRONT-END
  private static final double Y_MAX = 250;

  private String currentMode;
  private Map<String, String> myCommands = new HashMap<>();
  private List<String> myMovementCommands;
  private Map<String, Integer> counts = new HashMap<>();
  private Map<String, Integer> myControlCommands = new HashMap<>();
  private MainView myMainView;
  private List<String> mySupplierCommands;
  private List<String> myRunnableCommands;
  private Map<String, Integer> myConsumerCommands = new HashMap<>();


  public CommandFactory(Map<String, List<String>> commands){
    currentMode = "Toroidal";
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
    var resources = ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "ConsumerCommand");
    for(String key:resources.keySet()){
      myConsumerCommands.put(key, Integer.parseInt(resources.getString(key)));
    }
    myRunnableCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "RunnableCommand").getKeys());
    mySupplierCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "SupplierCommand").getKeys());
  }

  public void setView(MainView mv){
    myMainView = mv;
  }




  public Command makeCommand(String command, Stack<Command> previous, Stack<List<Command>> listCommands, Map<String, List<String>> myCommands) throws InvalidArgumentException{
    String formalCommand = validateCommand(command, myCommands);

    List<Command> commands = new ArrayList<>();
    int count = getCount(formalCommand);

    if(previous.size() + listCommands.size() < count){ //TODO: TYLER EDITED
      System.out.println((previous.size() + listCommands.size()) + " vs " + count);
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





  public Command buildCommand(String key, List<Command> commands, Stack<List<Command>> listCommands) throws InvalidCommandException {
    try {
      List<Object> obj = new ArrayList<>();

      for (int i = 0; i < getCount(key) && commands.size() > 0; i++) {
        obj.add(commands.get(i));
      }
      if (myMovementCommands.contains(key)) {obj.addAll(new ArrayList<>(Arrays.asList(X_MAX, Y_MAX, currentMode)));}
      if (myControlCommands.keySet().contains(key)) {for (int i = 0; i < myControlCommands.get(key); i++) {obj.add(listCommands.pop());}}

      System.out.println(key + "??");
      runnableAdd(key, obj);
      consumerAdd(key, obj);
      supplierAdd(key, obj);
      System.out.println(key + "???");
      if (key.equals("Tell")) {
        System.out.println("Telling");
        //TODO: TYLER FIX HERE
        if (listCommands.isEmpty()) {
          obj.clear();
          obj.add(List.of(commands.get(0)));
        } else {
          obj.add(listCommands.pop());
        }
      }

      //System.out.println(key);

      Object[] objArray = obj.toArray();
      Class<?> params[] = findParameter(objArray);
      for(Class<?> o: params) System.out.println(o);
      return (Command) Class.forName(myCommands.get(key)).getDeclaredConstructor(params).newInstance(objArray);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      System.out.println("sth happened");
      throw new InvalidCommandException("Command could not be found.");
    }
  }

  private void runnableAdd(String key, List<Object> obj){
    //System.out.println(key + "runnableAdding");
    if(myRunnableCommands.contains(key)) {
      Runnable z = () -> {
        try {
          this.getClass().getDeclaredMethod(key).invoke(this);
        } catch (NoSuchMethodException|InvocationTargetException | IllegalAccessException e) {
          throw new InvalidCommandException("Command could not be found.");
        }
      };
      obj.add(z);
    }
  }

  private void consumerAdd(String key, List<Object> obj){
    if(myConsumerCommands.keySet().contains(key)){
      //System.out.println(key);
//      if(myConsumerCommands.get(key)!=1) ;
//      Class<?> p[] = new Class<?>[myConsumerCommands.get(key)];
//      Arrays.fill(p, Integer.TYPE);
      if(myConsumerCommands.get(key)==1) {
        Consumer<Integer> z = index -> {
          try {
            this.getClass().getDeclaredMethod(key, Integer.TYPE).invoke(this, index);
          } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new InvalidCommandException("Command could not be found.");
          }
        };
        obj.add(z);
      }
      else{
        Consumer<int[]> z = index -> {
          try {
            this.getClass().getDeclaredMethod(key, int[].class).invoke(this, index);
          }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new InvalidCommandException("Command could not be found.");
          }
        };
        obj.add(z);
      }
    }
  }

  private void supplierAdd(String key, List<Object> obj){
    if(mySupplierCommands.contains(key)) {
      Supplier<Integer> z = ()-> {
        try {
          return (Integer) this.getClass().getDeclaredMethod(key).invoke(this);
        } catch (NoSuchMethodException|InvocationTargetException | IllegalAccessException e) {
          //System.out.println("????");
          throw new InvalidCommandException("Command could not be found.");
        }
      };
      obj.add(z);
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
    System.out.println(command + " " + counts.size());
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
      else if (objArray[i] instanceof Collection) {
        params[i] = Collection.class;
      }
    }
    return params;
  }

  private void ClearScreen(){
    myMainView.getTurtles().getMyLines().clear();
    myMainView.getTurtle().clearScreen();
    //for(TurtleView t : myMainView.getTurtles().getTurtleViews()) t.clearScreen();
  }




  private void SetBackground(int index){
    System.out.println("setBackGround");
    myMainView.getToolBar().setBackground(index);
  }
  private void SetPenColor(int index){
    System.out.println("setPenColoring");
    myMainView.getToolBar().setPenColor(index);
  }
  private void SetShape(int index){
    myMainView.getToolBar().setShape(index);
  }

  private void SetPenSize(int index){
    myMainView.getTurtle().setThickness(index);
  }

  private int GetPenColor(){
    int i = myMainView.getToolBar().getPenColor();
    System.out.println(i);
    return i;
  }

  private int GetShape(){
    return myMainView.getToolBar().getTurtleShape();
  }

  private void SetPalette(int[] index){
    System.out.println("reached here?");
    myMainView.getToolBar().setPalette(index);
  }
}


