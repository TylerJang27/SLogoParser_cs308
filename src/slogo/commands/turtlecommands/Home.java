package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.Collection;

/**
 *
 * @author Lucy Gu
 */
public class Home implements TurtleCommand {

    public static final int NUM_ARGS = 0;

    private static final double homeX = 0;
    private static final double homeY = 0;
    private TurtleCommand go;

    public Home(){
        go = new SetPosition(homeX, homeY);
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode) {
        return go.execute(ts, mode);
    }


    @Override
    public double returnValue() {
        return go.returnValue();
    }


}
