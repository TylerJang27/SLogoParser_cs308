package slogo.view;

import java.io.ObjectInputFilter.Config;
import java.util.Collection;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import slogo.backendexternal.TurtleStatus;


/**
 *
 * @author Shruthi Kumar
 */
public class MainView extends VBox implements EventHandler, MainViewAPI {

  private Toolbar myToolbar;
  private SimulationCanvas simulationCanvas;
 //s private Configpanel


  public MainView() {
    myToolbar = new Toolbar(this);
    simulationCanvas = new SimulationCanvas();
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
