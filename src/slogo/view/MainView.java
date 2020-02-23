package slogo.view;

import java.awt.*;
import java.io.ObjectInputFilter.Config;
import java.util.Collection;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import slogo.backendexternal.TurtleStatus;

public class MainView extends VBox implements EventHandler, MainViewAPI {

  public Toolbar myToolbar;
  public TextFields myTextFields;

  private Timeline timeline;

  private Canvas simCanvas;
  private double canvasWidth = 830;
  private double canvasHeight = 830;


  public MainView() {
    this.myToolbar = new Toolbar(this);
    this.myTextFields = new TextFields(this);

    this.simCanvas = new Canvas(canvasWidth,canvasHeight);

    this.getChildren().addAll(myTextFields, myToolbar, simCanvas);
  }

  private void test(ActionEvent actionEvent) {

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
