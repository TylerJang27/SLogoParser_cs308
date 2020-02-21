package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.Collection;

/**
 *
 * @author Tyler Jang, Lucy Gu
 */
public interface Command {
    public static final int NUM_ARGS = 0;
    double xMax = 100;
    double yMax = 100;        //TODO: change to front end display size
    public final static String[] MODES = {"normal", "edge", "toroidal"};

    public Collection<TurtleStatus> execute(TurtleStatus ts, String mode);
    public double returnValue();



    static Collection<TurtleStatus> move(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY, double xMax, double yMax,  String mode){
        if(mode.equals(MODES[1])){
            return Command.moveDeltaEdge(ts, ret, deltaX, deltaY);
        }
        else if(mode.equals(MODES[2])){
            return Command.moveDeltaWrap(ts, ret, deltaX, deltaY);
        }
        else {
            return Command.moveDelta(ts, ret, deltaX, deltaY);
        }
    }







    static Collection<TurtleStatus> moveDelta(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {
        if(!ts.getSmooth()){
            ret.add(new TurtleStatus(ts.getX()+deltaX, ts.getY()+deltaY, ts.getBearing(), true));
            return ret;
        }
        for(int i = 0; i<100; i++){              //TODO: REMOVE SMOOTH MAGIC VALUE, maybe front end should do this for animation purposes?
            double x = ts.getX()+deltaX*(i+1)/100;
            double y = ts.getY()+deltaY*(i+1)/100;
            ret.add(new TurtleStatus(x, y, ts.getBearing(), ts.getSmooth()));
        }
        return ret;
    }











    static Collection<TurtleStatus> moveDeltaWrap(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {
        if(!ts.getSmooth()){
            while(ts.getX()+deltaX>xMax){
                ret.add(new TurtleStatus(xMax, ts.getY()+deltaY, ts.getBearing(), ts.getSmooth()));
            }
            ret.add(new TurtleStatus(ts.getX()+deltaX, ts.getY()+deltaY, ts.getBearing(), ts.getSmooth()));
            return ret;
        }





        double x = ts.getX();
        double y = ts.getY();
        for(int i = 1; i<101; i++){
            x+=deltaX*i/100;
            y+=deltaY*i/100;
            if(x>xMax){
                ret.add(new TurtleStatus(xMax, y, ts.getBearing(), ts.getSmooth()));
                ret.add(new TurtleStatus(-xMax, y, ts.getBearing(), ts.getSmooth()));
                x = x - xMax;
            }
            if(x<-xMax){
                ret.add(new TurtleStatus(-xMax, y, ts.getBearing(), ts.getSmooth()));
                ret.add(new TurtleStatus(xMax, y, ts.getBearing(), ts.getSmooth()));
                x = x + xMax;
            }
            if(y>yMax){
                ret.add(new TurtleStatus(x, yMax, ts.getBearing(), ts.getSmooth()));
                ret.add(new TurtleStatus(x, -yMax, ts.getBearing(), ts.getSmooth()));
                y = y - yMax;
            }
            if(y<-yMax){
                ret.add(new TurtleStatus(x, -yMax, ts.getBearing(), ts.getSmooth()));
                ret.add(new TurtleStatus(y, yMax, ts.getBearing(), ts.getSmooth()));
                y = y + yMax;
            }
            ret.add(new TurtleStatus(x, y, ts.getBearing(), ts.getSmooth()));
        }
        return ret;
    }










    static Collection<TurtleStatus> moveDeltaEdge(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {
        double x = ts.getX()+deltaX;
        double y = ts.getY()+deltaY;
        if(!ts.getSmooth()){
            x = edge(x,xMax);
            y = edge(y,yMax);
            ret.add(new TurtleStatus(x,y, ts.getBearing(), ts.getSmooth()));
            return ret;
        }
        for(int i = 0; i<100; i++){
            double xPos = ts.getX()+deltaX*(i+1)/100;
            double yPos = ts.getY()+deltaY*(i+1)/100;
            xPos = edge(xPos,xMax);
            yPos = edge(yPos,yMax);
            ret.add(new TurtleStatus(xPos, yPos, ts.getBearing(), ts.getSmooth()));
        }
        return ret;
    }

    static double edge(double position, double size){
        if(position>size) position = size;
        if(position<-size) position = -size;
        return position;
    }











    static Collection<TurtleStatus> turnDeltaHeading(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaHeading) {
        if(!ts.getSmooth()){
            ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing()+deltaHeading, ts.getSmooth()));
            return ret;
        }
        for(int i = 0; i<10; i++){
            ret.add(new TurtleStatus(ts.getX(), ts.getY(), ts.getBearing()+deltaHeading*(i+1)/10, ts.getSmooth()));
        }
        return ret;
    }
}
