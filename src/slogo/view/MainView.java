package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import slogo.backendexternal.TurtleStatus;
import slogo.frontendexternal.TurtleView;

/** @author Shruthi Kumar, Nevzat Sevim */

public class MainView extends VBox implements EventHandler, MainViewAPI {

  //Create Toolbar (top) and Text Areas (bottom)
  private Toolbar myToolbar;
  private TextFields myTextFields;

  //Pane and Turtle Object
  private Pane pane;
  private double paneWidth = 1075;
  private double paneHeight = 600;
  private double turtleSize = 90;

  private TurtleView turtle;

  public MainView() {

    this.myTextFields = new TextFields(this);
    this.myToolbar = new Toolbar(this);
    this.myToolbar.setTextField(myTextFields);

    this.turtle = new TurtleView();
    turtle.getPenView().setMyPenColor(Color.RED);
    turtle.myImageView.setFitWidth(turtleSize);
    turtle.myImageView.setFitHeight(turtleSize);
    turtle.myImageView.setLayoutX(turtle.getMyStartXPos());
    turtle.myImageView.setLayoutY(turtle.getMyStartYPos());


    this.pane = new Pane(turtle.myImageView);

    pane.setMaxSize(paneWidth, paneHeight);
    pane.setMinSize(paneWidth, paneHeight);
    pane.setPrefSize(paneWidth, paneHeight);
    pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(0))));

    this.getChildren().addAll(myToolbar, pane, myTextFields);
  }

  public void moveTurtle(List<TurtleStatus> ts) {
      pane.getChildren().clear(); // clear complete list
      turtle.executeState(ts);
      pane.getChildren().add(turtle.myImageView);

    List<Line> temp = (ArrayList) turtle.getPenView().getMyLines();
    for(int i = 0; i < temp.size(); i++)  {
      if(!pane.getChildren().contains(temp.get(i))) { //TODO: redundant? Should be for-each?
        pane.getChildren().add(temp.get(i));
      }
    }
  }

  public void resetPane() {
   // this.getChildren().get(turtle);
    //turtle.getPenView().getMyLines().clear(); // remember first item
    //pane.getChildren().clear(); // clear complete list
    //pane.getChildren().add(obj);
  }

  public TurtleView getTurtle() {
    return turtle;
  }

  @Override
  public void handle(Event event) { }

  @Override
  public String readCommand() {
    return "";
  }

  @Override
  public void sendCommand(String command) { }

  @Override
  public int sendUpdates() {
    return 0;
  }

  @Override
  public void changeLanguage(int choice) { }

  @Override
  public Collection<TurtleStatus> getCommands() { return null; }

  @Override
  public void updatePastCommands() { }

  @Override
  public void clearCommands() { }

  @Override
  public void resetCommands() { }

  @Override
  public void setSkin(int choice) { }

  @Override
  public Node getStyleableNode() { return null; }

  //Public Get Methods
  public TextFields getTextFields(){return this.myTextFields;}
  public Toolbar getToolBar(){return this.myToolbar;}
  public Pane getPane() {return this.pane;}
  public double getTurtleSize() {return this.turtleSize;}

}
