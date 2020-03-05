package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import slogo.backendexternal.TurtleStatus;
import slogo.frontendexternal.TurtleView;
import slogo.frontendexternal.TurtleViewManager;
import slogo.view.InputFields.InputFields;

/** @author Shruthi Kumar, Nevzat Sevim */

public class MainView extends VBox implements EventHandler, MainViewAPI {
  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth() - 300;
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() - 300;

  //Create Toolbar (top) and Text Areas (bottom)
  private Toolbar myToolbar;
  private InputFields myInputFields;

  //Pane and Turtle Object
  private Pane pane;
  private final double paneWidth = 1010;
  private final double paneHeight = 510;

  private final double turtleSize = 90;
  private Insets insets = new Insets(5.0);

  private TurtleView turtle;
  //private TurtleViewManager turtle;
  private TurtleStatus turtleStatus;

  public MainView() {
    // Get the Textfield and Toolbar in the MainView
    System.out.println("WIDTH: " + SCREEN_WIDTH);
    System.out.println("height: " + SCREEN_HEIGHT);
    this.myInputFields = new InputFields(this);
    this.myToolbar = new Toolbar(this);
    this.myToolbar.setPadding(insets);


    //Generate the initial Turtle Object
    setUpTurtle();

    //Set the Pane for the IDE
    setUpPane();

    this.getChildren().addAll(myToolbar,pane,myInputFields);
    this.setPadding(new Insets(10.0));
  }

  private void setUpPane() {
    this.pane = new Pane(turtle.myImageView);
    pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
            CornerRadii.EMPTY, insets)));

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
    this.turtleStatus = ts.get(ts.size() - 1);

    List<Line> temp = (ArrayList) turtle.getPenView().getMyLines();
    for(int i = 0; i < temp.size(); i++)  {
      if(!pane.getChildren().contains(temp.get(i))) {
        pane.getChildren().add(temp.get(i));
      }
    }
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
  public InputFields getTextFields(){return this.myInputFields;}
  public Toolbar getToolBar(){return this.myToolbar;}
  public Pane getPane() {return this.pane;}
  public double getTurtleSize() {return this.turtleSize;}
  public TurtleStatus getTurtleStatus() {return this.turtleStatus;}

}
