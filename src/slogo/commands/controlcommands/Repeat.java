package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.Collection;

//TODO Dennis: PLEASE REPLACE ANY INSTANCE OF ":repcount" in the parsing process with:
// getRepCount()
// Note: if this does not work with the way the parser builds a stack, replace the uncommented version below with the commented version.

/**
 * Class that implements ControlCommand, used to run a standard Repeat loop, of the form REPEAT expr [ command(s) ].
 *
 * @author Tyler Jang
 */
public class Repeat implements ControlCommand {

    //Start
    public static final int NUM_ARGS = 2;
    private Variable repCount = new Variable();
    private ControlCommand loop;

    /**
     * Constructor for Repeat, creating a DoTimes loop based on its parameters.
     *
     * @param varCap    the maximum value of the variable.
     * @param commands  the Collection of commands to execute for a given loop.
     */
    public Repeat(Command varCap, Collection<Command> commands) {
        loop = new DoTimes(getRepCount(), varCap, commands);
    }
    //End

    //TODO: Parser implementation
//
//    public static final int NUM_ARGS = 3;
//    private Variable repCount = new Variable();
//    private ControlCommand loop;
//
//    /**
//     * Constructor for Repeat, creating a DoTimes loop based on its parameters.
//     *
//     * @param var       the variable to increment.
//     * @param varCap    the maximum value of the variable.
//     * @param commands  the Collection of commands to execute for a given loop.
//     */
//    public Repeat(Variable var, Command varCap, Collection<Command> commands) {
//        repCount = var;
//        loop = new DoTimes(var, varCap, commands);
//    }
//

    /**
     * Executes the Repeat instance, looping through the specified number of times.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a Collection of TurtleStatus instances produced as a result of running the loop.
     */
    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return loop.execute(ts);
    }

    /**
     * Returns the value of DoTimes, referring to its last executed command's value, or 0 if no commands are executed.
     *
     * @return the value of the loop.
     */
    @Override
    public double returnValue() {
        return loop.returnValue();
    }

    /**
     * Retreives repCount, the Variable implicitly used to count the loop's execution.
     *
     * @return the reference to repCount.
     */
    public Variable getRepCount() {
        return repCount;
    }
}
