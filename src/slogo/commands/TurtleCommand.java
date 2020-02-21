package slogo.commands;
import slogo.backendexternal.TurtleStatus;

import java.util.*;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public interface TurtleCommand extends Command {

    static Collection<TurtleStatus> moveDelta(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {
        //if(!ts.getSmooth()){
            ret.add(new TurtleStatus(ts.getX()+deltaX, ts.getY()+deltaY, ts.getBearing(), true, ts.getVisible(), ts.getPenDown(), ts.getPenColor()));
        //    return ret;
        //}

        /*for(int i = 0; i<10; i++){              //TODO: REMOVE SMOOTH MAGIC VALUE, maybe front end should do this for animation purposes?
            double x = ts.getX()+deltaX*(i+1)/10;
            double y = ts.getY()+deltaY*(i+1)/10;
            ret.add(new TurtleStatus(x, y, ts.getBearing(), ts.getSmooth(), ts.getVisible(), ts.getPenDown(), ts.getPenColor()));
        }*/

        return ret;
    }

    static Collection<TurtleStatus> turnDeltaHeading(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaHeading) {
        if(!ts.getSmooth()){
            ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing()+deltaHeading, true, ts.getVisible(), ts.getPenDown(), ts.getPenColor()));
            return ret;
        }
        for(int i = 0; i<10; i++){
            ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing()+deltaHeading*(i+1)/10, true, ts.getVisible(), ts.getPenDown(), ts.getPenColor()));
        }
        return ret;
    }
}
