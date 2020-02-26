package slogo.backendexternal.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import slogo.commands.Command;
import slogo.commands.controlcommands.Function;
import slogo.commands.controlcommands.MakeUserInstruction;
import slogo.commands.controlcommands.Variable;

public class FunctionFactory {

  private Map<String, MakeUserInstruction> functionMap;
  private List<Variable> functionVariables;
  private List<Command> functionCommands;

  public FunctionFactory(){
    functionMap = new HashMap<>();
    functionVariables = new ArrayList<>();
    functionCommands = new ArrayList<>();
  }

  public void defFunction(String current, Stack<Command> previous){
    if(previous.size() > 0){

    }
//    MakeUserInstruction maker = new MakeUserInstruction(new Function(), functionVariables, ); // TODO : THROW ERROR
//    functionMap.put(current, maker);
  }

  public MakeUserInstruction getVariable(String varName){

    return null;
  }

//  public void alterFunction(String varName, Command command){
//    functionMap.put(varName, new MakeUserInstruction(new Function(), functionVariables, command));
//  }


}
