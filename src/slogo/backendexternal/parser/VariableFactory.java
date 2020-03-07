package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.commands.Command;
import slogo.commands.controlcommands.MakeVariable;
import slogo.commands.controlcommands.Variable;

public class VariableFactory {

  private Stack<String> variablesAdded;
  private Map<String, Variable> variableMap;

  public VariableFactory(){
    variableMap = new HashMap<>();
    variablesAdded = new Stack<>();
  }

  public MakeVariable makeVariable(Stack<Command> previous){
    previous.pop();
    String key = variablesAdded.pop();
    return new MakeVariable(variableMap.get(key), previous.pop());
  }

  public void handleVariable(String current){
    variablesAdded.push(current);
    if(!variableMap.containsKey(current)){
      variableMap.put(current, new Variable());
    }
  }

  public Variable getVariable(String varName){
    if(variableMap.containsKey(varName)){
      return variableMap.get(varName);
    }
    return null;
  }

  public MakeVariable setVariable(Stack<Command> previous){
    previous.pop();
    return new MakeVariable(variableMap.get(variablesAdded.pop()), previous.pop());
  }

  public List<String> getVariableString(){
    List<String> ret = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    for(String key : variableMap.keySet()){
      builder.append(key + " -> ");
      builder.append(variableMap.get(key).returnValue());
      builder.append("\n");
      ret.add(builder.toString());
    }
    return ret;
  }
}
