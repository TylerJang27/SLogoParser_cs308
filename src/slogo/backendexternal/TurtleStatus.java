package slogo.backendexternal;

import javafx.scene.paint.Color;

/**
 * a "data" class that contains all the information for the turtle, including information regarding its x and y position, its bearing,
 * whether trail should be left when moved, and information of the pen used to draw the trail
 *
 * @author Lucy Gu, Tyler Jang
 */
public class TurtleStatus {
    private double x;
    private double y;
    private double bearing;
    private boolean leavesTrail;
    private PenModel penModel;
    private boolean turtleVisible;
    private boolean clear;
    private int turtleID;

    /**
     * Creating turtle status with an existing penModel
     *
     * @param xPos sets the x value of turtle status
     * @param yPos sets the y value of turtle status
     * @param bearing set the bearing (degrees turned away from north) of the turtle status
     * @param smooth set whether there should be a line connecting this and the previous turtle status
     * @param visible set whether the turtle is visible or hiding
     * @param penModel existing pen model that will be directly stored in turtle status
     */
    public TurtleStatus(int id, double xPos, double yPos, double bearing, boolean smooth, boolean visible, PenModel penModel){
        this.turtleID = id;
        this.x = xPos;
        this.y = yPos;
        this.bearing = bearing;
        this.leavesTrail = smooth;
        this.turtleVisible = visible;
        this.penModel = penModel;
        this.clear = false;
    }

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
    public TurtleStatus(int id, double xPos, double yPos, double bearing, boolean trail, boolean visible, boolean penDown, Color penColor) {
        this(id, xPos, yPos, bearing, trail, visible, new PenModel(penDown, penColor));
    }

    /**
     * Create a default turtle status, where the turtle is located at (0,0), has a bearing of 0, and is visible
     */
    public TurtleStatus() {
        this(1, 0, 0, 0, false, true, new PenModel());
    }

    public TurtleStatus(int id) {
        this(id, 0, 0, 0, false, true, new PenModel());
    }


    public int getID(){return turtleID;}

    /**
     * @return the x value of the turtle status
     */
    public double getX(){
        return x;
    }

    /**
     * @return the y value of the turtle status
     */
    public double getY(){
        return y;
    }

    /**
     * @return the degree of turn of the turtle status
     */
    public double getBearing(){
        return bearing;
    }

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

    /**
     * @return the color of the penModel used by this turtle status
     */
    public Color getPenColor() { return penModel.getPenColor(); }

    /**
     * @return whether the turtle is visible or hiding
     */
    public boolean getVisible() { return turtleVisible; }

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
}
