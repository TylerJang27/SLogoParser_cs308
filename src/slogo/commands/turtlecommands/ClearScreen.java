package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.Collection;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public class ClearScreen implements TurtleCommand {

    public static final int NUM_ARGS = 0;

    private TurtleCommand go;

    public ClearScreen(){
        go = new Home();
        //TODO: NEED ADDITIONAL PARAMETER QUALIFYING AS A RESET:
        //Could add a null to a collection of TurtleStatus, stuff, which the front
        //could interpret as remove everything and go from there
        //need to keep in mind sequential order of things
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
