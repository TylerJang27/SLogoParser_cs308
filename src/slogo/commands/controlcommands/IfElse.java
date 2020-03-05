package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that implements ControlCommand, used to run a standard IfElse conditional flow.
 *
 * @author Tyler Jang
 */
public class IfElse implements ControlCommand {

    public static final int NUM_ARGS = 3;
    private static final double ELSE_CASE = 0;
    private double myVal;

    private Command conditionExpr;
    private Collection<Command> trueCommandList;
    private Collection<Command> falseCommandList;

    /**
     * Constructor for IfElse, setting the true and false commands as well as the condition.

     * @param condition     the Command instance that determines whether the true or false case will execute.
     * @param trueCommands  the Collection of Command instances to execute if the true case is met.
     * @param falseCommands the Collection of Command instances to execute if the false case is met.
     */
    public IfElse(Command condition, Collection<Command> trueCommands, Collection<Command> falseCommands) {
        conditionExpr = condition;
        trueCommandList = trueCommands;
        falseCommandList = falseCommands;
    }

    /**
     * Executes the IfElse instance, executing the trueCommandList if exprVal is not ELSE_CASE(0) and executing the
     * falseCommandList if exprVal is ELSE_CASE(0).
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances produced as a result of running the conditional.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        double exprVal = Command.executeAndExtractValue(conditionExpr, ts, ret);
        ts = ret.get(ret.size() - 1);

        if (exprVal != ELSE_CASE) {
            for (Command c: trueCommandList) {
                myVal = Command.executeAndExtractValue(c, ts, ret);
                ts = ret.get(ret.size() - 1);
            }
        } else {
            for (Command c: falseCommandList) {
                myVal = Command.executeAndExtractValue(c, ts, ret);
                ts = ret.get(ret.size() - 1);
            }
        }
        return ret;
    }

    /**
     * Returns the value of the IfElse, referring to its last executed command's value, or 0 if no commands are executed.
     *
     * @return the value of the conditional.
     */
    @Override
    public double returnValue() {
        return myVal;
    }
}
