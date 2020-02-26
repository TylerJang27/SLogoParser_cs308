package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

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
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        xCor = ts.getX();
        return List.of(ts);
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
