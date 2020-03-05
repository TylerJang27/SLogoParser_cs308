package slogo.view.InputFields;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MoveArrows {
  private static final String ARROW = MoveArrows.class.getPackageName() + ".resources.";
  private VBox pane;
  private Button up;
  private Button down;
  private Button left;
  private Button right;
  private double increment;

  public MoveArrows(double size){
    createPane(size);
    createButtons();
    pane.getChildren().addAll(up,down,left,right);
  }

  public double getIncrement(){ return increment; }

  private void createPane(double size){
    pane = new VBox();
    pane.setMinHeight(size);
    pane.setMinWidth(size);
  }

  private void createButtons(){
    up = new Button();
  }
}
