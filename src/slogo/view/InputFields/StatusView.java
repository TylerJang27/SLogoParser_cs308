package slogo.view.InputFields;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import java.awt.*;

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

    status.setMaxSize(300,200);
    status.setMinSize(300,200);
    status.setPrefSize(300,200);

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

  //TODO: tYLER FIX THIS
  public void addStatusText(int id, Double turtleX, Double turtleY, Double turtleAngle, Color penColor, Boolean penDown) {
    status.appendText("Active Turtle: " + id + "\n");
    status.appendText("Turtle X: " + turtleX + "\n");
    status.appendText("Turtle Y: " + turtleY + "\n");
    status.appendText("Turtle Angle: " + turtleAngle + "\n");

    status.appendText("\n");

    status.appendText("Pen DOWN: " + penDown + "\n");
    status.appendText("Pen Color: " + penColor + "\n");

  }
}
