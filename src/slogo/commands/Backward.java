package slogo.commands;

import slogo.backendexternal.TurtleStatus;
import java.util.*;

public class Backward implements Command {

    public static final int NUM_ARGS = 1;

    private double steps;
    private double xMax;
    private double yMax;

    public Backward(double pixel, double maxX, double maxY){
        steps = pixel;
        xMax = maxX;
        yMax = maxY;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaX = 0-steps*Math.sin(ts.getBearing());
        double deltaY = steps*Math.cos(ts.getBearing());
        return Collections.unmodifiableCollection(Command.move(ts,ret,deltaX,deltaY,xMax, yMax, mode));
    }

    @Override
    public double returnValue() {
        return (double)steps;
    }

}
