package slogo.backendexternal.parser;

import java.util.HashMap;
import java.util.Map;

//TODO Refactor this functionality into a different place, this class doesn't do much right now

public class CommandCounter {
  private Map<String, Integer> counts;

  public CommandCounter(){
    counts = new HashMap<>();
    fillCounts();
  }

  public int getCount(String key){
    return counts.get(key);
  }

  private void fillCounts(){
    counts.put("Backward", 1);
    counts.put("ClearScreen", 0);
    counts.put("Forward", 1);
    counts.put("HideTurtle", 0);
    counts.put("Home", 0);
    counts.put("Left", 1);
    counts.put("PenUp", 0);
    counts.put("PenDown", 0);
    counts.put("Right", 1);
    counts.put("SetHeading", 1);
    counts.put("SetPosition", 2);
    counts.put("SetTowards", 2);
    counts.put("ShowTurtle", 0);
    counts.put("Heading", 0);
    counts.put("IsPenDown",0);
    counts.put("IsShowing",0);
    counts.put("XCoordinate", 0);
    counts.put("YCoordinate",0);
    counts.put("ArcTangent",1);
    counts.put("Cosine",1);
    counts.put("Difference", 2);
    counts.put("Minus", 2);
    counts.put("NaturalLog", 1);
    counts.put("Pi", 0);
    counts.put("Power", 2);
    counts.put("Product", 2);
    counts.put("Quotient", 2);
    counts.put("Rand", 1);
    counts.put("Remainder", 2);
    counts.put("Sine", 1);
    counts.put("Sum", 2);
    counts.put("Tangent", 1);
    counts.put("And", 2);
    counts.put("Equal", 2);
    counts.put("GreaterThan", 2);
    counts.put("LessThan", 2);
    counts.put("Not", 1);
    counts.put("NotEqual", 2);
    counts.put("Or", 2);
    counts.put("Repeat", 1);
    counts.put("If", 1);
    counts.put("IfElse", 1);
    counts.put("DoTimes", 2);
    counts.put("For", 4);
  }
}
