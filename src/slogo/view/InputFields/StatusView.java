package slogo.view.InputFields;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    statusBox.getChildren().addAll(statusLabel, status);
  }

  public VBox getVBox(){
    return statusBox;
  }

  public TextArea getStatus(){
    return status;
  }

  public void clear() { status.clear(); }

  public void addStatusText(Double turtleX, Double turtleY, Double turtleAngle, Color penColor, Boolean penDown) {
    status.appendText("Active Turtle:" + "name" + "\n");
    status.appendText("Turtle X: " + turtleX + "\n");
    status.appendText("Turtle Y: " + turtleY + "\n");
    status.appendText("Turtle Angle: " + turtleAngle + "\n");

    status.appendText("\n");

    status.appendText("Pen DOWN: " + penDown + "\n");
    status.appendText("Pen Color: " + penColor + "\n");

  }
}
