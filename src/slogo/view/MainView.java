package slogo.view;

import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Point2D;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Affine;
import slogo.backendexternal.TurtleStatus;

import java.awt.*;
import java.util.Collection;
import slogo.frontendexternal.TurtleView;


/**
 *
 * @author Shruthi Kumar
 */
public class MainView extends VBox implements EventHandler, MainViewAPI {

  private Toolbar myToolbar;
  private TextFields myTextFields;

  private Timeline timeline;
  private TurtleView turtle;

  private Canvas simCanvas;
  private double canvasWidth = 600;
  private double canvasHeight = 600;

  private Color backgroundColor, penColor;


  public MainView() {
    this.myTextFields = new TextFields(this);

    this.myToolbar = new Toolbar(this);
    this.myToolbar.setTextField(myTextFields);

    this.turtle = new TurtleView();

    this.simCanvas = new Canvas(canvasWidth,canvasHeight);

    this.getChildren().addAll(myToolbar, simCanvas, myTextFields);

  }


  public void step() {
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
  public void changeLanguage() {

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
}
