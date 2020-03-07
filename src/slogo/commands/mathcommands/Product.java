package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;
import slogo.commands.controlcommands.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the product of the first input value and the second input value
 * @author Lucy Gu
 */
public class Product implements MathCommand {

    private Command arg1;
    private Command arg2;
    private double returnVal;
    public static final int NUM_ARGS = 2;

    /**
     * Takes in two input command, the return value of this command will be used for the Product operation
     *
     * @param argA  first input command (executed first)
     * @param argB  second input command (executed after the first command)
     */
    public Product(Command argA, Command argB){
        arg1 = argA;
        arg2 = argB;
    }

    /**
     * Create an empty list of turtle status, fill list with execution from the argument commands
     * using twoArgOperation, and set return value to be the product of the return value of the first command and
     * the return value of the second command
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return      list of turtle status from executing the argument commands to this operation
     *              (this operation itself does not generate new turtle status)
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest){
        List<TurtleStatus> ret = new ArrayList<>();
        double[] val = MathCommand.twoArgOperation(ret, manifest, arg1, arg2);
        returnVal = val[0]*val[1];
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
