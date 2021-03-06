package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.TurtleCommand;

import java.util.*;
import java.util.jar.Manifest;

/**
 *
 * @author Lucy Gu
 */
public class Forward implements TurtleCommand {

    public static final int NUM_ARGS = 1;

    private Command arg1;
    private double xMax;
    private double yMax;
    private String mode;
    private double returnVal;

    public Forward(Command argA, double maxX, double maxY, String mode){
        arg1 = argA;
        xMax = maxX;
        yMax = maxY;
        this.mode = mode;
    }


    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(manifest));
        returnVal = arg1.returnValue();

        //TODO: MAY HAVE TO ADD SOMETHING LIKE:
        //  TurtleStatus currentStatus = (ret.isEmpty()) ? ts : ret.get(ret.size()-1);
        //  We'll see during testing

        double deltaX = returnVal*Math.sin(Math.toRadians(manifest.getActiveState().getBearing()));
        double deltaY = -1*returnVal*Math.cos(Math.toRadians(manifest.getActiveState().getBearing())); //TODO: Why is this negative?
        return TurtleCommand.move(manifest, ret, deltaX, deltaY, xMax, yMax, mode);
    }

    @Override
    public double returnValue() {
        return returnVal;
    }

}
