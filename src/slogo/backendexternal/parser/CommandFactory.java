package slogo.backendexternal.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.XMLFormatter;

import slogo.backendexternal.backendexceptions.InvalidArgumentException;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.booleancommands.And;
import slogo.commands.booleancommands.Equal;
import slogo.commands.booleancommands.GreaterThan;
import slogo.commands.booleancommands.LessThan;
import slogo.commands.booleancommands.Not;
import slogo.commands.booleancommands.NotEqual;
import slogo.commands.booleancommands.Or;
import slogo.commands.controlcommands.Constant;
import slogo.commands.controlcommands.DoTimes;
import slogo.commands.controlcommands.For;
import slogo.commands.controlcommands.If;
import slogo.commands.controlcommands.IfElse;
import slogo.commands.controlcommands.Repeat;
import slogo.commands.controlcommands.Variable;
import slogo.commands.mathcommands.ArcTangent;
import slogo.commands.mathcommands.Cosine;
import slogo.commands.mathcommands.Difference;
import slogo.commands.mathcommands.Minus;
import slogo.commands.mathcommands.NaturalLog;
import slogo.commands.mathcommands.Pi;
import slogo.commands.mathcommands.Power;
import slogo.commands.mathcommands.Product;
import slogo.commands.mathcommands.Quotient;
import slogo.commands.mathcommands.Rand;
import slogo.commands.mathcommands.Remainder;
import slogo.commands.mathcommands.Sine;
import slogo.commands.mathcommands.Sum;
import slogo.commands.mathcommands.Tangent;
import slogo.commands.queriescommands.Heading;
import slogo.commands.queriescommands.IsPenDown;
import slogo.commands.queriescommands.IsShowing;
import slogo.commands.queriescommands.XCoordinate;
import slogo.commands.queriescommands.YCoordinate;
import slogo.commands.turtlecommands.Backward;
import slogo.commands.turtlecommands.ClearScreen;
import slogo.commands.turtlecommands.Forward;
import slogo.commands.turtlecommands.HideTurtle;
import slogo.commands.turtlecommands.Home;
import slogo.commands.turtlecommands.Left;
import slogo.commands.turtlecommands.PenDown;
import slogo.commands.turtlecommands.PenUp;
import slogo.commands.turtlecommands.Right;
import slogo.commands.turtlecommands.SetHeading;
import slogo.commands.turtlecommands.SetPosition;
import slogo.commands.turtlecommands.SetTowards;
import slogo.commands.turtlecommands.ShowTurtle;

public class CommandFactory {

  private static final double X_MAX = 500; //TODO: TAKE FROM THE FRONT-END
  private static final double Y_MAX = 250;

  private String currentMode;
  private CommandCounter myCounts;
  private Map<String, String> myCommands = new HashMap<>();
  private List<String> myMovementCommands;

  public CommandFactory(Map<String, List<String>> commands){
    currentMode = "toroidal";
    myCounts = new CommandCounter();
    var resources = ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "CommandFactory");
    for(String key:resources.keySet()){
      myCommands.put(key, resources.getString(key));
    }
    myMovementCommands = Collections.list(ResourceBundle.getBundle(CommandFactory.class.getPackageName() + ".resources." + "MovementCommand").getKeys());
  }

  public Command makeCommand(String command, Stack<Command> previous, Stack<List<Command>> listCommands, Map<String, List<String>> myCommands) throws InvalidArgumentException{
    String formalCommand = validateCommand(command, myCommands);
    List<Command> commands = new ArrayList<>();
    System.out.println(previous.size());
    int count = myCounts.getCount(formalCommand);

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

  public Command buildCommand(String key, List<Command> commands, Stack<List<Command>> listCommands) throws InvalidCommandException{
    try {
      List<Object> obj = new ArrayList<>();

      for(int i = 0; i<myCounts.getCount(key); i++) obj.add(commands.get(i));
      if(myMovementCommands.contains(key)) obj.add(new ArrayList<>(Arrays.asList(X_MAX, Y_MAX, currentMode)));
      if(key.equals("Repeat")||key.equals("If")||key.equals("IfElse")){
        obj.add(listCommands.pop());
      }
      if(key.equals("IfElse")) obj.add(listCommands.pop());
      Object[] objArray = obj.toArray();

      Class<?> params[] = findParameter(objArray);
      return (Command) Class.forName(myCommands.get(key)).getDeclaredConstructor(params).newInstance(objArray);

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new InvalidCommandException("Command could not be found.");
    }


//    if(key.equals("Backward")){
//      return new Backward(commands.get(0), X_MAX, Y_MAX, currentMode);
//    }
//    else if(key.equals("ClearScreen")){
//      return new ClearScreen(X_MAX, Y_MAX, currentMode);
//    }
//    else if(key.equals("Forward")){
//      return new Forward(commands.get(0), X_MAX, Y_MAX, currentMode);
//    }
//    else if(key.equals("HideTurtle")){
//      return new HideTurtle();
//    }
//    else if(key.equals("Home")){
//      return new Home(X_MAX, Y_MAX, currentMode);
//    }
//    else if(key.equals("Left")){
//      return new Left(commands.get(0));
//    }
//    else if(key.equals("PenDown")){
//      return new PenDown();
//    }
//    else if(key.equals("PenUp")){
//      return new PenUp();
//    }
//    else if(key.equals("Right")){
//      return new Right(commands.get(0));
//    }
//    else if(key.equals("SetHeading")){
//      return new SetHeading(commands.get(0));
//    }
//    else if(key.equals("SetPosition")){
//      return new SetPosition(commands.get(0), commands.get(1), X_MAX, Y_MAX, currentMode);
//    }
//    else if(key.equals("SetTowards")){
//      return new SetTowards(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("ShowTurtle")){
//      return new ShowTurtle();
//    }
//    else if(key.equals("Heading")){
//      return new Heading();
//    }
//    else if(key.equals("IsPenDown")){
//      return new IsPenDown();
//    }
//    else if(key.equals("IsShowing")){
//      return new IsShowing();
//    }
//    else if(key.equals("XCoordinate")){
//      return new XCoordinate();
//    }
//    else if(key.equals("YCoordinate")){
//      return new YCoordinate();
//    }
//    else if(key.equals("ArcTangenet")){
//      return new ArcTangent(commands.get(0));
//    }
//    else if(key.equals("Cosine")){
//      return new Cosine(commands.get(0));
//    }
//    else if(key.equals("Difference")){
//      return new Difference(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Minus")){
//      return new Minus(commands.get(0));
//    }
//    else if(key.equals("NaturalLog")){
//      return new NaturalLog(commands.get(0));
//    }
//    else if(key.equals("Pi")){
//      return new Pi();
//    }
//    else if(key.equals("Power")){
//      return new Power(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Product")){
//      return new Product(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Quotient")){
//      return new Quotient(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Rand")){
//      return new Rand(commands.get(0));
//    }
//    else if(key.equals("Remainder")){
//      return new Remainder(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Sine")){
//      return new Sine(commands.get(0));
//    }
//    else if(key.equals("Sum")){
//      return new Sum(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Tangent")){
//      return new Tangent(commands.get(0));
//    }
//    else if(key.equals("And")){
//      return new And(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Equal")){
//      return new Equal(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("GreaterThan")){
//      return new GreaterThan(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("LessThan")){
//      return new LessThan(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Not")){
//      return new Not(commands.get(0));
//    }
//    else if(key.equals("NotEqual")){
//      return new NotEqual(commands.get(0),commands.get(1));
//    }
//    else if(key.equals("Or")){
//      return new Or(commands.get(0), commands.get(1));
//    }
//    else if(key.equals("Repeat")){
//      return new Repeat(commands.get(0), listCommands.pop());
//    }
////    else if(key.equals("DoTimes")){
////      return new DoTimes(commands.get(0), commands.get(1), listCommands.pop());
////    }
////    else if(key.equals("For")){
////      return new For(commands.get(0), commands.get(1), commands.get(2), commands.get(3), listCommands.pop());
////    }
//    else if(key.equals("If")){
//      return new If(commands.get(0), listCommands.pop());
//    }
//    else if(key.equals("IfElse")){
//      return new IfElse(commands.get(0), listCommands.pop(), listCommands.pop());
//    }
//    throw new InvalidCommandException("Command could not be found.");
  }

  public Command makeConstant(String current) {
    return new Constant(Integer.parseInt(current));
  }

  //TODO: WILL IT ALWAYS BE AN INTEGER?

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
      }
    }
    return params;
  }
}
