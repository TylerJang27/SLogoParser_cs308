package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import slogo.backendexternal.TurtleStatus;
import slogo.frontendexternal.TurtleView;
import java.lang.Object;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

/** @author Shruthi Kumar, Nevzat Sevim */

public class MainView extends VBox implements EventHandler, MainViewAPI {

  //Create Toolbar (top) and Text Areas (bottom)
  private Toolbar myToolbar;
  private TextFields myTextFields;

  //Pane and Turtle Object
  private Pane pane;
  private final double paneWidth = 1075;
  private final double paneHeight = 600;
  private final double turtleSize = 90;



  private TurtleView turtle;

  public MainView() {
    // Get the Textfield and Toolbar in the MainView
    this.myTextFields = new TextFields(this);
    this.myToolbar = new Toolbar(this);
    this.myToolbar.setTextField(myTextFields);

    //Generate the initial Turtle Object
    setUpTurtle();

    //Set the Pane for the IDE
    setUpPane();

    this.getChildren().addAll(myToolbar, pane, myTextFields);
  }

  private void setUpPane() {
    this.pane = new Pane(turtle.myImageView);
    pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
            CornerRadii.EMPTY, new Insets(0))));

    pane.setMaxSize(paneWidth, paneHeight);
    pane.setMinSize(paneWidth, paneHeight);
    pane.setPrefSize(paneWidth, paneHeight);

    BorderStroke borderStroke = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5));
    Border border = new Border(borderStroke);
    pane.setBorder(border);
  }

  private void setUpTurtle() {
    this.turtle = new TurtleView(paneWidth/2.0, paneHeight/2.0);
    turtle.getPenView().setMyPenColor(Color.RED);
    turtle.myImageView.setFitWidth(turtleSize);
    turtle.myImageView.setFitHeight(turtleSize);
    turtle.myImageView.setX(turtle.myImageView.getX() - turtle.myImageView.getFitWidth() / 2);
    turtle.myImageView.setY(turtle.myImageView.getY() - turtle.myImageView.getFitHeight() / 2);
    turtle.myImageView.setLayoutX(turtle.getMyStartXPos());
    turtle.myImageView.setLayoutY(turtle.getMyStartYPos());
  }

  public void moveTurtle(List<TurtleStatus> ts) {
    pane.getChildren().clear(); // clear complete list
    turtle.executeState(ts);
    pane.getChildren().add(turtle.myImageView);

    List<Line> temp = (ArrayList) turtle.getPenView().getMyLines();
    for(int i = 0; i < temp.size(); i++)  {
      if(!pane.getChildren().contains(temp.get(i))) {
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
