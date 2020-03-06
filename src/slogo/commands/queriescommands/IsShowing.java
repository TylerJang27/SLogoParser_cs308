package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.LinkedList;
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
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        showing = manifest.getActiveState().getVisible();
        return new LinkedList<>();
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
