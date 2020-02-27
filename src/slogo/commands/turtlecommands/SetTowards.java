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
public class SetTowards implements TurtleCommand {

    public static final int NUM_ARGS = 2;

    private Command arg1;
    private Command arg2;
    private double degreeMoved = 0;

    public SetTowards(Command argA, Command argB){
        arg1 = argA;
        arg2 = argB;
    }


    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        ret.addAll(arg2.execute(ts));

        double deltaX = arg1.returnValue() - ts.getX();
        double deltaY = arg2.returnValue() - ts.getY();

        degreeMoved = Math.toDegrees(Math.atan((deltaX)/(-1*deltaY))) - ts.getBearing();
        if(deltaY>0) degreeMoved+=180;

        return (TurtleCommand.turnDeltaHeading(ret.get(ret.size()-1), ret, degreeMoved));
    }


    @Override
    public double returnValue() {
        return degreeMoved;
    }

}
