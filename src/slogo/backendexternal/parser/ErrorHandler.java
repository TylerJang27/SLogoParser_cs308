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

  private int getDistance(String input, String command){
    if (input == null || command == null) {
      throw new IllegalArgumentException("Strings must not be null");
    }

    int inputLen = input.length();
    int commandLen = command.length();

    if (inputLen == 0) {
      return commandLen;
    } else if (commandLen == 0) {
      return inputLen;
    }

    if (inputLen > commandLen) {
      // swap the input strings to consume less memory
      String tmp = input;
      input = command;
      command = tmp;
      inputLen = commandLen;
      commandLen = command.length();
    }

    int p[] = new int[inputLen+1]; //'previous' cost array, horizontally
    int d[] = new int[inputLen+1]; // cost array, horizontally
    int _d[]; //placeholder to assist in swapping p and d

    // indexes into strings s and t
    int i; // iterates through s
    int j; // iterates through t

    char t_j; // jth character of t

    int cost; // cost

    for (i = 0; i<=inputLen; i++) {
      p[i] = i;
    }

    for (j = 1; j<=commandLen; j++) {
      t_j = command.charAt(j-1);
      d[0] = j;
      for (i=1; i<=inputLen; i++) {
        cost = input.charAt(i-1)==t_j ? 0 : 1;
        // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
        d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
      }
      // copy current distance counts to 'previous row' distance counts
      _d = p;
      p = d;
      d = _d;
    }
    // our last action in the above loop was to switch d and p, so p now
    // actually has the most recent cost counts
    return p[inputLen];
  }
}
