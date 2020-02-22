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
public class SetPosition implements TurtleCommand {

    public static final int NUM_ARGS = 2;

    private Command arg1;
    private Command arg2;
    private double distance = 0;
    private double xMax;
    private double yMax;
    private String mode;

    public SetPosition(Command argA, Command argB, double maxX, double maxY, String mode){
        arg1 = argA;
        arg2 = argB;
        xMax = maxX;
        yMax = maxY;
        this.mode = mode;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {

        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        ret.addAll(arg2.execute(ts));

        double deltaX = arg1.returnValue() - ts.getX();
        double deltaY = arg2.returnValue() - ts.getY();
        distance = Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));

        double deltaHeading = Math.tan((deltaX)/(deltaY)) - ts.getBearing();
        TurtleCommand.turnDeltaHeading(ret.get(ret.size()-1), ret, deltaHeading);
        TurtleCommand.move(ret.get(ret.size()-1), ret, deltaX, deltaY, xMax, yMax, mode);
        return (ret);
    }


    @Override
    public double returnValue() {
        return distance;
    }


}
