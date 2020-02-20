package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SetHeading implements Command {

    public static final int NUM_ARGS = 1;

    private double degree;
    private double degreeMoved = 0;

    public SetHeading(double heading){
        degree = heading;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        degreeMoved = degree - ts.getBearing();
        Collection<TurtleStatus> ret = new ArrayList<>();
        return Collections.unmodifiableCollection(Command.turnDeltaHeading(ts, ret, degreeMoved));
    }


    @Override
    public double returnValue() {
        return degreeMoved;
    }

}
