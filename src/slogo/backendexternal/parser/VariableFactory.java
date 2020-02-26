package slogo.backendexternal.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import slogo.commands.Command;
import slogo.commands.controlcommands.MakeVariable;
import slogo.commands.controlcommands.Variable;

public class VariableFactory {

  private Map<String, MakeVariable> variableMap;

  public VariableFactory(){
    variableMap = new HashMap<>();
  }

  public void makeVariable(String current, Command previous){
    MakeVariable maker = new MakeVariable(new Variable(), previous);
    variableMap.put(current, maker);
  }

  public MakeVariable getVariable(String varName){
    if(variableMap.containsKey(varName)){
      return variableMap.get(varName);
    }
    return null;
  }

  public void setVariable(String varName, Command command){
    variableMap.put(varName, new MakeVariable(new Variable(), command));
  }
}
