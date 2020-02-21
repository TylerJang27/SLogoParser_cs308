package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.*;

/**
 *
 * @author Lucy Gu
 */
public class Left implements TurtleCommand {

    public static final int NUM_ARGS = 1;

    private double degree;

    public Left(double turn){
        degree = turn;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaHeading = 0-degree;
        return Collections.unmodifiableCollection(TurtleCommand.turnDeltaHeading(ts, ret, deltaHeading));
    }


    @Override
    public double returnValue() {
        return degree;
    }

}
