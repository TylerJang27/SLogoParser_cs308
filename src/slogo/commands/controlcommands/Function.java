package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Function implements ControlCommand {

    public static final int NUM_ARGS = 0;

    private double myVal;
    private List<Variable> myVariables;
    private Collection<Command> myCommands;

    public Function() {
        myVariables = new ArrayList<>();
        myCommands = new ArrayList<>();
    }

    protected void setVariables(List<Variable> variables) {
        myVariables = variables;
    }

    protected void setCommands(Collection<Command> commands) {
        myCommands = commands;
    }

    protected void setVariableValue(int index, double val) {
        myVariables.get(index).setVal(val);
    }

    public int getNumVars() {
        return myVariables.size();
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        for (Command c: myCommands) {
            myVal = ControlCommand.executeAndExtractValue(c, ts, ret);
        }
        return ret;
    }

    @Override
    public double returnValue() {
        return myVal;
    }

}
