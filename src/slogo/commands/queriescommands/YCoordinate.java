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
public class YCoordinate implements QueriesCommand {
    public static final int NUM_ARGS = 0;

    private double yCor;

    public YCoordinate() {}

    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        yCor = ts.getY();
        return Collections.unmodifiableCollection(new ArrayList<>());
    }

    public double returnValue() {
        return yCor;
    }
}
