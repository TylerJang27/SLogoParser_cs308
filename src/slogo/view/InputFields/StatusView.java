package slogo.view.InputFields;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class StatusView {
  private static final Label statusLabel = new Label("Current Status:");

  private TextArea status;
  private VBox statusBox;

  public StatusView(){
    statusBox = new VBox();
    status = new TextArea();
    statusBox.getChildren().addAll(statusLabel, status);
  }

  public VBox getVBox(){
    return statusBox;
  }

  public TextArea getStatus(){
    return status;
  }

  public void clear() { status.clear(); }

  public void addStatusText(Double value) { status.appendText("Value: " + value + "\n"); }
}
