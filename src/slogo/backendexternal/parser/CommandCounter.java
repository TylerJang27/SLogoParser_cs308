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
    counts.put("Forward", 1);
    counts.put("Home", 0);
    counts.put("Backward", 1);
    counts.put("Right", 1);
    counts.put("ClearScreen", 1);
  }
}
