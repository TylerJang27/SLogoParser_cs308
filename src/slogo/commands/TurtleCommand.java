package slogo.commands;
import slogo.backendexternal.TurtleStatus;

import java.util.*;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public interface TurtleCommand extends Command {
    public final static String[] MODES = {"normal", "edge", "toroidal"};

    static Collection<TurtleStatus> move(TurtleStatus ts, Collection<TurtleStatus> ret,
                                         double deltaX, double deltaY, double xMax, double yMax,  String mode){
        if(mode.equals(MODES[1])){
            return TurtleCommand.moveDeltaEdge(ts, ret, deltaX, deltaY, xMax, yMax);
        }
        else if(mode.equals(MODES[2])){
            return TurtleCommand.moveDeltaWrap(ts, ret, deltaX, deltaY,  xMax, yMax);
        }
        else {
            return TurtleCommand.moveDelta(ts, ret, deltaX, deltaY , xMax, yMax);
        }
    }


    static Collection<TurtleStatus> moveDelta(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY, double xMax, double yMax) {
        ret.add(new TurtleStatus(ts.getX()+deltaX, ts.getY()+deltaY, ts.getBearing(),
                true, ts.getVisible(), ts.getPenDown(), ts.getPenColor()));
        return ret;
    }


    static Collection<TurtleStatus> moveDeltaWrap(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY, double xMax, double yMax) {

        double x = ts.getX();
        double y = ts.getY();
        double steps = Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
        for(int i = 1; i<(steps+1); i++){
            x+=deltaX*i/steps;
            y+=deltaY*i/steps;
            if(x>xMax){
                ret.add(new TurtleStatus(xMax, y, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                ret.add(new TurtleStatus(-xMax, y, ts.getBearing(), false , ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                x = x - xMax;
            }
            if(x<-xMax){
                ret.add(new TurtleStatus(-xMax, y, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                ret.add(new TurtleStatus(xMax,  y, ts.getBearing(), false, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                x = x + xMax;
            }
            if(y>yMax){
                ret.add(new TurtleStatus(x, yMax, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                ret.add(new TurtleStatus(x, -yMax, ts.getBearing(), false, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                y = y - yMax;
            }
            if(y<-yMax){
                ret.add(new TurtleStatus(x, -yMax, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                ret.add(new TurtleStatus(y, yMax, ts.getBearing(), false, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
                y = y + yMax;
            }
        }
        ret.add(new TurtleStatus(x,y,ts.getBearing(),true,ts.getVisible(),ts.getPenDown(), ts.getPenColor()));
        return ret;
    }


    static Collection<TurtleStatus> moveDeltaEdge(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY, double xMax, double yMax) {
        double x = ts.getX()+deltaX;
        double y = ts.getY()+deltaY;
        x = edge(x,xMax);
        y = edge(y,yMax);
        ret.add(new TurtleStatus(x,y, ts.getBearing(), true,ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
        return ret;
    }

    static double edge(double position, double size){
        if(position>size) position = size;
        if(position<-size) position = -size;
        return position;
    }


    static Collection<TurtleStatus> turnDeltaHeading(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaHeading) {
        ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing()+deltaHeading,
                false, ts.getVisible(), ts.getPenDown(), ts.getPenColor()));
        return ret;
    }
}
