package slogo.commands.idcommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.IdCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Class that implements IdCommand, used to query the active turtle.
 *
 * @author Tyler Jang
 */
public class ID implements IdCommand {
    public static final int NUM_ARGS = 0;

    private double activeID;

    /**
     * Constructor for ID. Takes 0 command arguments.
     */
    public ID() {
    }

    /**
     * Executes the ID instance, retrieving the active turtle.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        activeID = manifest.getActiveTurtle();
        return new LinkedList<>();
    }

    /**
     * Retrieves the value returned by ID's execution, the active turtle ID.
     *
     * @return the active turtle's ID.
     */
    @Override
    public double returnValue() {
        return activeID;
    }
}
