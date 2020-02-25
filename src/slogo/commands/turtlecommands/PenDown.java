package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Tyler Jang
 */
public class PenDown implements TurtleCommand {

    public static final int NUM_ARGS = 0;
    private static final int DOWN_STATUS = 1;

    public PenDown() {}

    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.add(ts);
        ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing(), true, ts.getVisible(), true, ts.getPenColor()));
        return (ret);
    }

    @Override
    public double returnValue() { return DOWN_STATUS; }
}
