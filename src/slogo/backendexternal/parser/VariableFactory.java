package slogo.backendexternal.parser;

import java.util.Stack;
import slogo.commands.Command;
import slogo.commands.controlcommands.MakeVariable;
import slogo.commands.controlcommands.Variable;

public class VariableFactory {

  public VariableFactory(){}

  public MakeVariable makeVariable(String current, Stack<Command> previous){
    Variable newVar = new Variable();
    MakeVariable maker = null; // TODO : THROW ERROR
    if(previous.size() > 0){
      maker = new MakeVariable(newVar, previous.pop());
    }
    return maker;
  }
}
