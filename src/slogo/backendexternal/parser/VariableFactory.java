package slogo.backendexternal.parser;

import java.util.HashMap;
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

  public MakeVariable makeVariable(Command previous){
    String key = variablesAdded.pop();
    return new MakeVariable(variableMap.get(key), previous);
  }

  public boolean handleVariable(String current){
    if(variableMap.containsKey(current)){
      return true;
    }
    else{
      variablesAdded.push(current);
      variableMap.put(current, new Variable());
      return false;
    }
  }

  public Variable getVariable(String varName){
    if(variableMap.containsKey(varName)){
      return variableMap.get(varName);
    }
    return null;
  }

  public MakeVariable setVariable(Command command){
    return new MakeVariable(variableMap.get(variablesAdded.pop()), command);
  }

  public String getVariableString(){
    StringBuilder ret = new StringBuilder();
    for(String key : variableMap.keySet()){
      ret.append(key + " -> ");
      ret.append(variableMap.get(key).returnValue());
      ret.append("\n");
    }
    return ret.toString();
  }
}
