package slogo.frontendexternal;

import java.security.Policy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
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
  public ImageView myImageView =  new ImageView(new Image("https://vignette.wikia.nocookie.net/tmnt2012series/images/6/63/Raph-rage.png/revision/latest?cb=20170428232825"));
  private PenView penView;// = new ArrayList<PenView>();
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
    penView = new PenView();
    myImage = new Image("https://vignette.wikia.nocookie.net/tmnt2012series/images/6/63/Raph-rage.png/revision/latest?cb=20170428232825");
  }

  /**
   *  Executes the command that the user enters by doing the action specified in the command
   * @param t : Turtle status that holds command
   */
  public void executeState(Collection<TurtleStatus> t) {
//    addPenViewLines(t);
    Polyline pathLine = new Polyline();
    Double[] pathPoints = new Double[t.size()*2];
    Iterator<TurtleStatus> iterator = t.iterator();
    int index = 0;

    while(iterator.hasNext()) {
      TurtleStatus temp = iterator.next();
      addPenViewLines(temp);
      pathPoints[index] = temp.getX();
      setMyXPos(temp.getX());
      pathPoints[index+1] = temp.getY();
      setMyYPos(temp.getY());
      index+=2;
    }

    pathLine.getPoints().addAll(pathPoints);

    PathTransition turtlePath = new PathTransition();
    turtlePath.setDuration(Duration.millis(2500));
    turtlePath.setNode(this.myImageView);

    turtlePath.setPath(pathLine);
    turtlePath.play();
  }

  private void addPenViewLines(TurtleStatus t) {

      if(t.getPenDown()) {
        penView.updateMyLines(this.getMyXPos(), this.getMyYPos(), t.getX(), t.getY());
      }

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

  public PenView getPenView() {
    return penView;
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

  public void setMyImageView(ImageView imageView) {
    myImageView = imageView;
  }



  /**
   * sets bearing of turtle
   * @param degrees : new bearing of turtle
   */
  public void setMyBearing(double degrees) {
    myBearing = degrees;
  }

}
