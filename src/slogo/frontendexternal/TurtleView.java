package slogo.frontendexternal;

import java.awt.Color;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class TurtleView {
  private int xPos;
  private int yPos;
  private ImageView imageView;


  public TurtleView() {
    xPos = 0;
    yPos = 0;
    imageView = new ImageView((Element) Color.GREEN);
  }

  public int getxPos() {
    return xPos;
  }

  public int getyPos() {
    return yPos;
  }

  public ImageView getImageView() {
    return imageView;
  }






}
