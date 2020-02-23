package slogo.frontendexternal;

import java.awt.Color;
import javafx.beans.property.SimpleBooleanProperty;

public class PenView {
  private SimpleBooleanProperty isDown;
  private Color penColor;
  private double lineThickness;
  private double xPos;
  private double yPos;

  public PenView() {
    isDown = new SimpleBooleanProperty(false);
    penColor = Color.BLACK;
    lineThickness = .5;
    xPos = 0;
    yPos = 0;
  }

  public void setPenDown() {

  }

}
