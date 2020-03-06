package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that implements QueriesCommand, used to retrieve the yCoordinate of the turtle.
 *
 * @author Tyler Jang
 */
public class YCoordinate implements QueriesCommand {
    public static final int NUM_ARGS = 0;

    private double yCor;

    /**
     * Constructor for YCoordinate. Takes 0 arguments.
     */
    public YCoordinate() {}

    /**
     * Executes the YCoordinate instance, retrieving the yCoordinate of the turtle.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        yCor = manifest.getActiveState().getY();
        return new LinkedList<>();
    }

    /**
     * Retrieves the yCoordinate calculated by YCoordinate's execution.
     *
     * @return the double xCoordinate of the turtle.
     */
    @Override
    public double returnValue() {
        return yCor;
    }
}
