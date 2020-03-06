package slogo.commands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;

import java.util.List;

/**
 * Interface for instances of Math Commands, extending the overarching Command interface.
 *
 * @author Lucy Gu
 */
public interface MathCommand extends Command {

//TODO: MOVE TO COMMAND TO AVOID DUPLICATED CODE
    /**
     * Takes in a list of turtle status, a most-recent turtle status, and two commands as parameter. Execute
     * each command consequentially and add the turtle status resulting from the execution to ret, then return
     * a collection of the return values of the input commands
     *
     *
     * @param ret   list of turtle status that will eventually hold all the turtle statuses from executing
     *              both input commands
     * @param manifest a TurtleManifest containing information about all the turtles
     * @param arg1  first argument to be executed
     * @param arg2  second argument to be executed
     *
     * @return      a double array, in which the first value is the return value from input command 1 and the second
     *              value is the return value from input command 2
     */
    static double[] twoArgOperation(List<TurtleStatus> ret, TurtleManifest manifest, Command arg1, Command arg2) {
        ret.addAll(arg1.execute(manifest));
        ret.addAll(arg2.execute(manifest));
        double[] val = {arg1.returnValue(), arg1.returnValue()};
        return val;
    }
}
