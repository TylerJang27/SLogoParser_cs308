package slogo.frontendexternal;

import java.awt.Color;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class TurtleView {
  private int myXPos;
  private int myYPos;
  private ImageView myImageView;


  public TurtleView() {
    myXPos = 0;
    myYPos = 0;
    myImageView = new ImageView((Element) Color.GREEN);
  }

  public int getxPos() {
    return myXPos;
  }

  public int getyPos() {
    return myYPos;
  }

  public ImageView getImageView() {
    return myImageView;
  }

  public void setxPos(int xPos) {
    myXPos = xPos;
  }

  public void setyPos(int yPos) {
    myYPos = yPos;
  }

  public void setMyImageView(ImageView imageView) {
    myImageView = imageView;
  }

}
