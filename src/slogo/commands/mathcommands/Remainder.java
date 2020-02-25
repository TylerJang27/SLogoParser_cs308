package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Calculates the remainder when the first input value is divided by the second input value
 * @author Lucy Gu
 */
public class Remainder implements MathCommand {

    private Command arg1;
    private Command arg2;
    private double returnVal;
    public static final int NUM_ARGS = 2;

    /**
     * Takes in two input command, the return value of this command will be used for the Remainder operation
     *
     * @param argA  first input command (executed first)
     * @param argB  second input command (executed after the first command)
     */
    public Remainder(Command argA, Command argB){
        arg1 = argA;
        arg2 = argB;
    }

    /**
     * Create an empty list of turtle status, fill list with execution from the argument commands
     * using twoArgOperation, and set return value to be the remainder of the return value of the first command divided by
     * the return value of the second command
     *
     * @param ts    a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *              TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return      list of turtle status from executing the argument commands to this operation
     *              (this operation itself does not generate new turtle status)
     */
    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        double[] val = MathCommand.twoArgOperation(ret, ts, arg1, arg2);
        returnVal = (val[0])%(val[1]);
        return ret;
    }

    /**
     * @return      the return value set during the execution of this operation
     */
    @Override
    public double returnValue() {
        return returnVal;
    }

}
