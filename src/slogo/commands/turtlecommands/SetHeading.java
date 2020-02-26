package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.TurtleCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Lucy Gu
 */
public class SetHeading implements TurtleCommand {

    public static final int NUM_ARGS = 1;

    private double degreeMoved = 0;
    private Command arg1;
    private double returnVal;

    public SetHeading(Command argA){
        arg1 = argA;
    }


    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {

        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        returnVal = arg1.returnValue();
        degreeMoved = returnVal - ts.getBearing();

        return (TurtleCommand.turnDeltaHeading(ret.get(ret.size()-1), ret, degreeMoved));
    }


    @Override
    public double returnValue() {
        return degreeMoved;
    }

}
