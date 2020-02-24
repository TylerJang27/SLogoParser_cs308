package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IfElse implements ControlCommand {

    public static final int NUM_ARGS = 3;
    private static final double ELSE_CASE = 0;
    private double myVal;

    private Command conditionExpr;
    private Collection<Command> trueCommandList;
    private Collection<Command> falseCommandList;

    public IfElse(Command condition, Collection<Command> trueCommands, Collection<Command> falseCommands) {
        conditionExpr = condition;
        trueCommandList = trueCommands;
        falseCommandList = falseCommands;
    }

    //TODO: NOTE BUG IN THE WAY OUR CODE IS SET UP: ROUNDOFF ERROR IS AN EXISTING PROBLEM
    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        double exprVal = ControlCommand.executeAndExtractValue(conditionExpr, ts, ret);

        if (exprVal != ELSE_CASE) {
            for (Command c: trueCommandList) {
                myVal = ControlCommand.executeAndExtractValue(c, ts, ret);
            }
        } else {
            for (Command c: falseCommandList) {
                myVal = ControlCommand.executeAndExtractValue(c, ts, ret);
            }
        }
        return ret;
    }

    @Override
    public double returnValue() {
        return myVal;
    }
}
