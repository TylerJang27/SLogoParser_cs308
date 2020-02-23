package slogo.view;

import java.io.ObjectInputFilter.Config;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MainView extends VBox implements EventHandler {

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
}
