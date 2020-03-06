package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
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
  public static final String DATA_TYPE = "sim";

  //Create Toolbar (top) and Text Areas (bottom)
  private Toolbar myToolbar;
  private InputFields myInputFields;

  //Pane and Turtle Object
  private Pane pane;
  private final double paneWidth = 990;
  private final double paneHeight = 510;

  private Color defaultBackgroundColor;
  private Color defaultPenColor;
  private int defaultNumTurtles;
  private String defaultTurtleFileName;

  private final double turtleSize = 90;
  private Insets insets = new Insets(0);

  private TurtleView turtle;
  private TurtleViewManager turtleManager;
  private TurtleStatus turtleStatus;



  public MainView() {
    // Get the Textfield and Toolbar in the MainView
    this.myInputFields = new InputFields(this);
    this.myToolbar = new Toolbar(this);
    this.myToolbar.setPadding(new Insets(0));
    defaultBackgroundColor = Color.LIGHTGRAY;
    defaultPenColor = Color.BLACK;
    defaultNumTurtles = 1;
    defaultTurtleFileName = "raphael.png";

    //Generate the initial Turtle Object
    setUpTurtles(1);

    //Set the Pane for the IDE
    setUpPane();

    this.getChildren().addAll(myToolbar,pane,myInputFields);
    //this.setPadding(new Insets(0.0));
    this.setAlignment(Pos.TOP_LEFT);
  }

  public MainView(Color backgroundColor, Color penColor, int numTurtle, String turtleImageName, String codeFileName) {
    defaultBackgroundColor = backgroundColor;
    defaultPenColor = penColor;
    defaultNumTurtles = numTurtle;
    defaultTurtleFileName = turtleImageName;
    // Get the Textfield and Toolbar in the MainView
    this.myInputFields = new InputFields(this);
    this.myToolbar = new Toolbar(this);
    this.myToolbar.setPadding(new Insets(0));


    //Generate the initial Turtle Object
    setUpTurtles(1);

    //Set the Pane for the IDE
    setUpPane();

    this.getChildren().addAll(myToolbar,pane,myInputFields);
    //this.setPadding(new Insets(0.0));
    this.setAlignment(Pos.TOP_LEFT);

  }

  private void setUpPane() {
    this.pane = new Pane(turtleManager.getImageViews().get(0));
    pane.setBackground(new Background(new BackgroundFill(defaultBackgroundColor,
            CornerRadii.EMPTY, new Insets(0))));

    pane.setMaxSize(paneWidth, paneHeight);
    pane.setMinSize(paneWidth, paneHeight);
    pane.setPrefSize(paneWidth, paneHeight);

    BorderStroke borderStroke = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5));
    Border border = new Border(borderStroke);
    pane.setBorder(border);
  }

  private void setUpTurtles(int numTurtles) {
    this.turtleManager = new TurtleViewManager(paneWidth/2.0, paneHeight/2.0);
    this.turtleManager.setAllPenViewColors(defaultPenColor);
    //turtleManager.setPenView(1, Color.BLACK); /** to do: update with correct ID*/
    this.turtleManager.initializeTurtleViews(numTurtles);
    this.turtle = turtleManager.getTurtle(1);
  }

  private void setUpTurtle() {
    this.turtle = new TurtleView(paneWidth/2.0, paneHeight/2.0, "raphael.png");
    turtle.getPenView().setMyPenColor(Color.BLACK);
    turtle.myImageView.setFitWidth(turtleSize);
    turtle.myImageView.setFitHeight(turtleSize);
    turtle.myImageView.setX(turtle.myImageView.getX() - turtle.myImageView.getFitWidth() / 2);
    turtle.myImageView.setY(turtle.myImageView.getY() - turtle.myImageView.getFitHeight() / 2);
    turtle.myImageView.setLayoutX(turtle.getMyStartXPos());
    turtle.myImageView.setLayoutY(turtle.getMyStartYPos());
  }

  public void moveTurtle(List<TurtleStatus> ts) {
    pane.getChildren().clear(); // clear complete list
    pane.getChildren().addAll(turtleManager.getImageViews());
    //pane.getChildren().add(turtle.myImageView);

    //turtle.executeState(ts);
    turtleManager.execute(ts);
    this.turtleStatus = ts.get(ts.size() - 1);

    //List<Line> temp = (ArrayList) turtle.getPenView().getMyLines();
    List<Line> temp = (ArrayList) turtleManager.getMyLines();


    for(int i = 0; i < temp.size(); i++)  {
      if(!pane.getChildren().contains(temp.get(i))) {
        pane.getChildren().add(temp.get(i));
      }
    }
  }

  public TurtleView getTurtle() {
    return turtle;
  }

  public TurtleViewManager getTurtles() {
    return turtleManager;
  }

  public void setImageViewLayouts() {
    for(ImageView temp : turtleManager.getImageViews()) {
      temp.setLayoutX(turtleManager.getStartX());
      temp.setLayoutY(turtleManager.getStartY());
      temp.setFitWidth(turtleSize);
      temp.setFitHeight(turtleSize);
    }
  }

  public void setPaneImageViews() {
    for(int i = 0; i < turtleManager.getImageViews().size(); i++)
    pane.getChildren().set(i, turtleManager.getImageViews().get(i));
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

  public Color getBackgroundColor() {
    return Color.BLACK;
    //return pane.getBackground().getC;
  }

  //Public Get Methods
  public InputFields getTextFields(){return this.myInputFields;}
  public Toolbar getToolBar(){return this.myToolbar;}
  public Pane getPane() {return this.pane;}
  public double getTurtleSize() {return this.turtleSize;}
  public TurtleStatus getTurtleStatus() {return this.turtleStatus;}

}
