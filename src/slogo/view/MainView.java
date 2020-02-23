package slogo.view;

import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

  private Toolbar myToolbar;

  public MainView() {
    myToolbar = new Toolbar(this);
    this.setTop(myToolbar);



  }

  public void step() {
  }
}
