package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public class Constant implements ControlCommand {

    public static final int NUM_ARGS = 0;
    private double myVal;

    public Constant(double val) {
        myVal = val;
    }

    public Constant() {
        this(0);
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return new ArrayList<>();
    }

    @Override
    public double returnValue() {
        return myVal;
    }
}
