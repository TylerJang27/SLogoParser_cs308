package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Returns a random number that is non negative and strictly less than the input value
 * @author Lucy Gu
 */
public class Rand implements MathCommand {

    private Command arg1;
    private double returnVal;
    public static final int NUM_ARGS = 1;

    /**
     * Takes in one input command, the return value of this command will be used for the Rand operation
     *
     * @param argA  input command to be executed
     */
    public Rand(Command argA){
        arg1 = argA;
    }

    /**
     * Create an empty list of turtle status, fill list with execution from the argument command
     * set return value to be a non negative random double strictly less than the return value of the argument command
     * (Math.random() gives a result from 0 to 1 but not including 1, which satisfies the strictly less than requirement)
     *
     * @param ts    a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *              TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return      list of turtle status from executing the argument commands to this operation
     *              (this operation itself does not generate new turtle status)
     */
    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        returnVal = Math.random()*arg1.returnValue();
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
