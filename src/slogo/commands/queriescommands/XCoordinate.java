package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that implements QueriesCommand, used to retrieve the xCoordinate of the turtle.
 *
 * @author Tyler Jang
 */
public class XCoordinate implements QueriesCommand {
    public static final int NUM_ARGS = 0;

    private double xCor;

    /**
     * Constructor for XCoordinate. Takes 0 arguments.
     */
    public XCoordinate() {}

    /**
     * Executes the XCoordinate instance, retrieving the xCoordinate of the turtle.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        xCor = manifest.getActiveState().getX();
        return new LinkedList<>();
    }

    /**
     * Retrieves the xCoordinate calculated by XCoordinate's execution.
     *
     * @return the double xCoordinate of the turtle.
     */
    @Override
    public double returnValue() {
        return xCor;
    }
}
