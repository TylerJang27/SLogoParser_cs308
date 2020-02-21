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
public class SetHeading implements TurtleCommand {

    public static final int NUM_ARGS = 1;

    private double degree;
    private double degreeMoved = 0;

    public SetHeading(double heading){
        degree = heading;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode) {
        degreeMoved = degree - ts.getBearing();
        Collection<TurtleStatus> ret = new ArrayList<>();
        return Collections.unmodifiableCollection(TurtleCommand.turnDeltaHeading(ts, ret, degreeMoved));
    }


    @Override
    public double returnValue() {
        return degreeMoved;
    }

}
