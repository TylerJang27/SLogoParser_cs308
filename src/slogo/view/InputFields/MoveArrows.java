package slogo.view.InputFields;

import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
  private TextField setIncrement;

  private double size;

  public MoveArrows(double length, double index){
    size = length;
    createTextField(index, size/5);
    createPane();
    createButtons();
    pane.getChildren().addAll(up,down,left,right, setIncrement);
  }

  private void createPane(){
    pane = new VBox();
    pane.setMinSize(size, size);
    pane.setPrefSize(size, size);
  }

  private void createTextField(double index, double size){
    setIncrement = new TextField();
    setIncrement.setMinSize(3*size, size);
    setIncrement.setPrefSize(3*size, size);
    setIncrement.setMaxSize(3*size, size);
    setIncrement.setText(String.valueOf(index));
  }

  public VBox getVBox(){ return pane; }

  public double getIncrement(){ return Integer.parseInt(setIncrement.getText()); }

  public List<Button> getButtons(){
    return Arrays.asList(up, down, left, right);
  }

  private void createButtons(){
    up = createButton("FD", 0, size/3, 0, size/5);
    down = createButton("BD", 180, size/3, 2*size/3, size/5);
    right = createButton("RT", 90, 2*size/3, size/3, size/5);
    left = createButton("LT", 270, 0, size/3, size/5);
  }

  private Button createButton(String name, double rotation, double x, double y, double size){
    Button button = new Button(name);
    button.setId(name.toLowerCase());
//    ImageView view = new ImageView(ARROW_IMAGE);
//    view.setRotate(view.getRotate() + 90);
//    button.setGraphic(view);
    button.setMinSize(3*size, size);
    button.setPrefSize(3*size, size);
    button.setMaxSize(3*size, size);
    button.setLayoutX(x - button.getLayoutBounds().getMinX());
    button.setLayoutY(y - button.getLayoutBounds().getMinY());
    return button;
  }
}
