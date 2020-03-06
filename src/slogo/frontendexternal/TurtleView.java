package slogo.frontendexternal;

import java.util.List;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import slogo.backendexternal.TurtleStatus;

/**
 * Creates TurtleViewg
 * @author Shruthi Kumar, Tyler Jang
 */
public class TurtleView {
  private double myStartXPos;
  private double myStartYPos;

  public Image myImage;
  public ImageView myImageView;

  private PenView penView;
  private double myBearing;
  private boolean isVisible;
  private boolean clearScreen;
  private boolean isActive;
  private String TURTLE_IMG_DEFAULT_PATH = "slogo/view/imagesFolder";
  private final double turtleSize = 90;
  private String turtleUrlPath;


  /**
   * Constructor for TurtleView object
   * @param x : x position of turtle
   * @param y : y position of turtle
   * @param picFileName : name of turtle image
   * @param color : color of pen
   *
   */
  public TurtleView(double x, double y, String picFileName, Color color) {
    myStartXPos = x;
    myStartYPos = y;

    myBearing = 0;
    isVisible = true;
    clearScreen = false;
    isActive = true;

    penView = new PenView();
    turtleUrlPath = TURTLE_IMG_DEFAULT_PATH + "/" + picFileName + ".png";
    myImage = new Image("" + turtleUrlPath);
    myImageView =  new ImageView(myImage);
    setUpMyImageView();
  }


  /**
   * Executes each turtle status
   * @param sequentialTransition the transition to be played
   * @param start starting turtle status
   * @param end ending turtle status
   */
  public void executeState(SequentialTransition sequentialTransition, TurtleStatus start, TurtleStatus end) {
    //SequentialTransition sequentialTransition = new SequentialTransition();
    if(isActive) {
      sequentialTransition.setNode(this.myImageView);
      Polyline pathLine = new Polyline();
      int index = 0;

      Double[] pathPoints = new Double[4];
      checkClearScreen(end);

      this.myImageView.setVisible(end.getVisible());

      if (end.getBearing() != myBearing) {
        addRotationCommand(sequentialTransition, start, end);
      }

      if(end.hasRunnable()) end.modify();
      pathLine = getTurtleTrail(sequentialTransition, pathLine, index, pathPoints, start, end);

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
   * Returns whether or not the turtle is active
   * @return isActive : T/F whether turtle is active
   */
  public boolean getIsActive() {return isActive;}


  /**
   * Gets ImageView of turtle
   * @return myImageView : image view of turtle
   */
  public ImageView getMyImageView() {
    return myImageView;
  }

  /**
   * Gets the PenView
   * @return penView
   */
  public PenView getPenView() {
    return penView;
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

  /**
   * sets bearing of turtle
   * @param active : new bearing of turtle
   */
  public void setIsActive(boolean active) {
    isActive = active;
  }

  /**
   * Sets up the layout of the current turtle image view
   */
  public void setUpMyImageView() {
    this.getPenView().setMyPenColor(penView.getMyPenColor());
    this.myImageView.setFitWidth(turtleSize);
    this.myImageView.setFitHeight(turtleSize);
    this.myImageView.setX(this.myImageView.getX() - this.myImageView.getFitWidth() / 2);
    this.myImageView.setY(this.myImageView.getY() - this.myImageView.getFitHeight() / 2);
    this.myImageView.setLayoutX(this.getMyStartXPos());
    this.myImageView.setLayoutY(this.getMyStartYPos());
  }

  private Polyline getTurtleTrail(SequentialTransition sequentialTransition, Polyline pathLine,
      int index, Double[] pathPoints, TurtleStatus start, TurtleStatus end) {
    if (checkMovement(start, end) && end.getTrail()) {
      addPenViewLines(start, end);
      pathLine = getPolyline(sequentialTransition, pathLine, index, pathPoints, start, end, 2500);

    } else if (checkMovement(start, end) && !end.getTrail()) { //wraparound case
      pathLine = getPolyline(sequentialTransition, pathLine, index, pathPoints, end, end, 0);
    } else {
      getPolyLinePoints(pathLine, pathPoints, start, end);
    }
    return pathLine;
  }

  private void getPolyLinePoints(Polyline pathLine, Double[] pathPoints,
      TurtleStatus start, TurtleStatus end) {
    pathPoints[0] = start.getX();
    pathPoints[1] = start.getY();
    pathPoints[2] = end.getX();
    pathPoints[3] = end.getY();
    pathLine.getPoints().addAll(pathPoints);
  }

  private Polyline getPolyline(SequentialTransition sequentialTransition, Polyline pathLine,
      int index, Double[] pathPoints, TurtleStatus start, TurtleStatus end, int i2) {
    getPolyLinePoints(pathLine, pathPoints, start, end);

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
    setMyBearing(end.getBearing());
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
    this.penView.setPenDown(end.getPenDown());
    if(end.getPenDown()) {
        this.penView.updateMyLines(this.getMyStartXPos() + start.getX(), this.getMyStartYPos() + start.getY(), this.getMyStartXPos() + end.getX(), this.getMyStartYPos() + end.getY());
      }
  }


}
