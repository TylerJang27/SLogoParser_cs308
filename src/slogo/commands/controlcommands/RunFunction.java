package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.*;

/**
 * Class that implements ControlCommand, used to call/execute a function, assigning it values to its variables.
 * The Function myFunction should be created already and defined using TO before calling RunFunction's constructor.
 * Ensure that the correct number of values are specified for myFunction's variables.
 *
 * @author Tyler Jang
 */
public class RunFunction implements ControlCommand {

    public static final int NUM_ARGS = 2;

    private static final String BAD_FUNCTION_CALL = "BadFunctionCall";

    private double myVal;

    private List<Command> myValues;
    private Function myFunction;

    /**
     * Constructor for RunFunction, used to apply the values for a function's variables and execute it.
     *
     * @param builtFunction             the function to be called/executed.
     * @param variableValues            the values to assign to builtFunction's variables.
     * @throws InvalidCommandException  if the number of values does not equal the number of variables.
     */
    public RunFunction(Function builtFunction, List<Command> variableValues) throws InvalidCommandException {
        myFunction = builtFunction;
        myValues = variableValues;
        if (myValues.size() != myFunction.getNumVars()) {
            //TODO Dennis: I would like to grab this from the resource files for the error name, but
            // I am unsure how to do so without overextending my bounds. Do you think you could look into this?
            throw new InvalidCommandException(BAD_FUNCTION_CALL);
        }
    }

    /**
     * Executes the RunFunction, used to apply the values to the variables sequentially
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return a Collection of TurtleStatus instances, produced as a result of setting the variable values and running the Function.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();

        for (int k = 0; k < myValues.size(); k ++) {
            Command c = myValues.get(k);
            double val = ControlCommand.executeAndExtractValue(c, ts, ret);
            myFunction.setVariableValue(k, val);
        }
        myVal = ControlCommand.executeAndExtractValue(myFunction, ts, ret);
        return ret;
    }

    /**
     * Returns the value of RunFunction, referring to the Function's last executed command's value.
     *
     * @return the value of the Function.
     */
    @Override
    public double returnValue() {
        return myVal;
    }
}
