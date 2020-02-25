package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
     * 
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return
     */
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        dir = ts.getBearing();
        return Collections.unmodifiableCollection(new ArrayList<>());
    }

    public double returnValue() {
        return dir;
    }
}
