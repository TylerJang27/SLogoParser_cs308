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
  private boolean isActive;
  private String TURTLE_IMG_DEFAULT_PATH = "slogo/view/imagesFolder";
  private final double turtleSize = 90;
  private String turtleUrlPath;


  /**
   * Constructor for TurtleView object
   */

  public TurtleView(double x, double y, String picFileName) {
    myStartXPos = x;
    myStartYPos = y;
    myEndXPos = 0;
    myEndYPos = 0;
    myBearing = 0;
    isVisible = true;
    clearScreen = false;
    isActive = true;

    penView = new PenView();
    turtleUrlPath = TURTLE_IMG_DEFAULT_PATH + "/" + picFileName;
    myImage = new Image("" + turtleUrlPath);
    myImageView =  new ImageView(myImage);
    setUpMyImageView();
    //System.out.println(myImageView);
  }


  public void executeState(SequentialTransition sequentialTransition, TurtleStatus start, TurtleStatus end) {
    this.setIsActive(true);
    //SequentialTransition sequentialTransition = new SequentialTransition();
    sequentialTransition.setNode(this.myImageView);
    Polyline pathLine = new Polyline();
    int index = 0;

    Double[] pathPoints = new Double[4];
    checkClearScreen(end);
    this.myImageView.setVisible(end.getVisible());
    if (end.getBearing() != myBearing) {
      addRotationCommand(sequentialTransition, start, end);
    }

    pathLine = getTurtleTrail(sequentialTransition, pathLine, index, pathPoints, start, end);

    //if (t.size() > 1) {

      //sequentialTransition.play(); /** If executing one at a time, move this to a play method and have sequential as global var*/
    //}

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
      pathLine = getTurtleTrail(sequentialTransition, pathLine, index, pathPoints, start, end);
    }

    setMyEndXPos(t.get(t.size()-1).getX());

    setMyEndYPos(t.get(t.size()-1).getY());


    if (t.size() > 1) {
      sequentialTransition.play();
    }


    /*
    SequentialTransition sequentialTransition = new SequentialTransition();
    sequentialTransition.setNode(this.myImageView);
    Polyline pathLine = new Polyline();

    int index = 0;
      Double[] pathPoints = new Double[4];
      checkClearScreen(end);
      this.myImageView.setVisible(end.getVisible());
      if (end.getBearing() != myBearing) {
        addRotationCommand(sequentialTransition, start, end);
      }
      pathLine = getTurtleTrail(sequentialTransition, pathLine, index, pathPoints, start, end);
      sequentialTransition.play();

     */

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
    myBearing = end.getBearing();
    sequentialTransition.getChildren().add(turtleRotate);
  }

  private void checkClearScreen(TurtleStatus end) {
    if(end.getClear()) {
      getPenView().getMyLines().clear();
    }
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

  /**
   * sets bearing of turtle
   * @param active : new bearing of turtle
   */
  public void setIsActive(boolean active) {
    isActive = active;
  }

  public void setUpMyImageView() {
    this.getPenView().setMyPenColor(Color.BLACK);
    this.myImageView.setFitWidth(turtleSize);
    this.myImageView.setFitHeight(turtleSize);
    this.myImageView.setX(this.myImageView.getX() - this.myImageView.getFitWidth() / 2);
    this.myImageView.setY(this.myImageView.getY() - this.myImageView.getFitHeight() / 2);
    this.myImageView.setLayoutX(this.getMyStartXPos());
    this.myImageView.setLayoutY(this.getMyStartYPos());

  }

}
