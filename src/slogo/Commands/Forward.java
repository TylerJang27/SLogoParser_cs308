package slogo.Commands;

import slogo.BackEndExternal.TurtleStatus;
import java.util.*;

public class Forward implements Command {

    public static final int NUM_ARGS = 1;

    double steps;

    public Forward(double pixel){
        steps = pixel;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaX = steps*Math.sin(ts.getBearing());
        double deltaY = 0 - steps*Math.cos(ts.getBearing()); //TODO: Why is this negative?
        return Command.moveDelta(ts, ret, deltaX, deltaY);
    }

    @Override
    public double returnValue() {
        return (double)steps;
    }

    @Override
    public int getNumArguments() { return NUM_ARGS; }
}