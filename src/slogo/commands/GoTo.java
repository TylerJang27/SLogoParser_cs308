package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GoTo implements Command {

    public static final int NUM_ARGS = 2;

    private double xPos;
    private double yPos;
    private double distance = 0;

    public GoTo(double x, double y){
        xPos = x;
        yPos = y;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode) {
        distance = Math.sqrt(Math.pow(ts.getX()-xPos,2)+Math.pow(ts.getY()-yPos,2));
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaX = xPos - ts.getX();
        double deltaY = xPos - ts.getY();
        double deltaHeading = Math.tan((ts.getX()-xPos)/(ts.getY()-yPos)) - ts.getBearing();
        Command.turnDeltaHeading(ts, ret, deltaHeading);
        Command.moveDelta(ts, ret, deltaX, deltaY);
        return Collections.unmodifiableCollection(ret);
    }


    @Override
    public double returnValue() {
        return distance;
    }


}
