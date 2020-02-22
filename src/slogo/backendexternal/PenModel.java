package slogo.backendexternal;

import javafx.scene.paint.Color;

/**
 * This is class is used to model the pen on the backend by storing information about its color and up/down status.
 *
 * @author Tyler Jang
 */
public class PenModel {
    private boolean penDown;
    private Color penColor;

    public PenModel (boolean down, Color pen) {
        penDown = down;
        penColor = pen;
    }

    public PenModel() {
        this(true, Color.BLACK);
    }

    public Color getPenColor() {
        return penColor;
    }

    public boolean getPenDown() {
        return penDown;
    }

}
