package slogo.view;

import java.util.Random;
import java.util.Collection;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import slogo.backendexternal.TurtleStatus;
import slogo.frontendexternal.TurtleView;

/** @author Shruthi Kumar, Nevzat Sevim */

public class MainView extends VBox implements EventHandler, MainViewAPI {

  //Create Toolbar (top) and Text Areas (bottom)
  private Toolbar myToolbar;
  private TextFields myTextFields;

  //Create a Timeline and the Object of Interest
  private Timeline animation;
  private static final int FRAMES_PER_SECOND = 60;
  private static final double MILLISECOND_DELAY = 10000/FRAMES_PER_SECOND;

  //Create Canvas, Canvas Parameters and Turtle Object
  private Canvas simCanvas;
  private double canvasWidth = 600;
  private double canvasHeight = 600;
  private Color backgroundColor, penColor;
  private TurtleView turtle;

  private Random random;


  public MainView() {

    this.myTextFields = new TextFields(this);
    this.myToolbar = new Toolbar(this);
    this.myToolbar.setTextField(myTextFields);

    this.turtle = new TurtleView();
    this.simCanvas = new Canvas(canvasWidth,canvasHeight);

    this.getChildren().addAll(myToolbar, simCanvas, myTextFields);
    animationFunctions();
  }


  public void step() {
    //turtle.setMyXPos(random.nextInt(100));
    //turtle.setMyYPos(random.nextInt(100));
    draw();
  }


  public void draw() {

    GraphicsContext g = this.simCanvas.getGraphicsContext2D();
    g.setFill(backgroundColor);
    g.fillRect(0, 0, simCanvas.getWidth(), simCanvas.getHeight());
    g.drawImage(turtle.myImage, 5, 5, 50, 50);

  }

  @Override
  public void handle(Event event) {
  }

  @Override
  public String readCommand() {
    return "";
  }

  @Override
  public void sendCommand(String command) {
    turtle.setMyXPos(50);
    turtle.setMyYPos(100);
    /*if(true) { //ie if command is valid - will add correct booleans when backend side sends command
      turtle.executeState(((ArrayList) getCommands()).get(0));
    }
    else { //if not valid
      turtle.executeState(((ArrayList) getCommands()).get(0));
    }
     */
  }

  @Override
  public int sendUpdates() {
    return 0;
  }

  @Override
  public void changeLanguage(int choice) {

  }

  @Override
  public Collection<TurtleStatus> getCommands() {
    return null;
  }

  @Override
  public void updatePastCommands() {

  }

  @Override
  public void clearCommands() {

  }

  @Override
  public void resetCommands() {

  }

  @Override
  public void setSkin(int choice) {
    //turtle.setMyImageView(new Image("https://vignette.wikia.nocookie.net/tmnt2012series/images/6/63/Raph-rage.png/revision/latest?cb=20170428232825"));
  }

  //Public Set Methods
  public void setBackgroundColor(Color c){ this.backgroundColor = c; }
  public void setPenColor(Color c){this.penColor = c;}

  //Public Get Methods
  public TextFields getTextFields(){return this.myTextFields;}
  public ToolBar getToolBar(){return this.myToolbar;}

  @Override
  public Node getStyleableNode() {
    return null;
  }

  public void stop() {
    animation.stop();
  }

  /**
   * Method that sets up the animation, in which the myMainView step method is called every second which updates the
   * grid on the screen.
   */
  public void animationFunctions() {

    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
      try {
        this.step();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();

  }

}