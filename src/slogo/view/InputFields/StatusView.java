package slogo.view.InputFields;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StatusView {
  private static final Label statusLabel = new Label("Current Status:");

  private TextArea status;
  private VBox statusBox;

  public StatusView(){
    statusBox = new VBox();
    status = new TextArea();
    this.setDetails();
    statusBox.getChildren().addAll(statusLabel, status);
  }


  private void setDetails(){
    status.setMinHeight(200);
    status.setMinWidth(400);
    Background backing = new Background(new BackgroundFill(
        Color.BLACK, new CornerRadii(0), new Insets(0)));
    //statusBox.setBackground(backing);
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
