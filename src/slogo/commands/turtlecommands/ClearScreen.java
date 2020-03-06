package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.List;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public class ClearScreen implements TurtleCommand {

    public static final int NUM_ARGS = 0;

    private TurtleCommand go;

    public ClearScreen(double xMax, double yMax, String mode){
        go = new Home(xMax,yMax,mode);
        //TODO: NEED ADDITIONAL PARAMETER QUALIFYING AS A RESET:
        //Could add a null to a collection of TurtleStatus, stuff, which the front
        //could interpret as remove everything and go from there
        //need to keep in mind sequential order of things
    }

    //TODO: MAKE SURE TO DO THE NEW TURTLE MANAGER IMPLEMENTATION

    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = go.execute(manifest);
        TurtleStatus last = ret.get(ret.size()-1);
        TurtleStatus next = new TurtleStatus(last.getID(), last.getX(), last.getY(), 0.0, false, last.getVisible(), last.getPenDown());
        next.setClear();
        ret.add(next);
        return ret;
    }


    @Override
    public double returnValue() {
        return go.returnValue();
    }

    public static boolean toClear() {
        return true; //TODO: MAKE SURE THIS IMPLEMENTATION WORKS
    }

}
