package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;
import java.util.ArrayList;
import java.util.Collection;

public class Constant implements TurtleCommand {

    double value;

    public Constant(double c){
        value = c;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return new ArrayList<>();
    }

    @Override
    public double returnValue() {
        return value;
    }
}
