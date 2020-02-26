package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Tyler Jang
 */
public class IsPenDown implements QueriesCommand {
    public static final int NUM_ARGS = 0;
    private static final double PEN_DOWN = 1;
    private static final double PEN_UP = 0;

    private boolean penDown;

    public IsPenDown() {}

    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        penDown = ts.getPenDown(); //TODO: Test to make sure this accounts for wraparound cases
        return Collections.unmodifiableCollection(new ArrayList<>());
    }

    public double returnValue() {
        return penDown ? PEN_DOWN : PEN_UP;
    }
}
