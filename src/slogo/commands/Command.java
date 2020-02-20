package slogo.commands;
import slogo.backendexternal.TurtleStatus;

import java.util.*;

public interface Command {

    public Collection<TurtleStatus> execute(TurtleStatus ts);
    public double returnValue();
    public int getNumArguments();

    //TODO: We may want to consider moving moveDelta to something like TurtleModel (or at the very least separate the front-end animation stuff)
    static Collection<TurtleStatus> moveDelta(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {
        if(!ts.getSmooth()){
            ret.add(new TurtleStatus(ts.getX()+deltaX, ts.getY()+deltaY, ts.getBearing(), ts.getSmooth()));
            return ret;
        }
        for(int i = 0; i<10; i++){              //TODO: REMOVE SMOOTH MAGIC VALUE, maybe front end should do this for animation purposes?
            double x = ts.getX()+deltaX*(i+1)/10;
            double y = ts.getY()+deltaY*(i+1)/10;
            ret.add(new TurtleStatus(x, y, ts.getBearing(), ts.getSmooth()));
        }
        return Collections.unmodifiableCollection(ret);
    }

    static Collection<TurtleStatus> turnDeltaHeading(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaHeading) {
        if(!ts.getSmooth()){
            ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing()+deltaHeading, ts.getSmooth()));
            return ret;
        }
        for(int i = 0; i<10; i++){
            ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing()+deltaHeading*(i+1)/10, ts.getSmooth()));
        }
        return Collections.unmodifiableCollection(ret);
    }
}
