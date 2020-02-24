package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.*;

/**
 * Runable RunFunction
 *
 * @author Tyler Jang
 */
public class RunFunction implements ControlCommand {

    public static final int NUM_ARGS = 2;

    private static final String BAD_FUNCTION_CALL = "BadFunctionCall";

    private double myVal;

    private Collection<Command> myValues;
    private Function myFunction;

    public RunFunction(Function builtFunction, Collection<Command> variableValues) {
        myFunction = builtFunction;
        myValues = variableValues;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();

        if (myValues.size() == myFunction.getNumVars()) {
            int k = 0;
            for (Command c: myValues) {
                double val = ControlCommand.executeAndExtractValue(c, ts, ret);
                myFunction.setVariableValue(k, val);
                k++;
            }
        } else {
            //TODO Dennis: I would like to grab this from the resource files for the error name, but
            // I am unsure how to do so without overextending my bounds. Do you think you could look into this?
            throw new InvalidCommandException(BAD_FUNCTION_CALL);
        }
        myVal = ControlCommand.executeAndExtractValue(myFunction, ts, ret);
        return ret;
    }

    @Override
    public double returnValue() {
        return myVal;
    }
}
