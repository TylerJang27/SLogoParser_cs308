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
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = turn.execute(ts);
        double deltaX = arg1.returnValue() - ret.get(ret.size() - 1).getX();
        double deltaY = -1*arg2.returnValue() - ret.get(ret.size() - 1).getY();
        if(outOfBounds(arg1.returnValue(), arg2.returnValue())) {
            TurtleCommand.move(ret.get(ret.size() - 1), ret, deltaX, deltaY, xMax, yMax, mode);
        }
        else{
            TurtleCommand.moveDelta(ret.get(ret.size() - 1), ret, deltaX, deltaY);
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
