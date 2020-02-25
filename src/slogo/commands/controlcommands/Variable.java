package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.ControlCommand;

import java.util.Collection;
import java.util.List;

/**
 * Class that implements ControlCommand, used to store a variable's value and return it if queried.
 *
 * @author Tyler Jang
 */
public class Variable implements ControlCommand {

    //TODO: Should be 0 or 1?
    public static final int NUM_ARGS = 0;
    private double myVal;

    /**
     * Constructor for Variable, setting its value to val.
     *
     * @param val the value to which myVal should be initialized.
     */
    public Variable(double val) {
        myVal = val;
    }

    /**
     * Default Constructor for Variable, settings it value to 0.
     */
    public Variable() { this(0); }

    /**
     * Executes the Variable, retrieving its value.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a Collection of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        return List.of(ts);
    }

    /**
     * Retrieves the value of the variable.
     *
     * @return the value stored in the variable.
     */
    @Override
    public double returnValue() {
        return myVal;
    }

    /**
     * Sets the value of the variable, to be called only by other Commands (typically MakeVariable).
     *
     * @param newVal the new value to be stored by the variable.
     */
    protected void setVal(double newVal) {
        myVal = newVal;
    }
}
