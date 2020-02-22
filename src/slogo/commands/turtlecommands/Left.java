package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.TurtleCommand;

import java.util.*;

/**
 *
 * @author Lucy Gu
 */
public class Left implements TurtleCommand {

    public static final int NUM_ARGS = 1;

    private Command arg1;
    private double returnVal;

    public Left(Command argA){
        arg1 = argA;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        returnVal = arg1.returnValue();

        double deltaHeading = 0-returnVal;
        return TurtleCommand.turnDeltaHeading(ret.get(ret.size()-1), ret, deltaHeading);
    }


    @Override
    public double returnValue() {
        return returnVal;
    }

}
