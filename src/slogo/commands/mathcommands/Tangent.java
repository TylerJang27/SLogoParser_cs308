package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the tangent of the input value
 * @author Lucy Gu
 */
public class Tangent implements MathCommand {

    private Command arg1;
    private double returnVal;
    public static final int NUM_ARGS = 1;

    /**
     * Takes in one input command, the return value of this command will be used for the Tangent operation
     *
     * @param argA  input command to be executed
     */
    public Tangent(Command argA){
        arg1 = argA;
    }

    /**
     * Create an empty list of turtle status, fill list with execution from the argument command
     * set return value to the tangent of the return value of the input command
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return      list of turtle status from executing the argument commands to this operation
     *              (this operation itself does not generate new turtle status)
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest){
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(manifest));
        returnVal = Math.tan(arg1.returnValue());
        return ret;
    }

    /**
     * @return      the return value of this operation
     */
    @Override
    public double returnValue() {
        return returnVal;
    }

}
