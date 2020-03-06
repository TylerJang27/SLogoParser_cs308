package slogo.commands.idcommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.IdCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Class that implements IdCommand, used to query the number of created turtles.
 *
 * @author Tyler Jang
 */
public class Turtles implements IdCommand {
    public static final int NUM_ARGS = 0;

    private double numTurtles;

    /**
     * Constructor for Turtles. Takes 0 command arguments.
     */
    public Turtles() {
    }

    /**
     * Executes the Tell instance, retrieving the number of turtles.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        numTurtles = manifest.getAvailableTurtles().size();
        return new LinkedList<>();
    }

    /**
     * Retrieves the value returned by Turtle's execution, the number of turtles.
     *
     * @return the number of turtles.
     */
    @Override
    public double returnValue() {
        return numTurtles;
    }
}
