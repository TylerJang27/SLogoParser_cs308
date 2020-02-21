package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Towards implements Command {

    public static final int NUM_ARGS = 2;

    private double xFacing;
    private double yFacing;
    private double degreeMoved = 0;

    public Towards(double x, double y){
        xFacing = x;
        yFacing = y;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode) {
        degreeMoved = Math.tan((ts.getX()-xFacing)/(ts.getY()-yFacing)) - ts.getBearing();
        Collection<TurtleStatus> ret = new ArrayList<>();
        return Collections.unmodifiableCollection(Command.turnDeltaHeading(ts, ret, degreeMoved));
    }


    @Override
    public double returnValue() {
        return degreeMoved;
    }

}
