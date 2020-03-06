package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements ControlCommand, used to adjust/make a variable. MakeVariable is used for the MAKE or SET commands.
 * The Variable varHolder should be created with the default constructor before calling
 * MakeVariable's constructor. Ensure that this same Variable is passed to future MakeVariable instances
 * so that a variable may have continuity over a program.
 *
 * @author Tyler Jang
 */
public class MakeVariable implements ControlCommand {

    public static final int NUM_ARGS = 1;
    private Command expr;
    private double val;
    Variable varHolder;

    /**
     * Constructor for MakeVariable, used to note the variable and its new value.
     *
     * @param var   the variable to be adjusted.
     * @param value the new value for the variable.
     */
    public MakeVariable(Variable var, Command value) {
        varHolder = var;
        expr = value;
    }

    /**
     * Executes the MakeVariable, modifying the variable's value.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing any statuses resulting from the execution of subsequent commands.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(expr.execute(manifest));
        val = expr.returnValue();
        varHolder.setVal(val);
        return ret;
    }

    /**
     * Retrieves the value to which the variable is set.
     *
     * @return the new value of the variable.
     */
    @Override
    public double returnValue() {
        return val;
    }
}
