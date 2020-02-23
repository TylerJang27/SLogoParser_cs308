package slogo.frontendexternal;

import java.awt.Color;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import slogo.backendexternal.TurtleModel;

/**
 * Creates TurtleView
 * @author Shruthi Kumar
 */
public class TurtleView {
  private int myXPos;
  private int myYPos;
  private ImageView myImageView;
  private TurtleModel turtleModel = new TurtleModel();


  /**
   * Constructor for TurtleView object
   */
  public TurtleView() {
    myXPos = 0;
    myYPos = 0;
    myImageView = new ImageView((Element) Color.GREEN);
  }

  /**
   * Gets x position of turtle
   * @return myXPos : x position
   */
  public int getMyXPos() {
    return myXPos;
  }

  /**
   * Gets y position of turtle
   * @return myYPos : y position
   */
  public int getMyYPos() {
    return myYPos;
  }

  /**
   * Gets ImageView of turtle
   * @return myImageView : image view of turtle
   */
  public ImageView getMyImageView() {
    return myImageView;
  }

  /**
   * sets x position of turtle
   * @param xPos : x position
   */
  public void setMyXPos(int xPos) {
    myXPos = xPos;
  }

  /**
   * sets y position of turtle
   * @param yPos : y position
   */
  public void setMyYPos(int yPos) {
    myYPos = yPos;
  }

  /**
   * sets image view of turtle
   * @param imageView : image view of turtle
   */
  public void setMyImageView(ImageView imageView) {
    myImageView = imageView;
  }

}
