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

  public Image myImage;
  public ImageView myImageView;

  private PenView penView;
  private double myBearing;
  private boolean isVisible;
  private boolean clearScreen;
  private String TURTLE_IMG = "view/imagesFolder/turtle.png";



  /**
   * Constructor for TurtleView object
   */

  public TurtleView(double x, double y) {
    myStartXPos = x;
    myStartYPos = y;
    myEndXPos = 0;
    myEndYPos = 0;
    myBearing = 0;
    isVisible = true;
    clearScreen = false;
    penView = new PenView();
    myImage = new Image("/slogo/view/imagesFolder/raphael.png");
    myImageView =  new ImageView(myImage);
  }


  /**
   *  Executes the command that the user enters by doing the action specified in the command
   * @param t : Turtle status that holds command
   */
  public void executeState(List<TurtleStatus> t) {
    SequentialTransition sequentialTransition = new SequentialTransition();
    sequentialTransition.setNode(this.myImageView);
    Polyline pathLine = new Polyline();

    int index = 0;

    for(int i = 0; i < t.size() - 1; i++) {
      index = 0;
      Double[] pathPoints = new Double[4];
      TurtleStatus start = t.get(i);
      TurtleStatus end = t.get(i + 1);
      checkClearScreen(end);
      this.myImageView.setVisible(end.getVisible());
      if (end.getBearing() != myBearing) {
        addRotationCommand(sequentialTransition, start, end);
      }
      pathLine = getPolyline(sequentialTransition, pathLine, index, pathPoints, start, end);
    }

    setMyEndXPos(t.get(t.size()-1).getX());

    setMyEndYPos(t.get(t.size()-1).getY());


    if (t.size() > 1) {
      sequentialTransition.play();
    }
  }

  private Polyline getPolyline(SequentialTransition sequentialTransition, Polyline pathLine,
      int index, Double[] pathPoints, TurtleStatus start, TurtleStatus end) {
    if (checkMovement(start, end) && end.getTrail()) {
      addPenViewLines(start, end);
      pathLine = getPolyline(sequentialTransition, pathLine, index, pathPoints, start, end, 2500);

    } else if (checkMovement(start, end) && !end.getTrail()) { //wraparound case
      pathLine = getPolyline(sequentialTransition, pathLine, index, pathPoints, end, end, 0);
    } else {
      getPolyLinePoints(pathLine, index, pathPoints, start, end);
    }
    return pathLine;
  }

  private void getPolyLinePoints(Polyline pathLine, int index, Double[] pathPoints,
      TurtleStatus start, TurtleStatus end) {
    pathPoints[index] = start.getX();
    pathPoints[index + 1] = start.getY();
    pathPoints[index + 2] = end.getX();
    pathPoints[index + 3] = end.getY();
    pathLine.getPoints().addAll(pathPoints);
  }

  private Polyline getPolyline(SequentialTransition sequentialTransition, Polyline pathLine,
      int index, Double[] pathPoints, TurtleStatus start, TurtleStatus end, int i2) {
    getPolyLinePoints(pathLine, index, pathPoints, start, end);

    PathTransition turtlePath = new PathTransition(Duration.millis(2500), pathLine,
        this.myImageView);
    turtlePath.setDuration(new Duration(i2));
    sequentialTransition.getChildren().add(turtlePath);
    pathLine = new Polyline();
    return pathLine;
  }

  private void addRotationCommand(SequentialTransition sequentialTransition, TurtleStatus start,
      TurtleStatus end) {
    RotateTransition turtleRotate = new RotateTransition(Duration.millis(2500),
        this.myImageView);
    turtleRotate.setFromAngle(start.getBearing());
    turtleRotate.setToAngle(end.getBearing());
    myBearing = end.getBearing();
    sequentialTransition.getChildren().add(turtleRotate);
  }

  private void checkClearScreen(TurtleStatus end) {
    if(end.getClear()) {
      getPenView().getMyLines().clear();
    }
  }
  public void clearScreen() {
      getPenView().getMyLines().clear();
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
