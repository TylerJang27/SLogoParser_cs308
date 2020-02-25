package slogo.backendexternal.parser.resources;

import slogo.commands.controlcommands.Variable;

public class VariableFactory {

  public VariableFactory(){}

  public Variable makeVariable(){
    return new Variable();
  }
}
