package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.*;

/**
 *
 * @author Lucy Gu
 */
public class Forward implements TurtleCommand {

    public static final int NUM_ARGS = 1;

    private double steps;

    public Forward(double pixel){
        steps = pixel;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaX = steps*Math.sin(ts.getBearing());
        double deltaY = -1 * steps*Math.cos(ts.getBearing()); //TODO: Why is this negative?
        return Collections.unmodifiableCollection(TurtleCommand.moveDelta(ts, ret, deltaX, deltaY));
    }

    @Override
    public double returnValue() {
        return steps;
    }

}
