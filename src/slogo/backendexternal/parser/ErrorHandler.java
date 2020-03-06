package slogo.backendexternal.parser;

import java.util.List;
import java.util.Map;

public class ErrorHandler {
  private static final String PROMPT = "Did you mean %s? [y/n] ";
  private String lastFix;
  private String lastError;

  public ErrorHandler(){}

  public String getErrorMessage(String input, Map<String, List<String>> commands){
    String recommendation = determineCommand(input, commands);
    lastError = input;
    lastFix = recommendation;
    return promptUser(recommendation);
  }

  public String promptUser(String command){
    return String.format(PROMPT, command);
  }

  public String fixLine(String line){
    String[] inputs = line.split(" ");
    for(int i = 0; i < inputs.length; i++){
      if(inputs[i].equals(lastError)){
        inputs[i] = lastFix;
      }
    }
    return String.join(" ", inputs);
  }

  public void clear(){
    lastError = "";
    lastFix = "";
  }

  private String determineCommand(String input, Map<String, List<String>> commands){
    int minDistance = Integer.MAX_VALUE;
    String closest = "";
    for(String key : commands.keySet()){
      for(String command : commands.get(key)){
        int dist = getDistance(input, command);
        if(dist < minDistance){
          closest = command;
          minDistance = dist;
        }
      }
    }
    return closest;
  }

  //TODO: HANDLE CASE OF NO INPUT
  private int getDistance(String input, String command){
    int inputLen = input.length();
    int commandLen = command.length();

    if (inputLen == 0 || commandLen == 0) {
      return Math.max(inputLen, commandLen);
    }
    int p[] = new int[inputLen+1];
    int d[] = new int[inputLen+1];
    int _d[];
    char t_j;
    int cost;
    for (int i = 0; i<=inputLen; i++) {
      p[i] = i;
    }

    for (int j = 1; j<=commandLen; j++) {
      t_j = command.charAt(j-1);
      d[0] = j;
      for (int i=1; i<=inputLen; i++) {
        cost = input.charAt(i-1)==t_j ? 0 : 1;
        d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
      }
      _d = p;
      p = d;
      d = _d;
    }
    return p[inputLen];
  }
}
