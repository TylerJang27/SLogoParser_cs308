package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tyler Jang
 */
public class HideTurtle implements TurtleCommand {

    public static final int NUM_ARGS = 0;
    private static final int TURTLE_HIDDEN = 0;

    public HideTurtle() {}

    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        TurtleStatus ts = manifest.getActiveState();
        ret.add(new TurtleStatus(ts.getID(), ts.getX(), ts.getY(), ts.getBearing(), true, false, ts.getPenDown()));
        return ret;
    }

    @Override
    public double returnValue() { return TURTLE_HIDDEN; }
}
