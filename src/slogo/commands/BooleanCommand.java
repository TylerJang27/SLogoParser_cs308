package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.List;

/**
 *
 * @author Tyler Jang
 */
public interface BooleanCommand extends Command {

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    static double[] twoArgOperation(List<TurtleStatus> ret, TurtleStatus ts, Command arg1, Command arg2) {
        ret.addAll(arg1.execute(ts));
        ret.addAll(arg2.execute(ts));
        double[] val = {arg1.returnValue(), arg1.returnValue()};
        return val;
    }
}
