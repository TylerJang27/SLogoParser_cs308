package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.LinkedList;
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
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        dir = manifest.getActiveState().getBearing();
        return new LinkedList<>();
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
