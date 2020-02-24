package slogo.view;

import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
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

    this.getChildren().addAll(myToolbar, simCanvas, turtle.getMyImageView(), myTextFields);
  }


  public void step() {
  }

  @Override
  public void handle(Event event) {

  }

  @Override
  public void readCommand() {

  }

  @Override
  public String sendCommand() {
    return null;
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
  public void setBackgroundColor(Color c){
    this.backgroundColor = c;
  }
  public void setPenColor(Color c){this.penColor = c;}

  //Public Get Methods
  public TextFields getTextFields(){return this.myTextFields;}
  public ToolBar getToolBar(){return this.myToolbar;}

}
