package slogo.commands;

import slogo.backendexternal.TurtleStatus;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Right implements Command {

    public static final int NUM_ARGS = 1;

    private double degree;

    public Right(double turn){
        degree = turn;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaHeading = degree;
        return Collections.unmodifiableCollection(Command.turnDeltaHeading(ts, ret, deltaHeading));
    }


    @Override
    public double returnValue() {
        return degree;
    }

}
