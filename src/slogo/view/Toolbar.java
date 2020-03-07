package slogo.view;


import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.*;
import javax.xml.parsers.ParserConfigurationException;
import slogo.configuration.XMLException;
import slogo.configuration.XMLWriter;

import slogo.view.InputFields.InputFields;


/**
 * This class sets up the toolbar that users can use to interact with the program. It sets up the different buttons and options that users can choose.
 * Purpose: This creates the tool bar that users can use to set preferences in the simulation. It also allows the users to run the commands. We made a separate
 * class for the Toolbar so that everything would not have to be placed in the MainView class.
 * Assumptions: The class will work assuming all dependencies on property files are functioning.
 * Dependencies: This class relies on the MainView class to instantiate it correctly and on built-in Java classes (like ComboBox, Button, etc_
 * @author Shruthi Kumar, Nevzat Sevim
 */
public class Toolbar extends ToolBar {

  private static final double WIDTH = 1010.0;
  private static final double HEIGHT = 40.0;
  public static final int MAX_WIDTH = 50;
  public static final String CS_DUKE_WEBPAGE = "https://www2.cs.duke.edu/courses/spring20/compsci308/assign/03_parser/commands.php";
  public static final String SLOGO_VIEW_IMAGES_FOLDER = "/slogo/view/imagesFolder/";
  public static final String PNG = ".png";
  //Incorporate View and Text Field
  private MainView myMainView;
  private InputFields myTextFields;

  //The Drop Down Menus Themselves
  private ColorPicker penMenu, backgroundMenu;
  private ComboBox languageMenu, turtleMenu, modeMenu;

  //The Buttons & Bundles
  private Button commandButton, helpButton, changesButton, savePrefButton, uploadFile;
  private ResourceBundle buttonBundle, labelBundle, languageBundle, turtleSkinBundle, modeBundle, exceptionBundle;

  Label penLabel, backgroundLabel, turtleLabel, languageLabel, extraLabel;

  /**
   * Constructor for the Toolbar object
   * @param mainview : the MainView that will instantiate it
   */
  public Toolbar(MainView mainview) {
    setUpBundles();

    this.myMainView = mainview;
    this.myTextFields = myMainView.getTextFields();

    //set up tool bar and its elements
    createMenus();
    createButtons();
    setUpLabels();
    setUpToolBarSize();

    this.getItems().addAll(commandButton, new Separator(),
        turtleLabel, turtleMenu, penLabel, penMenu,
        languageLabel, languageMenu, backgroundLabel, backgroundMenu,  changesButton, new Separator(),
            extraLabel, savePrefButton, modeMenu, uploadFile, helpButton);
  }

  /**
   * Gets the command button
   * @return commandButton : the command button
   */
  public Button getCommandButton(){
    return commandButton;
  }

  /**
   * Gets the upload file button for code blocks
   * @return uploadFile : upload file button
   */
  public Button getUploadFile(){ return uploadFile; }

  /**
   * Gets the language choice box
   * @return languageMenu : language choice box
   */
  public ComboBox getLanguageBox() {return languageMenu; }

  /**
   * Gets the pane mode box
   * @return modeMenu : mode choice box
   */
  public ComboBox getModeMenu(){ return modeMenu; }

  /**
   * Sets the background based on the background color choice box
    * @param i : index of color chosen
   */
  public void setBackground(int i){
    ObservableList<Color> colorList = backgroundMenu.getCustomColors();
    if(i<0) return;
    if(i>=colorList.size()) i = colorList.size()-1;
    backgroundMenu.setValue(colorList.get(i));
    applyChanges();
  }

  /**
   * Sets the pen color based on the pen color choice box
   * @param i : index of color chosen
   */
  public void setPenColor(int i){
    ObservableList<Color> colorList = penMenu.getCustomColors();
    if(i<0) return;
    if(i>=colorList.size()) i = colorList.size()-1;
    penMenu.setValue(colorList.get(i));
    applyChanges();
  }

  /**
   * Sets the turtle image based on the image choice box
   * @param i : index of image chosen
   */
  public void setShape(int i){
    System.out.println(turtleMenu.getItems());
    if(i<0) return;
    if(i>=turtleMenu.getItems().size()) i = turtleMenu.getItems().size()-1;
    turtleMenu.getSelectionModel().select(i);
    System.out.println(turtleMenu.getSelectionModel().getSelectedItem());
    applyChanges();
  }

  /**
   * Sets the color palette
   * @param things : the RGB values of the color
   */
  public void setPalette(int[] things){
    System.out.println("toolbar");
    if(things.length!=4) return;
    int i = things[0];
    int r = things[1];
    int g = things[2];
    int b = things[3];
    if(i<0 || r <0 || g<0 || b<0 || r>255 || g>255 ||b>255 ) return;
    System.out.println("let's try get color");
    Color c = Color.rgb(r,g,b);
    System.out.println("got color");
    if(i<backgroundMenu.getCustomColors().size()) backgroundMenu.getCustomColors().set(i, c);
    else{
      for(int j = backgroundMenu.getCustomColors().size(); j<i; j++){
        backgroundMenu.getCustomColors().add(Color.WHITE);
      }
      backgroundMenu.getCustomColors().add(c);
    }
  }

  /**
   * Gets the pen color
   * @return pen color
   */
  public int getPenColor() { return penMenu.getCustomColors().indexOf(penMenu.getValue()); }

  /**
   * Gets the background color
   * @return background color
   */
  public int getBackgroundColor() { return backgroundMenu.getCustomColors().indexOf(backgroundMenu.getValue()); }

  /**
   * Gets the turtle image
   * @return turtle image
   */
  public int getTurtleShape() {return turtleMenu.getSelectionModel().getSelectedIndex();}

  private void setUpBundles() {
    buttonBundle = ResourceBundle.getBundle("slogo.view.resources.buttons");
    labelBundle = ResourceBundle.getBundle("slogo.view.resources.labels");
    languageBundle = ResourceBundle.getBundle("slogo.view.resources.languages");
    turtleSkinBundle = ResourceBundle.getBundle("slogo.view.resources.turtleSkin");
    modeBundle = ResourceBundle.getBundle("slogo.view.resources.modes");
    exceptionBundle = ResourceBundle.getBundle("slogo.view.resources.exceptionMessages");
  }

  /** Helping methods to import menus and buttons to the toolbar*/
  private void createMenus() {
    //Color Menus
    createColorMenu();

    //Background Color Menu
    createBackgroundColorMenu();

    //Turtle Menu
    createTurtleImageMenu();

    //Language Menu
    createLanguageMenu();

    //Mode Menu
    createModeMenu();
  }

  private void createModeMenu() {
    this.modeMenu = new ComboBox();
    modeMenu.setPromptText(modeBundle.getString("Toroidal"));
    modeMenu.getItems().addAll(modeBundle.getString("Toroidal"),
        modeBundle.getString("Normal"),
        modeBundle.getString("Edge"));
  }

  private void createLanguageMenu() {
    this.languageMenu = new ComboBox();
    languageMenu.setPromptText(languageBundle.getString("English"));
    languageMenu.getItems().addAll(languageBundle.getString("English"),
        languageBundle.getString("Chinese"),
        languageBundle.getString("French"),
        languageBundle.getString("German"),
        languageBundle.getString("Italian"),
        languageBundle.getString("Portuguese"),
        languageBundle.getString("Russian"),
        languageBundle.getString("Spanish"),
        languageBundle.getString("Urdu"));
  }

  private void createTurtleImageMenu() {
    this.turtleMenu = new ComboBox();
    turtleMenu.setPromptText(turtleSkinBundle.getString("Raphael"));
    turtleMenu.getItems().addAll(turtleSkinBundle.getString("Mickey"),
        turtleSkinBundle.getString("Raphael"),
        turtleSkinBundle.getString("Turtle"));
  }

  private void createBackgroundColorMenu() {
    this.backgroundMenu = new ColorPicker();
    backgroundMenu.setValue(Color.LIGHTGRAY);
    backgroundMenu.setMaxWidth(MAX_WIDTH);
  }

  private void createColorMenu() {
    this.penMenu = new ColorPicker();
    penMenu.setValue(Color.BLACK);
    penMenu.setMaxWidth(MAX_WIDTH);

  }

  private void createButtons() {
    this.commandButton = new Button(buttonBundle.getString("Run"));

    this.helpButton = new Button(buttonBundle.getString("Help"));
    helpButton.setOnAction(this:: handleHelp);

    this.changesButton = new Button(buttonBundle.getString("ApplyLabel"));
    changesButton.setOnAction(this::handleChanges);

    this.savePrefButton = new Button(buttonBundle.getString("SavePref"));
    savePrefButton.setOnAction(this::writeOutTab);

    this.uploadFile = new Button(buttonBundle.getString("ChooseFile"));
  }

  private void applyChanges () {
    this.myMainView.setBackgroundColor(backgroundMenu.getValue());
    this.myMainView.setPenColor(penMenu.getValue());

    if (!turtleMenu.getSelectionModel().isEmpty()) {
      String url = SLOGO_VIEW_IMAGES_FOLDER + turtleMenu.getValue() + PNG;
      myMainView.setTurtleFileName(turtleMenu.getValue().toString());
      myMainView.getTurtles().setImageViews(new ImageView(new Image("" + url)));
      myMainView.setImageViewLayouts();
      myMainView.setPaneImageViews();

      //TODO: REMOVE THIS FAILED ATTEMPT TO CORRECT POSITIONS BELOW
      myMainView.getTurtles().correctPositions();
      myMainView.updateViewLocation();
    }
  }

  /** Methods that define the function of each Button */
  private void handleChanges(ActionEvent actionEvent) {
    applyChanges();
  }

  private void handleHelp(ActionEvent actionEvent) {
    WebView wv = new WebView();
    setUpWebView(wv);

    StackPane root = new StackPane();
    root.getChildren().add(wv);
    Scene scene = new Scene(root, WIDTH , HEIGHT);

    Stage stage = new Stage();
    stage.setTitle("List of Commands");
    stage.setScene(scene);
    stage.show();

    wv.getEngine().load(
        CS_DUKE_WEBPAGE);
  }

  private void setUpWebView(WebView wv) {
    wv.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

      @Override
      public WebEngine call(PopupFeatures p) {
        Stage stage = new Stage(StageStyle.UTILITY);
        WebView wv2 = new WebView();
        stage.setScene(new Scene(wv2));
        stage.show();
        return wv2.getEngine(); }
    });
  }

  private void writeOutTab(ActionEvent actionEvent) {
    try{
      XMLWriter writer = new XMLWriter(myMainView);
      writer.outputFile();
    } catch (XMLException | ParserConfigurationException e) {
      throw new XMLException(exceptionBundle.getString("ParseError"));
    } catch(Exception e) {
      throw new XMLException(exceptionBundle.getString("WriteError"));
    }
  }

  private void setUpToolBarSize() {
    this.setMinSize(WIDTH, HEIGHT);
    this.setMaxSize(WIDTH, HEIGHT);
    this.setPrefSize(WIDTH, HEIGHT);
  }

  private void setUpLabels() {
    penLabel = new Label(labelBundle.getString("PenLabel"));
    backgroundLabel = new Label(labelBundle.getString("BackgroundLabel"));
    turtleLabel = new Label(labelBundle.getString("TurtleLabel"));
    languageLabel = new Label(labelBundle.getString("LanguageLabel"));
    extraLabel = new Label(labelBundle.getString("MoreFuncLabel"));
  }

}
