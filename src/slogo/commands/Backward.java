package slogo.commands;

import slogo.backendexternal.TurtleStatus;
import java.util.*;

public class Backward implements Command {

    public static final int NUM_ARGS = 1;

    private double steps;

    public Backward(double pixel){
        steps = pixel;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaX = 0-steps*Math.sin(ts.getBearing());
        double deltaY = steps*Math.cos(ts.getBearing());
        return Collections.unmodifiableCollection(Command.moveDelta(ts, ret, deltaX, deltaY));
    }

    @Override
    public double returnValue() {
        return (double)steps;
    }

}
