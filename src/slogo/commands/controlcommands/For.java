package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that implements ControlCommand, used to run For loops, of the form FOR [ variable start end increment ] [ command(s) ].
 *
 * @author Tyler Jang
 */
public class For implements ControlCommand {
    public static final int NUM_ARGS = 5;
    private static final int INFINITE_MAX = 9999;
    private static final String BAD_INFINITE_LOOP = "BadInfiniteLoop";
    private double myVal;

    private Variable varCounter;
    private Command varMin;
    private Command varMax;
    private Command varIncr;
    private Collection<Command> commandsToExecute;

    /**
     * Constructor for For, creating a loop with the specified parameters.
     *
     * @param var       the variable to increment.
     * @param varStart  the starting value of the variable.
     * @param varCap    the maximum value of the variable at which to stop the loop.
     * @param varStep   the amount by which the variable should be incremented.
     * @param commands  the Collection of commands to execute for a given loop.
     */
    public For(Variable var, Command varStart, Command varCap, Command varStep, Collection<Command> commands) {
        varCounter = var;
        varMin = varStart;
        varMax = varCap;
        varIncr = varStep;
        commandsToExecute = commands;
        myVal = 0;
    }

    /**
     * Executes the For instance, updating varCounter as necessary and executing the contained commands.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances produced as a result of running the loop.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        //TODO: AT THE MOMENT, THE ITEMS IN THE VARCAP FIELD WILL BE EXECUTED AND ADDED AS TURTLESTATUS INSTANCES
        //TODO: DETERMINE IF THIS IS APPROPRIATE/REQUIRED

        List<TurtleStatus> ret = new ArrayList<>();
        varCounter.setVal(ControlCommand.executeAndExtractValue(varMin, ts, ret));
        double cap = ControlCommand.executeAndExtractValue(varMax, ts, ret);

        int counter = 0;
        while (varCounter.returnValue() < cap) {
            if (counter > INFINITE_MAX) {
                //TODO Dennis: Please also help with this resource file thing (maybe add it so that the Exception itself pulls from the ResourceBundle
                throw new InvalidCommandException(BAD_INFINITE_LOOP);
            }
            for (Command c: commandsToExecute) {
                myVal = ControlCommand.executeAndExtractValue(c, ts, ret);
            }
            double incr = ControlCommand.executeAndExtractValue(varIncr, ts, ret);
            varCounter.setVal(varCounter.returnValue() + incr);
            counter ++;
        }
        return ret;
    }

    /**
     * Returns the value of For, referring to its last executed command's value, or 0 if no commands are executed.
     *
     * @return the value of the loop.
     */
    @Override
    public double returnValue() {
        return myVal;
    }
}
