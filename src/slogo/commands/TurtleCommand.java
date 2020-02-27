package slogo.commands;
import slogo.backendexternal.TurtleStatus;

import java.util.*;

/**
 *
 * @author Lucy Gu, Tyler Jang
 */
public interface TurtleCommand extends Command {


    static Collection<TurtleStatus> move(TurtleStatus ts, Collection<TurtleStatus> ret,
                                         double deltaX, double deltaY, double xMax, double yMax,  String mode){
        if(mode.equals(MODES[1])){
            return TurtleCommand.moveDeltaEdge(ts, ret, deltaX, deltaY);
        }
        else if(mode.equals(MODES[2])){
            return TurtleCommand.moveDeltaWrap(ts, ret, deltaX, deltaY);
        }
        else {
            return TurtleCommand.moveDelta(ts, ret, deltaX, deltaY);
        }
    }

<<<<<<< HEAD
    /**
     * Simplest movement mode, where the turtle's destination is simply specified by the amount it needs to move in the X and Y
     * directions. The x and y values can be theoretically off-screen and there will be no changes made to the turtle status. A
     * new turtle status is added to the list, which will be the endpoint of the movement. Trail is set to true since there will
     * not be toroidal movement of the turtle and theoretically a trail will be left (unless pen is up). Bearing, visibility, pen status,
     * and pen color remains the same as the initial turtle status provided
     *
     * @param ts        Initial status of the turtle before the method executes
     * @param ret       List of pre-established turtle status that will be added onto
     * @param deltaX    amount that the turtle needs to move in the X direction
     * @param deltaY    amount that the turtle needs to move in the Y direction
     * @return          the list of turtle statuses after moveDelta has been executed
     */
    static List<TurtleStatus> moveDelta(TurtleStatus ts, List<TurtleStatus> ret, double deltaX, double deltaY) {
=======






    static Collection<TurtleStatus> moveDelta(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {
>>>>>>> origin/master
        ret.add(new TurtleStatus(ts.getX()+deltaX, ts.getY()+deltaY, ts.getBearing(),
                true, ts.getVisible(), ts.getPenDown(), ts.getPenColor()));
        return ret;
    }











    static Collection<TurtleStatus> moveDeltaWrap(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {

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

<<<<<<< HEAD
    /**
     * Checks if the current x and y position exceeds the maximum (needs wrapping). If either variable requires wrapping, add two turtle
     * status that represents the turtle moving to the edge of the screen, and appearing at the other edge, to the list. Moving to the edge of
     * the screen has its trails variable set to true since theoretically a trial can be drawn between the previous and the current turtle status;
     * the turtle status that represents turtle appearing at the other edge of the Afterwards,
     * decrement/increment variable value depending on if the variable value is positive/negative.
     *
     * @param ts
     * @param x
     * @param y
     * @param xMax
     * @param yMax
     * @param ret
     * @return
     */
    static double[] wrap(TurtleStatus ts, double x, double y, double xMax, double yMax, List<TurtleStatus> ret){
        if(x>xMax){
            ret.add(new TurtleStatus(xMax, y, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            ret.add(new TurtleStatus(-xMax, y, ts.getBearing(), false , ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            x = x - 2 * xMax;
        }
        if(x<-xMax){
            ret.add(new TurtleStatus(-xMax, y, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            ret.add(new TurtleStatus(xMax,  y, ts.getBearing(), false, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            x = x + 2 * xMax;
        }
        if(y>yMax){
            ret.add(new TurtleStatus(x, yMax, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            ret.add(new TurtleStatus(x, -yMax, ts.getBearing(), false, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            y = y - 2 * yMax;
        }
        if(y<-yMax){
            ret.add(new TurtleStatus(x, -yMax, ts.getBearing(), true, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            ret.add(new TurtleStatus(x, yMax, ts.getBearing(), false, ts.getVisible(),ts.getPenDown(),ts.getPenColor()));
            y = y + 2 * yMax;
        }
        return new double[]{x, y};
    }


    /**
     * Moves the turtle deltaX in the x direction and deltaY in the y direction, provided that the turtle cannot move past the
     * edge of the display screen. For example, moving the turtle forward 500 in a coordinate system with maximum y being 100 will
     * move the turtle to (0,100). The location of the end point for the turtle (x and y) are calculated, then changed to reflect
     * the fact that the turtle cannot go beyond xMax and yMax (using the edge method. A new turtle status is added to the list
     * of turtle status, which specifies the end point of the turtle movement. Since there is no toroidal movement, trail is set to be true
     *
     * @param ts        the initial status of the turtle before moving
     * @param ret       a pre-established list of turtle status, onto which we will add the endpoint of the turtle movement
     * @param deltaX    the amount the turtle needs to move in the x directionT
     * @param deltaY    the amount the turtle needs to move in the y direction
     * @param xMax      the maximum x that the turtle cannot go beyond
     * @param yMax      the maximum y that the turtle cannot go beyond
     * @return          the list of turtle statuses after moveDeltaEdge has been executed
     */
    static List<TurtleStatus> moveDeltaEdge(TurtleStatus ts, List<TurtleStatus> ret, double deltaX, double deltaY, double xMax, double yMax) {
=======









    static Collection<TurtleStatus> moveDeltaEdge(TurtleStatus ts, Collection<TurtleStatus> ret, double deltaX, double deltaY) {
>>>>>>> origin/master
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
