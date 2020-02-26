package slogo.view;

import java.io.ObjectInputFilter.Config;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MainView extends BorderPane {

  private Toolbar myToolbar;
 //s private Configpanel


  public MainView() {
    myToolbar = new Toolbar(this);
    this.setTop(myToolbar);



  }

  public void step() {
  }
}
