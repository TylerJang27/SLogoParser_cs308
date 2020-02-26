package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
     * @param ts    a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *              TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return      list of TurtleStatus instances containing only ts
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts){
        returnVal = Math.PI;
        return List.of(ts);
    }

    /**
     * @return      the return value set during the execution of this operation
     */
    @Override
    public double returnValue() {
        return returnVal;
    }

}
