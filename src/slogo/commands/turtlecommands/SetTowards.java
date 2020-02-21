package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Lucy Gu
 */
public class SetTowards implements TurtleCommand {

    public static final int NUM_ARGS = 2;

    private double xFacing;
    private double yFacing;
    private double degreeMoved = 0;

    public SetTowards(double x, double y){
        xFacing = x;
        yFacing = y;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        double newDirection = Math.toDegrees(Math.atan((ts.getX()-xFacing)/(ts.getY()-yFacing)));
        degreeMoved = newDirection - ts.getBearing();
        Collection<TurtleStatus> ret = new ArrayList<>();
        return Collections.unmodifiableCollection(TurtleCommand.turnDeltaHeading(ts, ret, degreeMoved));
    }


    @Override
    public double returnValue() {
        return degreeMoved;
    }

}
