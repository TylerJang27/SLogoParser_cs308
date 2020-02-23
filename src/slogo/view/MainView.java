package slogo.view;

import java.awt.*;
import java.io.ObjectInputFilter.Config;
import java.util.Collection;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import slogo.backendexternal.TurtleStatus;


/**
 *
 * @author Shruthi Kumar
 */
public class MainView extends VBox implements EventHandler, MainViewAPI {

  public Toolbar myToolbar;
  public TextFields myTextFields;

  private Timeline timeline;

  private Canvas simCanvas;
  private double canvasWidth = 600;
  private double canvasHeight = 600;


  public MainView() {
    this.myToolbar = new Toolbar(this);
    this.myTextFields = new TextFields(this);
    this.myToolbar.setTextField(myTextFields);


    this.simCanvas = new Canvas(canvasWidth,canvasHeight);

    this.getChildren().addAll(myToolbar, simCanvas, myTextFields);
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
}
