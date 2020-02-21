package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.Collection;

/**
 *
 * @author Lucy Gu
 */
public class Home implements Command {

    public static final int NUM_ARGS = 0;

    private static final double homeX = 0;
    private static final double homeY = 0;
    private Command go;

    public Home(){
        go = new SetPosition(homeX, homeY);
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return go.execute(ts);
    }


    @Override
    public double returnValue() {
        return go.returnValue();
    }


}
