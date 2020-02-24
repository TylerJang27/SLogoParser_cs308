package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Tyler Jang
 */
public class Variable implements ControlCommand {

    public static final int NUM_ARGS = 0;
    private double myVal;

    public Variable(double val) {
        myVal = val;
    }

    public Variable() { this(0); }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return new ArrayList<>();
    }

    @Override
    public double returnValue() {
        return myVal;
    }

    //Should only be called from other Commands, not by Parser or CommandFactory
    protected void setVal(double newVal) {
        myVal = newVal;
    }
}
