package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public class ClearScreen implements TurtleCommand {

    public static final int NUM_ARGS = 0;

    private TurtleCommand go;

    public ClearScreen(int xMax, int yMax, String mode){
        go = new Home(xMax,yMax,mode);
        //TODO: NEED ADDITIONAL PARAMETER QUALIFYING AS A RESET:
        //Could add a null to a collection of TurtleStatus, stuff, which the front
        //could interpret as remove everything and go from there
        //need to keep in mind sequential order of things
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = (List<TurtleStatus>) go.execute(ts);
        TurtleStatus last = ret.get(ret.size()-1);
        ret.add(new TurtleStatus(last.getX(), last.getY(), 0.0, false, last.getVisible(), last.getPenDown(), last.getPenColor()));
        return ret;
    }


    @Override
    public double returnValue() {
        return go.returnValue();
    }


}
