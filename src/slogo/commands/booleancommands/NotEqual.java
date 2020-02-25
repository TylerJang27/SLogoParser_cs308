package slogo.commands.booleancommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.BooleanCommand;
import slogo.commands.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * return 1 if first value is not equal to second value
 * @author Lucy Gu
 */
public class NotEqual implements BooleanCommand{
    private Command arg1;
    private Command arg2;
    private double returnVal;
    public static final int NUM_ARGS = 2;

    /**
     * Takes in two commands as arguments: the return values of the two commands will be used for the NotEqual operation
     * @param argA first input command
     * @param argB second input command
     */
    public NotEqual(Command argA, Command argB){
        arg1 = argA;
        arg2 = argB;
    }

    /**
     * Create an empty list of turtle status, fill list with execution from the argument commands
     * using twoArgOperation, and set return value to be 1 if the return value of the two arguments are not
     * equal
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return list of turtle status from executing the argument commands to this operation
     *         (this operation itself does not generate new turtle status)
     */
    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        double[] val = BooleanCommand.twoArgOperation(ret, ts, arg1, arg2);
        returnVal = (val[0]!=val[1]) ? TRUE : FALSE;
        return ret;
    }

    /**
     * @return the return value set during the execution of this operation
     */
    @Override
    public double returnValue() {
        return returnVal;
    }

}
