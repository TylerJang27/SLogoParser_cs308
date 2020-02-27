package slogo.frontendexternal;

import java.util.Iterator;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import slogo.backendexternal.TurtleStatus;

/**
 * Creates TurtleView
 * @author Shruthi Kumar
 */
public class TurtleView {
  private double myStartXPos;
  private double myStartYPos;
  private double myEndXPos;
  private double myEndYPos;
  private double myUpdatedXPos;
  private double myUpdatedYPos;

  private double xCor;
  private double yCor;
  private double heading;
  private boolean penDown;
  private boolean showing;

  public Image myImage;
  public ImageView myImageView;

  private PenView penView;
  private double myBearing;
  private boolean isVisible;
  private String TURTLE_IMG = "view/imagesFolder/turtle.png";
  //private PathTransition turtlePath;



  /**
   * Constructor for TurtleView object
   */

  public TurtleView(double x, double y) {
    myStartXPos = x;
    myStartYPos = y;
    myEndXPos = 150;
    myEndYPos = 250;
    myBearing = 0;
    isVisible = true;
    penView = new PenView();
    myImage = new Image("/slogo/view/imagesFolder/raphael.png");
    myImageView =  new ImageView(myImage);
    initializeQueries(myStartXPos, myStartYPos, myBearing);

  }

  private void initializeQueries(double x, double y, double angle) {
    xCor = x;
    yCor = y;
    heading = angle;
    penDown = true;
    showing = true;
  }

  /**
   *  Executes the command that the user enters by doing the action specified in the command
   * @param t : Turtle status that holds command
   */
  public void executeState(List<TurtleStatus> t) {
//    addPenViewLines(t);
    SequentialTransition sequentialTransition = new SequentialTransition();
    sequentialTransition.setNode(this.myImageView);
    Polyline pathLine = new Polyline();
//    Double[] pathPoints = new Double[t.size()*2];
    Iterator<TurtleStatus> iterator = t.iterator();

    int index = 0;

    // directions of commands
    // check for get trail
    // getTrail : if getTrail is true, animate turtle -- if false, just move itd 50
    // getPen : if true, pen down -- if false, pen up
    // set heading, set position
    // error handling, user-defined commands?

    //TODO: TYLER's changes here:
    //for(int i = 0; i < t.size(); i+=2) {
    for(int i = 0; i < t.size() - 1; i++) {
      index = 0;
      Double[] pathPoints = new Double[4];
      TurtleStatus start = t.get(i);
      TurtleStatus end = t.get(i + 1);
      this.myImageView.setVisible(end.getVisible());
      if (end.getBearing() != myBearing) {
        RotateTransition turtleRotate = new RotateTransition(Duration.millis(2500),
            this.myImageView);
        turtleRotate.setFromAngle(start.getBearing());
        turtleRotate.setToAngle(end.getBearing());
        myBearing = end.getBearing();
        sequentialTransition.getChildren().add(turtleRotate);
      }

      else if (checkMovement(start, end) && end.getTrail()) {
        addPenViewLines(start, end);
        System.out.println("hello" + i);
        pathPoints[index] = start.getX();
        pathPoints[index + 1] = start.getY();
        pathPoints[index + 2] = end.getX();
        pathPoints[index + 3] = end.getY();
        pathLine.getPoints().addAll(pathPoints);

        PathTransition turtlePath = new PathTransition(Duration.millis(2500), pathLine,
                this.myImageView);
        sequentialTransition.getChildren().add(turtlePath);
        pathLine = new Polyline();

      } else if (checkMovement(start, end) && !end.getTrail()) { //wraparound case
        System.out.println("HELLO" + i);
        //this.myImageView.setX(end.getX());
        //this.myImageView.setY(end.getY());
        pathPoints[index] = end.getX();
        pathPoints[index + 1] = end.getY();
        pathPoints[index + 2] = end.getX();
        pathPoints[index + 3] = end.getY();
        pathLine.getPoints().addAll(pathPoints);

        PathTransition turtlePath = new PathTransition(Duration.millis(2500), pathLine,
                this.myImageView);
        turtlePath.setDuration(new Duration(0));
        sequentialTransition.getChildren().add(turtlePath);
        pathLine = new Polyline();
      } else {
        pathPoints[index] = start.getX();
        pathPoints[index + 1] = start.getY();
        pathPoints[index + 2] = end.getX();
        pathPoints[index + 3] = end.getY();
        pathLine.getPoints().addAll(pathPoints);
      }

      xCor = myStartXPos + end.getX();
      yCor = myStartYPos + end.getY();
      heading = myBearing;
      penDown = end.getPenDown();
      showing = end.getVisible();

    }

    setMyEndXPos(t.get(t.size()-1).getX());
    setMyEndYPos(t.get(t.size()-1).getY());
//    setMyEndXPos(t.get(t.size()-1).getX());
  //  setMyEndYPos(t.get(t.size()-1).getY());
 //   setMyUpdatedXPos(this.getMyStartXPos() + t.get(t.size() - 1).getX());
   // setMyUpdatedYPos(this.getMyStartYPos() + t.get(t.size() - 1).getY());

    /*PathTransition turtlePath = new PathTransition(Duration.millis(2500), pathLine,
            this.myImageView);
    sequentialTransition.getChildren().add(turtlePath);*/
    System.out.println(sequentialTransition);
    System.out.println(sequentialTransition.getChildren());
    System.out.println(sequentialTransition.getChildren().size());
    if (t.size() > 1) {
      sequentialTransition.play();
    }
  }


  private boolean checkMovement(TurtleStatus start, TurtleStatus end) {
    return start.getX() != end.getX() || start.getY() != end.getY();
  }

  private void addPenViewLines(TurtleStatus start, TurtleStatus end) {
      if(end.getPenDown()) {
        this.penView.updateMyLines(this.getMyStartXPos() + start.getX(), this.getMyStartYPos() + start.getY(), this.getMyStartXPos() + end.getX(), this.getMyStartYPos() + end.getY());
      }
  }

  /**
   * Gets x position of turtle
   * @return myXPos : x position
   */
  public double getMyStartXPos() {
    return myStartXPos;
  }

  /**
   * Gets y position of turtle
   * @return myYPos : y position
   */
  public double getMyStartYPos() {
    return myStartYPos;
  }

  /**
   * Gets x position of turtle
   * @return myXPos : x position
   */
  public double getMyUpdatedXPos() {
    return myUpdatedXPos;
  }

  /**
   * Gets y position of turtle
   * @return myYPos : y position
   */
  public double getMyUpdatedYPos() {
    return myUpdatedYPos;
  }

  /**
   * Gets x position of turtle
   * @return myXPos : x position
   */
  public double getMyEndXPos() {
    return myEndXPos;
  }

  /**
   * Gets y position of turtle
   * @return myYPos : y position
   */
  public double getMyEndYPos() {
    return myEndYPos;
  }



  /**
   * Gets ImageView of turtle
   * @return myImageView : image view of turtle
   */
  public ImageView getMyImageView() {
    return myImageView;
  }

  /**
   * Gets bearing of turtle
   * @return  : image view of turtle
   */
  public double getMyBearing() {
    return myBearing;
  }

  public PenView getPenView() {
    return penView;
  }

  public boolean getIsVisible() {
    return isVisible;
  }

  /**
   * sets x position of turtle
   * @param xPos : x position
   */
  public void setMyStartXPos(double xPos) {
    myStartXPos = xPos;
  }

  /**
   * sets y position of turtle
   * @param yPos : y position
   */
  public void setMyStartYPos(double yPos) {
    myStartYPos = yPos;
  }

  /**
   * sets x position of turtle
   * @param xPos : x position
   */
  public void setMyUpdatedXPos(double xPos) {
    myUpdatedXPos = xPos;
  }

  /**
   * sets y position of turtle
   * @param yPos : y position
   */
  public void setMyUpdatedYPos(double yPos) {
    myUpdatedYPos = yPos;
  }

  /**
   * sets x position of turtle
   * @param xPos : x position
   */
  public void setMyEndXPos(double xPos) {
    myEndXPos = xPos;
  }

  /**
   * sets y position of turtle
   * @param yPos : y position
   */
  public void setMyEndYPos(double yPos) {
    myEndYPos = yPos;
  }

  /**
   * sets image view of turtle
   * @param imageView : image view of turtle
   */

  public void setImageView(ImageView imageView) {
    myImageView = imageView;
  }



  /**
   * sets bearing of turtle
   * @param degrees : new bearing of turtle
   */
  public void setMyBearing(double degrees) {
    myBearing = degrees;
  }

  /**
   * sets bearing of turtle
   * @param visible : new bearing of turtle
   */
  public void setIsVisible(boolean visible) {
    isVisible = visible;
  }

}
