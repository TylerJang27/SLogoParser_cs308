package slogo.backendexternal;

import javafx.scene.paint.Color;

/**
 * Class used to model the pen on the backend by storing information about its color and up/down status.
 *
 * @author Tyler Jang
 */
public class PenModel {
    private boolean penDown;
    private Color penColor;

    /**
     * Constructor for PenModel, used to apply a down status and color.
     *
     * @param down whether or not the pen should be drawing.
     * @param pen  the Color of the pen.
     */
    public PenModel (boolean down, Color pen) {
        penDown = down;
        penColor = pen;
    }

    /**
     * Default Constructor for PenModel, used to set a drawing status of true and a black color.
     */
    public PenModel() {
        this(true, Color.BLACK);
    }

    /**
     * Retrieves the penColor.
     *
     * @return penColor.
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     * Retrieves the penDown status.
     *
     * @return penDown.
     */
    public boolean getPenDown() {
        return penDown;
    }

}
