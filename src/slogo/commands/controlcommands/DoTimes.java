package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.Collection;
import java.util.List;

/**
 * Class that implements ControlCommand, used to run a standard DoTimes loop, of the form DOTIMES [ variable limit ] [ command(s) ].
 *
 * @author Tyler Jang
 */
public class DoTimes implements ControlCommand {

    public static final int NUM_ARGS = 3;
    private static final int SINGLE_STEP = 1;

    private ControlCommand loop;
    /**
     * Constructor for DoTimes, creating a For loop based on its parameters.
     *
     * @param var       the variable to increment.
     * @param varCap    the maximum value of the variable.
     * @param commands  the Collection of commands to execute for a given loop.
     */
    public DoTimes(Variable var, Command varCap, List<Command> commands) {
        loop = new For(var, new Constant(), varCap, new Constant(SINGLE_STEP), commands);
    }

    /**
     * Executes the DoTimes instance, looping through the specified number of times.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances produced as a result of running the loop.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        return loop.execute(manifest);
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
}
