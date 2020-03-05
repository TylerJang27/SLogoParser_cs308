package slogo.view.InputFields;

import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import slogo.view.Display;

public class MoveArrows {
  private static final Image ARROW_IMAGE = new Image("/slogo/view/imagesFolder/arrow.png");
  private VBox pane;
  private Button up;
  private Button down;
  private Button left;
  private Button right;
  private double increment;
  private double size;

  public MoveArrows(double length, double index){
    size = length;
    increment = index;
    createPane();
    createButtons();
    pane.getChildren().addAll(up,down,left,right);
  }

  public VBox getVBox(){ return pane; }

  public double getIncrement(){ return increment; }

  public List<Button> getButtons(){
    return Arrays.asList(up, down, left, right);
  }

  private void createPane(){
    pane = new VBox();
    pane.setMinHeight(size);
    pane.setMinWidth(size);
  }

  private void createButtons(){
    up = new Button();
    ImageView upImage = new ImageView(ARROW_IMAGE);
    up.setGraphic(upImage);
    up.setLayoutX(size/3);

    down = new Button();
    ImageView downImage = new ImageView(ARROW_IMAGE);
    downImage.setRotate(downImage.getRotate() + 180);
    down.setGraphic(downImage);
    down.setLayoutX(size/3);
    down.setLayoutY(2*size/3);

    right = new Button();
    ImageView rightImage = new ImageView(ARROW_IMAGE);
    rightImage.setRotate(rightImage.getRotate() + 90);
    right.setGraphic(rightImage);
    right.setLayoutX(2*size/3);
    right.setLayoutY(size/3);

    left = new Button();
    ImageView leftImage = new ImageView(ARROW_IMAGE);
    leftImage.setRotate(leftImage.getRotate() + 270);
    left.setGraphic(leftImage);
    left.setLayoutY(size/3);
  }
}
