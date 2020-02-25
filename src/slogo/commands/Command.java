package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.List;

/**
 * Interface for instances of Commands, each with their own execute() and returnValue() implementation.
 * Calling execute() on a command will determine the output of its returnValue() implementation, depending on subsequent
 * commands that are executed.
 *
 * Every Command also has a NUM_ARGS constant, used to determine how many arguments must be passed to its constructor during
 * the parsing process.
 *
 * @author Tyler Jang, Lucy Gu
 */
public interface Command {
    public static final int NUM_ARGS = 0;
    double xMax = 100;
    double yMax = 100;        //TODO: change to front end display size
    public final static String[] MODES = {"normal", "edge", "toroidal"};

    /**
     * Executes a Command, updating variables, control logic, computation, or turtle movement (as stored in TurtleStatus instances).
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return a Collection of TurtleStatus instances, given by the execution of this and any subsequent commands.
     */
    public List<TurtleStatus> execute(TurtleStatus ts);

    /**
     * Retrieves the value returned by a Command's execution, calculated during the execute() process.
     *
     * @return a double for the value that a Command returns, which may depend on subsequent commands.
     */
    public double returnValue();

    /**
     * Returns a boolean for whether or not the screen's pen drawings should be cleared.
     *
     * @return a boolean, which should be false for all cases except ClearScreen or similar commands.
     */
    public static boolean toClear() {
        return false;
    }
}
