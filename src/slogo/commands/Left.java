package slogo.commands;

import slogo.backendexternal.TurtleStatus;
import java.util.*;

public class Left implements Command {

    public static final int NUM_ARGS = 1;

    double degree;

    public Left(double turn){
        degree = turn;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaHeading = 0-degree;
        return Command.turnDeltaHeading(ts, ret, deltaHeading);
    }


    @Override
    public double returnValue() {
        return (double)degree;
    }

    @Override
    public int getNumArguments() { return NUM_ARGS; }
}
