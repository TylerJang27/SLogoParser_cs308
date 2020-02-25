package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.List;

/**
 * Interface that extends Command, for instances of commands related to Control flow, variables, and user-defined functions.
 * Inherits the execute() and returnValue() methods from Command.
 *
 * Adds a static method for executeAndExtractValue, in which a command and any subsequent commands are executed,
 * its returnValue() output is retrieved, and any TurtleStatus instances generated along the way are added to a
 * List.
 *
 * @author Tyler Jang
 */
public interface ControlCommand extends Command {

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
