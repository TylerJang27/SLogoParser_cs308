package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.MathCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * returns the value of pi
 * @author Lucy Gu
 */
public class Pi implements MathCommand {

    private double returnVal;
    public static final int NUM_ARGS = 0;

    /**
     * This operation does not take in any command; it returns the value of Pi
     */
    public Pi(){
    }

    /**
     * Create and return an empty list of turtle status, and set return value to the value of pi
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return      list of TurtleStatus instances containing only ts
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest){
        returnVal = Math.PI;
        return new LinkedList<>();
    }

    /**
     * @return      the return value set during the execution of this operation
     */
    @Override
    public double returnValue() {
        return returnVal;
    }

}
