package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Tyler Jang
 */
public class HideTurtle implements TurtleCommand {

    public static final int NUM_ARGS = 0;
    private static final int TURTLE_HIDDEN = 0;

    public HideTurtle() {}

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing(), true, false, ts.getPenDown(), ts.getPenColor()));
        return ret;
    }

    @Override
    public double returnValue() { return TURTLE_HIDDEN; }
}
