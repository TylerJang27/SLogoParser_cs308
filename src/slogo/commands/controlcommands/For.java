package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Tyler Jang
 */
public class For implements ControlCommand {
    public static final int NUM_ARGS = 5;
    private double myVal;

    private Variable varCounter;
    private Command varMin;
    private Command varMax;
    private Command varIncr;
    private Collection<Command> commandsToExecute;

    public For(Variable var, Command varStart, Command varCap, Command varStep, Collection<Command> commands) {
        varCounter = var;
        varMin = varStart;
        varMax = varCap;
        varIncr = varStep;
        commandsToExecute = commands;
        myVal = 0;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        //TODO: AT THE MOMENT, THE ITEMS IN THE VARCAP FIELD WILL BE EXECUTED AND ADDED AS TURTLESTATUS INSTANCES
        //TODO: DETERMINE IF THIS IS APPROPRIATE/REQUIRED

        List<TurtleStatus> ret = new ArrayList<>();
        varCounter.setVal(executeAndExtractValue(varMin, ts, ret));
        double cap = executeAndExtractValue(varMax, ts, ret);
        double incr = executeAndExtractValue(varIncr, ts, ret);

        while (varCounter.returnValue() < cap) {
            for (Command c: commandsToExecute) {
                myVal = executeAndExtractValue(c, ts, ret);
            }
            varCounter.setVal(varCounter.returnValue() + incr);
        }
        return ret;
    }

    private double executeAndExtractValue(Command command, TurtleStatus ts, List<TurtleStatus> ret) {
        TurtleStatus currentStatus = (ret.isEmpty()) ? ts : ret.get(ret.size()-1);
        ret.addAll(command.execute(currentStatus));
        return command.returnValue();
    }

    @Override
    public double returnValue() {
        return myVal;
    }
}
