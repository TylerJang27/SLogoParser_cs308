package slogo.backendexternal;

import javafx.scene.paint.Color;

/**
 * @author Lucy Gu, Tyler Jang
 */
public class TurtleStatus {
    private double x;
    private double y;
    private double bearing;
<<<<<<< HEAD
    private boolean leavesTrail;
=======
    private boolean smooth;
>>>>>>> origin/master
    private PenModel penModel;
    private boolean turtleVisible;
    private boolean clear;

    public TurtleStatus(double xPos, double yPos, double bearing, boolean smooth, boolean visible, PenModel penModel){
        this.x = xPos;
        this.y = yPos;
        this.bearing = bearing;
<<<<<<< HEAD
        this.leavesTrail = smooth;
=======
        this.smooth = smooth;
>>>>>>> origin/master
        this.turtleVisible = visible;
        this.penModel = penModel;
        this.clear = false;
    }

<<<<<<< HEAD
    /**
     * Create turtle status using the inputs
     *
     * @param xPos sets the x value of turtle status
     * @param yPos sets the y value of turtle status
     * @param bearing set the bearing (degrees turned away from north) of the turtle status
     * @param trail set whether there should be a line connecting this and the previous turtle status
     * @param visible set whether the turtle is visible or hiding
     * @param penDown set whether the turtle leaves a trail (penDown and penColor will create a new PenModel object to store in turtle status)
     * @param penColor set the color of trail the turtle leaves (penDown and penColor will create a new PenModel object to store in turtle status)
     */
    public TurtleStatus(double xPos, double yPos, double bearing, boolean trail, boolean visible, boolean penDown, Color penColor) {
        this(xPos, yPos, bearing, trail, visible, new PenModel(penDown, penColor));
=======
    public TurtleStatus(double xPos, double yPos, double bearing, boolean smooth, boolean visible, boolean penDown, Color penColor) {
        this(xPos, yPos, bearing, smooth, visible, new PenModel(penDown, penColor));
>>>>>>> origin/master
    }


    public TurtleStatus() {
        this(0, 0, 0, false, true, new PenModel());
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getBearing(){
        return bearing;
    }

<<<<<<< HEAD
    /**
     * @return if, theoretically, a line should be drawn between the previous and the current
     *         turtle status
     */
    public boolean getTrail(){
        return leavesTrail;
    }

    /**
     * @return true if pen is put down (thus turtle leaves trails) and false if pen is up (turtle leaves no trail)
     */
    public boolean getPenDown() { return penModel.getPenDown(); }

    /**
     * The value of penDraw is determined by both the smooth value and the penDown value
     * leavesTrail specifies if a line should be created between this and the previous turtle
     * status in the list; penDown specified if lines should be drawn at all. A line should only be
     * drawn when both pen is down and smooth is set to be true.
     *
     * @return if a line should be drawn between the previous turtle status and this turtle status
     */
    public boolean getPenDraw() { return leavesTrail && penModel.getPenDown(); } //USED BY THE FRONTEND
=======
    public boolean getSmooth(){
        return smooth;
    }

    public boolean getPenDown() { return smooth && penModel.getPenDown(); }
>>>>>>> origin/master

    public Color getPenColor() { return penModel.getPenColor(); }

    public boolean getVisible() { return turtleVisible; }
<<<<<<< HEAD

    /**
     * Toggles clear to be on for the ClearScreen command.
     */
    public void setClear() { clear = true; }

    /**
     * @return whether or not the screen should be cleared
     */
    public boolean getClear() { return clear; }

    /**
     * @return a string that summarizes the information in this turtle status, including the x location, y location,
     *         bearing, whether the turtle theoretically leaves a trail between the previous and this status,
     *         visibility of the turtle (hiding or not), status of the pen used to draw turtle's trail (down or up), and color
     *         of pen used to draw trail
     */
    @Override
    public String toString() {
        return String.format("%f, %f \t %f \t%b \t%b \t%b \t%s", x, y, bearing, leavesTrail, turtleVisible, penModel.getPenDown(), getPenColor());
    }
=======
>>>>>>> origin/master
}
