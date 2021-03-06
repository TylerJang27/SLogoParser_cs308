package slogo.commands.booleancommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.BooleanCommand;
import slogo.commands.Command;

import java.util.ArrayList;
import java.util.List;
/**
 * Return 1 if value is zero and 0 if nonzero
 * @author Lucy Gu
 */
public class Not implements BooleanCommand{
    private Command arg1;
    private double returnVal;
    public static final int NUM_ARGS = 1;

    /**
     * Takes in one input command, the return value of this command will be used for the Not operation
     *
     * @param argA argument to be executed, whose return value will be used in this operation
     */
    public Not(Command argA){
        arg1 = argA;
    }

    /**
     * Create an empty list of turtle status, fill list with execution from the argument command
     * set return value to 1 if return value from the argument is 0
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return      list of turtle status from executing the argument commands to this operation
     *              (this operation itself does not generate new turtle status)
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest){
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(manifest));
        returnVal = (arg1.returnValue()==0) ? TRUE : FALSE;
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
