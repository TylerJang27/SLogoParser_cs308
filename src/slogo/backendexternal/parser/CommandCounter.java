package slogo.backendexternal.parser;

import java.util.HashMap;
import java.util.Map;

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
    counts.put("ClearScreen", 1);
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
  }
}
