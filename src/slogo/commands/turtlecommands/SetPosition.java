package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.TurtleCommand;

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
    private Command turn;

    public SetPosition(Command argA, Command argB, double maxX, double maxY, String mode){
        arg1 = argA;
        arg2 = argB;
        xMax = maxX;
        yMax = maxY;
        this.mode = mode;
        turn = new SetTowards(arg1, arg2);
    }


    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = turn.execute(manifest);
        double deltaX = arg1.returnValue() - manifest.getActiveState().getX();
        double deltaY = -1*arg2.returnValue() - manifest.getActiveState().getY();
        if(outOfBounds(arg1.returnValue(), arg2.returnValue())) {
            TurtleCommand.move(manifest, ret, deltaX, deltaY, xMax, yMax, mode);
        }
        else{
            TurtleCommand.moveDelta(manifest.getActiveState(), ret, deltaX, deltaY);
            manifest.updateTurtleState(ret.get(ret.size()-1));
        }
        return ret;
    }


    @Override
    public double returnValue() {
        return distance;
    }

    private boolean outOfBounds(double xPos, double yPos){
        return (xPos>xMax || xPos<-xMax || yPos>yMax || yPos<-yMax);
    }


}
