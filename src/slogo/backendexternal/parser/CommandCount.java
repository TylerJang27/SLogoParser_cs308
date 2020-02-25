package slogo.backendexternal.parser;

import java.util.HashMap;
import java.util.Map;

public class CommandCount {
  private Map<String, Integer> counts;

  public CommandCount(){
    counts = new HashMap<String, Integer>();
    fillCounts();
  }

  public int getCount(String key){
    return counts.get(key);
  }

  private void fillCounts(){
    counts.put("Forward", 1);
    counts.put("Home", 0);
    counts.put("Backward", 1);
  }
}
