package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.List;

/**
 * Interface for instances of Boolean Commands, extending the overarching Command interface
 * @author Tyler Jang, Lucy Gu
 */
public interface BooleanCommand extends Command {

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    /**
     * Takes in a list of turtle status, a most-recent turtle status, and two commands as parameter. Execute
     * each command consequentially and add the turtle status resulting from the execution to ret, then return
     * a collection of the return values of the input commands
     *
     *
     * @param ret   list of turtle status that will eventually hold all the turtle statuses from executing
     *              both input commands
     * @param ts    a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *              TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @param arg1  first argument to be executed
     * @param arg2  second argument to be executed
     *
     * @return      a double array, in which the first value is the return value from input command 1 and the second
     *              value is the return value from input command 2
     */
    static double[] twoArgOperation(List<TurtleStatus> ret, TurtleStatus ts, Command arg1, Command arg2) {
        ret.addAll(arg1.execute(ts));
        ret.addAll(arg2.execute(ret.get(ret.size()-1)));
        double[] val = {arg1.returnValue(), arg1.returnValue()};
        return val;
    }
}
