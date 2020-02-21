package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Tyler Jang
 */
public class PenDown implements Command {

    public static final int NUM_ARGS = 0;
    private static final int DOWN_STATUS = 1;

    public PenDown() {}

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing(), true, ts.getVisible(), true, ts.getPenColor()));
        return Collections.unmodifiableCollection(ret);
    }

    @Override
    public double returnValue() { return DOWN_STATUS; }
}
