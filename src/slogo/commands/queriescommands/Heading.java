package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.Collection;
import java.util.List;

/**
 * Class that implements QueriesCommand, used to retrieve the direction the turtle is facing.
 *
 * @author Tyler Jang
 */
public class Heading implements QueriesCommand {
    public static final int NUM_ARGS = 0;

    private double dir;

    /**
     * Constructor for Heading. Takes 0 arguments.
     */
    public Heading() {}

    /**
     * Executes the Heading instance, retrieving the direction based off of ts.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a Collection of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        dir = ts.getBearing();
        return List.of(ts);
    }

    /**
     * Retrieves the value returned by Heading's execution, the direction.
     *
     * @return the turtle's direction.
     */
    @Override
    public double returnValue() {
        return dir;
    }
}
