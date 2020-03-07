package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public class ClearScreen implements TurtleCommand {

    public static final int NUM_ARGS = 0;

    private TurtleCommand go;
    private Runnable runnable;

    public ClearScreen(double xMax, double yMax, String mode, Runnable runnable){
        go = new Home(xMax,yMax,mode);
        this.runnable = runnable;
    }

    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = go.execute(manifest);
        //List<TurtleStatus> ret = new ArrayList<>();
        TurtleStatus last = manifest.getActiveState();
        TurtleStatus next = new TurtleStatus(last.getID(), 0, 0, 0.0, false, last.getVisible(), last.getPenDown());
        //next.setClear();
        next.setRunnable(()->runnable.run());
        ret.add(next);
        return ret;
    }


    @Override
    public double returnValue() {
        return go.returnValue();
    }

//    public static boolean toClear() {
//        return true; //TODO: MAKE SURE THIS IMPLEMENTATION WORKS
//    }

}
