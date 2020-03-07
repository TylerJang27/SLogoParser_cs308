package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.TurtleCommand;

import java.util.ArrayList;
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
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(manifest));
        ret.addAll(arg2.execute(manifest));

        double deltaX = arg1.returnValue() - manifest.getActiveState().getX();
        double deltaY = arg2.returnValue() + manifest.getActiveState().getY();

        if(deltaY!=0) {
            degreeMoved = Math.toDegrees(Math.atan((deltaX) / (deltaY))) - manifest.getActiveState().getBearing();
        }
        else{
            degreeMoved = 0 -  manifest.getActiveState().getBearing();
        }
        if(deltaY<0) degreeMoved+=180;


        return (TurtleCommand.turnDeltaHeading(manifest, ret, degreeMoved));
    }


    @Override
    public double returnValue() {
        return degreeMoved;
    }

}
