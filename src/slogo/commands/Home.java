package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.Collection;

/**
 *
 * @author Lucy Gu
 */
public class Home implements Command {

    public static final int NUM_ARGS = 0;

    private double xPos = 0;//WIDTH/2;      //TODO: What do we put here?
    private double yPos = 0;//HEIGHT/2;
    private Command go;

    public Home(){
        go = new SetPosition(xPos, yPos);
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
