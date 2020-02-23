package slogo.frontendexternal;

import java.awt.Color;
import javafx.beans.property.SimpleBooleanProperty;

public class PenView {
  private SimpleBooleanProperty isDown;
  private Color myPenColor;
  private double myLineThickness;
  private double myXPos;
  private double myYPos;

  public PenView() {
    isDown = new SimpleBooleanProperty(false);
    myPenColor = Color.BLACK;
    myLineThickness = .5;
    myXPos = 0;
    myYPos = 0;
  }

  public void setPenDown() {
    isDown.set(true);
  }


  public boolean getPenDown() {
    return isDown.get();
  }

  public double getMyLineThickness() {
    return myLineThickness;
  }

  public void setMyLineThickness(double myLineThickness) {
    this.myLineThickness = myLineThickness
  }



}
