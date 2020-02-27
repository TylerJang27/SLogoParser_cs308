package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
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

  public CommandFactory(){
    currentMode = "toroidal";
    myCounts = new CommandCounter();
  }

  public Command makeCommand(String command, Stack<Command> previous, Map<String, List<String>> myCommands) {
    String formalCommand = validateCommand(command, myCommands);
    List<Command> commands = new ArrayList<>();
    int count = myCounts.getCount(formalCommand);
    while(commands.size() < count){
      if(previous.size() > 0){
        commands.add(previous.pop());
      }
    }
    return buildCommand(formalCommand, commands);
  }

  //TODO Replace the following if else tree with reflection - will make much cleaner

  public Command buildCommand(String key, List<Command> commands){
    if(key.equals("Backward")){
      return new Backward(commands.get(0), X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("ClearScreen")){
      return new ClearScreen(X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("Forward")){
      return new Forward(commands.get(0), X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("HideTurtle")){
      return new HideTurtle();
    }
    if(key.equals("Home")){
      return new Home(X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("Left")){
      return new Left(commands.get(0));
    }
    if(key.equals("PenDown")){
      return new PenDown();
    }
    if(key.equals("PenUp")){
      return new PenUp();
    }
    if(key.equals("Right")){
      return new Right(commands.get(0));
    }
    if(key.equals("SetHeading")){
      return new SetHeading(commands.get(0));
    }
    if(key.equals("SetPosition")){
      return new SetPosition(commands.get(0), commands.get(1), X_MAX, Y_MAX, currentMode);
    }
    if(key.equals("SetTowards")){
      return new SetTowards(commands.get(0), commands.get(1));
    }
    if(key.equals("ShowTurtle")){
      return new ShowTurtle();
    }
    if(key.equals("Heading")){
      return new Heading();
    }
    if(key.equals("IsPenDown")){
      return new IsPenDown();
    }
    if(key.equals("IsShowing")){
      return new IsShowing();
    }
    else if(key.equals("XCoordinate")){
      return new XCoordinate();
    }
    else if(key.equals("YCoordinate")){
      return new YCoordinate();
    }
    else if(key.equals("ArcTangenet")){
      return new ArcTangent(commands.get(0));
    }
    else if(key.equals("Cosine")){
      return new Cosine(commands.get(0));
    }
    else if(key.equals("Difference")){
      return new Difference(commands.get(0), commands.get(1));
    }
    else if(key.equals("Minus")){
      return new Minus(commands.get(0));
    }
    else if(key.equals("NaturalLog")){
      return new NaturalLog(commands.get(0));
    }
    else if(key.equals("Pi")){
      return new Pi();
    }
    else if(key.equals("Power")){
      return new Power(commands.get(0), commands.get(1));
    }
    else if(key.equals("Product")){
      return new Product(commands.get(0), commands.get(1));
    }
    else if(key.equals("Quotient")){
      return new Quotient(commands.get(0), commands.get(1));
    }
    else if(key.equals("Rand")){
      return new Rand(commands.get(0));
    }
    else if(key.equals("Remainder")){
      return new Remainder(commands.get(0), commands.get(1));
    }
    else if(key.equals("Sine")){
      return new Sine(commands.get(0));
    }
    else if(key.equals("Sum")){
      return new Sum(commands.get(0), commands.get(1));
    }
    else if(key.equals("Tangent")){
      return new Tangent(commands.get(0));
    }
    else if(key.equals("And")){
      return new And(commands.get(0), commands.get(1));
    }
    else if(key.equals("Equal")){
      return new Equal(commands.get(0), commands.get(1));
    }
    else if(key.equals("GreaterThan")){
      return new GreaterThan(commands.get(0), commands.get(1));
    }
    else if(key.equals("LessThan")){
      return new LessThan(commands.get(0), commands.get(1));
    }
    else if(key.equals("Not")){
      return new Not(commands.get(0));
    }
    else if(key.equals("NotEqual")){
      return new NotEqual(commands.get(0),commands.get(1));
    }
    else if(key.equals("Or")){
      return new Or(commands.get(0), commands.get(1));
    }
    return null;
  }

  public Command makeConstant(String current) {
    return new Constant(Integer.parseInt(current));
  }
  //TODO: WILL IT ALWAYS BE AN INTEGER?

  public void setMode(String mode){
    currentMode = mode;
  }

  private String validateCommand(String current, Map<String, List<String>> myCommands) throws InvalidCommandException {
    try {
      for (String key : myCommands.keySet()) {
        if (myCommands.get(key).contains(current)) {
          return key;
        }
      }
    } catch (Exception e) {
      throw new InvalidCommandException(String.format("The command %s could not be found", current));
    }
    return null;
  }
}
