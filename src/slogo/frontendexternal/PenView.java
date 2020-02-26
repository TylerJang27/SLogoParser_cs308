package slogo.frontendexternal;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * This class sets up the View of the Pen object for the GUI to get data from
 * @author Shruthi Kumar
 */
public class PenView {
  private SimpleBooleanProperty isDown;
  private Color myPenColor;
  private double myLineThickness;
  private double myXPos;
  private double myYPos;
  private List<Line> myLines;

  /**
   * Constructor for pen view
   */
  public PenView() {
    isDown = new SimpleBooleanProperty(false);
    myPenColor = Color.BLACK;
    myLineThickness = .5;
    myLines = new ArrayList<>();
  }

  /**
   * Constructor for pen view
   */
  public PenView(boolean down, Point start, Point end) {
    isDown = new SimpleBooleanProperty(down);
    myLineThickness = .5;
    myLines = new ArrayList<>();
  }

  /**
   * Draws the pen trail
   * @param start: starting point of trail
   * @param end: end point of trail
   */
  public void drawTrail(Point start, Point end) {
    Line penTrail = new Line(start.getX(), start.getY(), end.getX(), end.getY());
    penTrail.setStroke(myPenColor);
    penTrail.setStrokeWidth(myLineThickness);

  }

  //getPenDraw();

  /**
   * Sets whether or not the pen should be down
   * @param setUpOrDown : T/F depending on if pen should be down
   */
  public void setPenDown(boolean setUpOrDown) {
    isDown.set(setUpOrDown);
  }

  /**
   * Returns whether or not the pen is down
   * @return isDown.get(): T/F depending on if the pen is down
   */
  public boolean getPenDown() {
    return isDown.get();
  }

  /**
   * Returns line thickness
   * @return myLineThickness : line thickness
   */
  public double getMyLineThickness() {
    return myLineThickness;
  }


  /**
   * Sets line thickness
   * @param lineThickness : line thickness
   */
  public void setMyLineThickness(double lineThickness) {
    this.myLineThickness = lineThickness;
  }

  /**
   * Returns x position of pen
   * @return myXPos : x position of pen
   */
  public double getMyXPos() {
    return myXPos;
  }

  /**
   * Sets x position of pen
   * @param xPos : x position of pen
   */
  public void setMyXPos(int xPos) {
    myXPos = xPos;
  }

  /**
   * Returns y position of pen
   * @return myYPos : y position of pen
   */
  public double getMyYPos() {
    return myYPos;
  }

  /**
   * Sets y position of pen
   * @param yPos : y position of pen
   */
  public void setMyYPos(int yPos) {
    myYPos = yPos;
  }

  /**
   * Returns current pen color
   * @return myPenColor : pen color
   */
  public Color getMyPenColor() {
    return myPenColor;
  }

  /**
   * Sets color of pen
   * @param penColor : color of pen
   */
  public void setMyPenColor(Color penColor) {
    myPenColor = penColor;
  }

  public void updateMyLines(double startX, double startY, double endX, double endY) {
    Line temp = new Line(startX, startY, endX, endY);
    //temp.setStrokeWidth(3.0);
    myLines.add(temp);
  }

  public List<Line> getMyLines() {
    return myLines;
  }




}
