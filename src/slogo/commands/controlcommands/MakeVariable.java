package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.parser.Parser;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
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
    //TODO Dennis: PLEASE CHECK IN THE PARSER TO ENSURE THAT arg1 CONTAINS A VARIABLE INSTANCE
    public MakeVariable(Variable var, Command value) {
        varHolder = var;
        expr = value;
    }

    /**
     * Executes the MakeVariable, modifying the variable's value.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a Collection of TurtleStatus instances, containing any statuses resulting from the execution of subsequent commands.
     */
    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(expr.execute(ts));
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

    /**
     * Retrieves the Variable instance for this Command.
     *
     * @return varHolder.
     */
    //TODO: Remove if possible
    public Variable getVarHolder() {
        return varHolder;
    }
}
