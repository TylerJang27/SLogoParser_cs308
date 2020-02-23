package slogo.backendexternal;

import javafx.scene.paint.Color;

/**
 * @author Lucy Gu, Tyler Jang
 */
public class TurtleStatus {
    private double x;
    private double y;
    private double bearing;
    private boolean smooth;
    private PenModel penModel;
    private boolean turtleVisible;

    public TurtleStatus(double xPos, double yPos, double bearing, boolean smooth, boolean visible, PenModel penModel){
        this.x = xPos;
        this.y = yPos;
        this.bearing = bearing;
        this.smooth = smooth;
        this.turtleVisible = visible;
        this.penModel = penModel;
    }

    public TurtleStatus(double xPos, double yPos, double bearing, boolean smooth, boolean visible, boolean penDown, Color penColor) {
        this(xPos, yPos, bearing, smooth, visible, new PenModel(penDown, penColor));
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

    public boolean getSmooth(){
        return smooth;
    }

    public boolean getPenDown() { return smooth && penModel.getPenDown(); }

    public Color getPenColor() { return penModel.getPenColor(); }

    public boolean getVisible() { return turtleVisible; }
}
