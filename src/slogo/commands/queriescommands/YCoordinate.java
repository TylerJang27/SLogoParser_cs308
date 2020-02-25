package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.Collection;
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
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a Collection of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        yCor = ts.getY();
        return List.of(ts);
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
