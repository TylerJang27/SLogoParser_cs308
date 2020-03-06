package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.ControlCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that implements ControlCommand, used to store (and execute) a defined double value.
 *
 * @author Lucy Gu, Tyler Jang
 */
public class Constant implements ControlCommand {

    public static final int NUM_ARGS = 0;
    private double myVal;

    /**
     * Constructor for Constant. Takes 0 arguments.
     *
     * @param val the value stored in this command.
     */
    public Constant(double val) {
        myVal = val;
    }

    /**
     * The default constructor for Constant, setting the constant to 0.
     */
    public Constant() {
        this(0);
    }

    /**
     * Executes the Constant instance, per standard implementation of the interface.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        return new LinkedList<>();
    }

    /**
     * Retrieves the value of the Constant.
     *
     * @return myVal.
     */
    @Override
    public double returnValue() {
        return myVal;
    }
}
