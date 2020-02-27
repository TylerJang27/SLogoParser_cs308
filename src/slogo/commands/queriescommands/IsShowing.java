package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.Collection;
import java.util.List;

/**
 * Class that implements QueriesCommand, used to retrieve the visibility of the turtle.
 *
 * @author Tyler Jang
 */
public class IsShowing implements QueriesCommand {
    public static final int NUM_ARGS = 0;
    private static final double VISIBLE = 1;
    private static final double INVISIBLE = 0;

    private boolean showing;

    /**
     * Constructor for IsShowing. Takes 0 arguments.
     */
    public IsShowing() {}

    /**
     * Executes the IsShowing instance, retrieving the visibility of the turtle based off ts.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        showing = ts.getVisible();
        return List.of(ts);
    }

    /**
     * Retrieves the value returned by IsShowing's execution, the visibility of the turtle.
     *
     * @return VISIBLE(1) if the turtle is visible, else INVISIBLE(0).
     */
    @Override
    public double returnValue() {
        return showing ? VISIBLE : INVISIBLE;
    }
}
