package slogo.frontendexternal;

import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;

/**
 * Creates TurtleView
 * @author Shruthi Kumar
 */
public class TurtleView {
  private double myXPos;
  private double myYPos;
  public Image myImage;
  private TurtleModel turtleModel = new TurtleModel();
  private double myBearing;
  private String TURTLE_IMG = "view/imagesFolder/turtle.png";


  /**
   * Constructor for TurtleView object
   */
  public TurtleView() {
    myXPos = 0;
    myYPos = 0;
    myBearing = 0;
    myImage = new Image("https://vignette.wikia.nocookie.net/tmnt2012series/images/6/63/Raph-rage.png/revision/latest?cb=20170428232825");
  }

  /**
   *  Executes the command that the user enters by doing the action specified in the command
   * @param t : Turtle status that holds command
   */
  public void executeState(TurtleStatus t) {
    //do something
  }

  /**
   * Gets x position of turtle
   * @return myXPos : x position
   */
  public double getMyXPos() {
    return myXPos;
  }

  /**
   * Gets y position of turtle
   * @return myYPos : y position
   */
  public double getMyYPos() {
    return myYPos;
  }

  /**
   * Gets ImageView of turtle
   * @return myImageView : image view of turtle
   */
  //public ImageView getMyImageView() {
    //return myImageView;
  //}

  /**
   * Gets bearing of turtle
   * @return  : image view of turtle
   */
  public double getMyBearing() {
    return myBearing;
  }

  /**
   * sets x position of turtle
   * @param xPos : x position
   */
  public void setMyXPos(double xPos) {
    myXPos = xPos;
  }

  /**
   * sets y position of turtle
   * @param yPos : y position
   */
  public void setMyYPos(double yPos) {
    myYPos = yPos;
  }

  /**
   * sets image view of turtle
   * @param imageView : image view of turtle
   */

  //public void setMyImageView(ImageView imageView) {
    //myImageView = imageView;
  //}


  /**
   * sets bearing of turtle
   * @param degrees : new bearing of turtle
   */
  public void setMyBearing(double degrees) {
    myBearing = degrees;
  }

}