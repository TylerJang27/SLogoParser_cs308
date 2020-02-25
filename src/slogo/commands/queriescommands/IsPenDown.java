package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.Collection;
import java.util.List;

/**
 * Class that implements QueriesCommand, used to retrieve the status of the pen (up or down).
 *
 * @author Tyler Jang
 */
public class IsPenDown implements QueriesCommand {
    public static final int NUM_ARGS = 0;
    private static final double PEN_DOWN = 1;
    private static final double PEN_UP = 0;

    private boolean penDown;

    /**
     * Constructor for IsPenDown. Takes 0 arguments.
     */
    public IsPenDown() {}

    /**
     * Executes the IsPenDown instance, retrieving the status of the pen based off of ts.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        penDown = ts.getPenDown();
        return List.of(ts);
    }

    /**
     * Retrieves the status calculated by Heading's execution, the pen status.
     *
     * @return PEN_DOWN(1) if the pen is down, else PEN_UP(0).
     */
    @Override
    public double returnValue() {
        return penDown ? PEN_DOWN : PEN_UP;
    }
}
