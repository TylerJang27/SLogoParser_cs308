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
public class ShowTurtle implements TurtleCommand {

    public static final int NUM_ARGS = 0;
    private static final int TURTLE_SHOWN = 1;

    public ShowTurtle() {}

    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        TurtleStatus ts = manifest.getActiveState();
        ret.add(new TurtleStatus(ts.getID(), ts.getX(), ts.getY(), ts.getBearing(), true, true, ts.getPenDown()));
        return ret;
    }

    @Override
    public double returnValue() { return TURTLE_SHOWN; }
}
