package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.List;

public interface BooleanCommand extends Command {


    static double[] twoArgOperation(List<TurtleStatus> ret, TurtleStatus ts, Command arg1, Command arg2) {
        ret.addAll(arg1.execute(ts));
        ret.addAll(arg2.execute(ts));
        double[] val = {arg1.returnValue(), arg1.returnValue()};
        return val;
    }
}
