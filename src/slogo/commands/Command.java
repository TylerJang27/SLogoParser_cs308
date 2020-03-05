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
 * Adds a static method for executeAndExtractValue, in which a command and any subsequent commands are executed,
 * its returnValue() output is retrieved, and any TurtleStatus instances generated along the way are added to a
 * List.
 *
 * @author Tyler Jang, Lucy Gu
 */
public interface Command {

    /**
     * Executes a Command, updating variables, control logic, computation, or turtle movement (as stored in TurtleStatus instances).
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return a List of TurtleStatus instances, given by the execution of this and any subsequent commands.
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

    /**
     * Executes the command argument, along with any subsequent commands, based on the initial TurtleStatus instance.
     * Adds the output of the commands' execution to a List of TurtleStatus instances, and returns the value of command.
     *
     * @param command   the command to execute (along with any related subsequent commands).
     * @param ts        the TurtleStatus instance upon which to base the execution of command if ret is empty.
     * @param ret       the List of TurtleStatus instances which includes all outputs of previously executed commands.
     * @return          a double for the value of command's returnValue() output after execution.
     */
    static double executeAndExtractValue(Command command, TurtleStatus ts, List<TurtleStatus> ret) {
        TurtleStatus currentStatus = (ret.isEmpty()) ? ts : ret.get(ret.size()-1);
        ret.addAll(command.execute(currentStatus));
        return command.returnValue();
    }
}
