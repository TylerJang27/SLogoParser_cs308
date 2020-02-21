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
public class Right implements TurtleCommand {

    public static final int NUM_ARGS = 1;

    private double degree;

    public Right(double turn){
        degree = turn;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaHeading = degree;
        return Collections.unmodifiableCollection(TurtleCommand.turnDeltaHeading(ts, ret, deltaHeading));
    }


    @Override
    public double returnValue() {
        return degree;
    }

}
