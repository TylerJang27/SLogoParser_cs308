package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that implements ControlCommand, used to run a standard If conditional flow.
 *
 * @author Tyler Jang
 */
public class If implements ControlCommand {

    public static final int NUM_ARGS = 2;
    private ControlCommand conditional;

    /**
     * Constructor for If, setting the true commands as well as the condition using IfElse.
     *
     * @param condition     the Command instance that determines whether or not the true case will execute.
     * @param trueCommands  the Collection of Command instances to execute if the true case is met.
     */
    public If(Command condition, Collection<Command> trueCommands) {
        conditional = new IfElse(condition, trueCommands, new ArrayList<>());
    }

    /**
     * Executes the If instance, executing the trueCommands if the condition is not IfElse.ELSE_CASE(0).
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances produced as a result of running the conditional.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        return conditional.execute(manifest);
    }

    /**
     * Returns the value of the If, referring to its last executed command's value, or 0 if no commands are executed.
     *
     * @return the value of the conditional.
     */
    @Override
    public double returnValue() {
        return conditional.returnValue();
    }
}
