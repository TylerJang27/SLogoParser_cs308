package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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


/**
 * This class sets up the MainView that will host the toolbar, the pane, and the input fields (console, list of variables, etc)
 * Purpose: This initializes and updates the display. Users can input commands and see their commands play out on the pane as the turtle moves.
 * Assumptions: The class will work assuming all dependencies on Toolbar and the InputFields are working
 * Dependencies: This class relies on the Toolbar, Display, and InputFields, as well as resources files like the images
 * @author Shruthi Kumar, Nevzat Sevim
 **/

public class MainView extends VBox implements EventHandler {
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
  private String defaultCodeFileName;

  private TurtleView turtle;
  private TurtleViewManager turtleManager;
  private TurtleStatus turtleStatus;


  /**
   * Constructor for the MainView object
   */
  public MainView() {
    // Get the Textfield and Toolbar in the MainView
    setUpMainViewFields(Color.LIGHTGRAY, Color.BLACK, 1, "raphael", "blank");

    //Generate the initial Turtle Object
    setUpTurtles(1);
    this.turtleStatus = new TurtleStatus(1);

    //Set the Pane for the IDE
    setUpPane();

    this.getChildren().addAll(pane, myToolbar, myInputFields);
    this.setAlignment(Pos.TOP_LEFT);
  }

  /**
   * Overloaded constructor used when preferences are loaded from a file
   * @param backgroundColor : display background color
   * @param penColor : pen color
   * @param numTurtle : number of turtles
   * @param turtleImageName : image name of turtle
   * @param codeFileName : name of code file
   */
  public MainView(Color backgroundColor, Color penColor, int numTurtle, String turtleImageName, String codeFileName) {
    // Get the Textfield and Toolbar in the MainView
    setUpMainViewFields(backgroundColor, penColor, numTurtle, turtleImageName, codeFileName);

    //Generate the initial Turtle Object
    setUpTurtles(numTurtle);

    //Set the Pane for the IDE
    setUpPane();

    this.getChildren().addAll(myToolbar,pane,myInputFields);
    this.setAlignment(Pos.TOP_LEFT);
    this.turtleStatus = new TurtleStatus(1);
  }

  /**
   * Moves the turtle based on given statuses that execute commands
   * @param ts: list of turtle statuses
   */
  public void moveTurtle(List<TurtleStatus> ts) {
    if (!ts.isEmpty()) {
      turtleManager.execute(ts);
      this.turtleStatus = ts.get(ts.size() - 1);

      updateViewLocation();
    }
  }

  /**
   * Updates location of the image view of the turtle
   */
  public void updateViewLocation() {
    pane.getChildren().clear(); // clear complete list
    pane.getChildren().addAll(turtleManager.getImageViews());

    List<Line> temp = (ArrayList) turtleManager.getMyLines();

    for (int i = 0; i < temp.size(); i++) {
      if (!pane.getChildren().contains(temp.get(i))) {
        pane.getChildren().add(temp.get(i));
      }
    }
  }

  /**
   * Gets the TurtleView
   * @return turtle: turtleView
   */
  public TurtleView getTurtle() {
    return turtle;
  }

  /**
   * Gets the turtle manager
   * @return turtleManager : turtle view manager
   */
  public TurtleViewManager getTurtles() {
    return turtleManager;
  }

  /**
   * Sets the layout of all image views
   */
  public void setImageViewLayouts() {
    for (TurtleView tv: turtleManager.getTurtleViews()) {
      tv.setUpMyImageView();
    }
  }

  /**
   * Sets the image views on the pane
   */
  public void setPaneImageViews() {
    for(int i = 0; i < turtleManager.getImageViews().size(); i++) {
      pane.getChildren().set(i, turtleManager.getImageViews().get(i));
    }
  }

  /**
   * sets the background color
   * @param newColor : new background color
   */
  public void setBackgroundColor(Color newColor) {
    this.pane.setBackground(new Background(new BackgroundFill(newColor, CornerRadii.EMPTY, new Insets(0))));
    defaultBackgroundColor = newColor;
  }

  /**
   * Sets the new pen color
   * @param newColor : new pen color
   */
  public void setPenColor(Color newColor) {
    this.turtleManager.setAllPenViewColors(newColor);
    defaultPenColor = newColor;
  }

  /**
   * sets the name of the image file
   * @param name : turtle image file name
   */
  public void setTurtleFileName(String name) {
    defaultTurtleFileName = name;
  }


  /**
   * Overriden method that handles events
   * @param event : event
   */
  @Override
  public void handle(Event event) { }

  /**
   * Gets the default background color
   * @return defaultBackgroundColor : default background color
   */
  public Color getDefaultBackgroundColor() {
    return defaultBackgroundColor;
  }

  /**
   * Gets the default pen color
   * @return defaultPenColor : default pen color
   */
  public Color getDefaultPenColor() {
    return defaultPenColor;
  }

  /**
   * Gets default number of turtles
   * @return number of turtles
   */
  public int getDefaultNumTurtles() {
    return turtleManager.getTurtleViews().size();
  }

  /**
   * Gets turtle image name
   * @return defaultTurtleFileName : turtle file name
   */
  public String getDefaultTurtleFileName() {
    return defaultTurtleFileName;
  }

  /**
   * Gets default code file name
   * @return defaultCodeFileName : code file name
   */
  public String getDefaultCodeFileName() {
    return defaultCodeFileName;
  }

  //Public Get Methods

  /**
   * Gets the input fields
   * @return myInpurFields : input fields
   */
  public InputFields getTextFields(){return this.myInputFields;}

  /**
   * Gets the tool bar
   * @return myToolbar : tool bar
   */
  public Toolbar getToolBar(){return this.myToolbar;}
  //public Pane getPane() {return this.pane;}
  //public double getTurtleSize() {return this.turtleSize;}

  /**
   * Gets the turtle status
   * @return turtleStatus : turtle status
   */
  public TurtleStatus getTurtleStatus() {return this.turtleStatus;}


  private void setUpMainViewFields(Color backgroundColor, Color penColor, int numTurtles, String turtleImageName,
      String codeFileName) {
    this.myInputFields = new InputFields(this);
    this.myToolbar = new Toolbar(this);
    this.myToolbar.setPadding(new Insets(0));
    defaultBackgroundColor = backgroundColor;
    defaultPenColor = penColor;
    defaultNumTurtles = numTurtles;
    defaultTurtleFileName = turtleImageName;
    defaultCodeFileName = codeFileName;
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
    this.turtleManager = new TurtleViewManager(paneWidth/2.0, paneHeight/2.0, defaultTurtleFileName, defaultPenColor);
    this.turtleManager.setAllPenViewColors(defaultPenColor);
    this.turtleManager.initializeTurtleViews(numTurtles);
    this.turtle = turtleManager.getTurtle(1);
  }


}
