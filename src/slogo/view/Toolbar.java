package slogo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.*;
import javax.imageio.ImageIO;
import slogo.frontendexternal.TurtleView;

/**
 * @author Shruthi Kumar, Nevzat Sevim
 */

public class Toolbar extends ToolBar {

  //Incorporate View and Text Field
  private MainView myMainView;
  private TextFields myTextFields;

  //Labels for DropDown Menus
  private final Label penLabel = new Label("Pen:");
  private final Label backgroundLabel = new Label("Background:");
  private final Label turtleLabel = new Label("Turtle:");
  private final Label languageLabel = new Label("Language:");

  //The Drop Down Menus Themselves
  private ColorPicker penMenu, backgroundMenu;
  private ComboBox languageMenu, turtleMenu;

  //The Buttons
  private Button commandButton, helpButton, changesButton;
  TextField textField;


  private static final int FRAMES_PER_SECOND = 60;
  private static final double MILLISECOND_DELAY = 10000/FRAMES_PER_SECOND;


  public Toolbar(MainView mainview) {
    this.myMainView = mainview;
    this.myTextFields = myMainView.getTextFields();
    textField = new TextField("Enter Command: ");

    createMenus();
    createButtons();

    //textField.setOnAction(this:: handleCommand);


    this.getItems().addAll(textField, commandButton, new Separator(),
                            turtleLabel, turtleMenu, penLabel, penMenu,
                            languageLabel, languageMenu, backgroundLabel, backgroundMenu,  changesButton, new Separator(),
                            helpButton);
  }

  /**
   * Helping methods to import menus and buttons to the toolbar
   */

  private void createMenus() {
    this.penMenu = new ColorPicker();
    penMenu.setMaxWidth(50);
    this.backgroundMenu = new ColorPicker();
    backgroundMenu.setMaxWidth(50);

    addTurtleSkins();
    this.languageMenu = new ComboBox();
    addLanguageChoices();
  }


  private void setUpTurtleMenu() {
    this.turtleMenu = new ComboBox();

    turtleMenu.setPromptText("Choose Turtle Skin");
    turtleMenu.setEditable(true);

    turtleMenu.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
      myMainView.stop();
      if (newValue == "Turtle") {
        myMainView.setSkin(0);
      } /*else if (newValue == toolbarBundle.getString("PercolationSim")) {
        choosingNewSim(PERCOLATIONNUM);
      }else if (newValue == toolbarBundle.getString("SegregationSim")){
        choosingNewSim(SEGREGATIONNUM);
      } else if (newValue == toolbarBundle.getString("PredatorPreySim")) {
        choosingNewSim(PREDATORPREYNUM);
      } else if (newValue == toolbarBundle.getString("FireSim")) {
        choosingNewSim(FIRENUM);
      } else if (newValue == toolbarBundle.getString("RPSSim")) {
        choosingNewSim(RPSNUM);
      } else if (newValue == toolbarBundle.getString("SugarscapeSim")) {
        choosingNewSim(SURGARNUM);
      }
      */
    });
  }
  //needs to be loaded from files not hardcoded
  private void addTurtleSkins() {
    //turtleMenu.setOnAction();
    turtleMenu.getItems().add(0, "Turtle");
    turtleMenu.getItems().add(1, "Mickey");
    turtleMenu.getItems().add(2, "Raphael");
  }

  //needs to be loaded from files not hardcoded
  private void addLanguageChoices() {
    languageMenu.getItems().add(0, "English");
    languageMenu.getItems().add(1, "Chinese");
    languageMenu.getItems().add(2, "French");
  }

  private void createButtons() {
    //this.commandButton = new Button("Run");

    EventHandler<ActionEvent> showHandler = event -> handleCommand(); //commandButton.getText());
    this.commandButton = makeButton("Go Command", showHandler);
    //commandButton.setOnAction(this:: handleCommand);

    this.helpButton = new Button("?");
    helpButton.setOnAction(this:: handleHelp);

    EventHandler<ActionEvent> showHandler1 = event -> handleChanges();
    this.changesButton = makeButton("Apply", showHandler1); //new Button("Apply");
    //changesButton.setOnAction(this::handleChanges);
  }

  private void addCommand() {
    myTextFields.addText(textField.getText());
  }

  /**
   * Methods that define the function of each Button
   */
  private void handleChanges() {

    this.myMainView.setBackgroundColor(backgroundMenu.getValue());
    this.myMainView.setPenColor(penMenu.getValue());

   // this.myMainView.draw();
  }

  private void handleHelp(ActionEvent actionEvent) {
  }


  private void handleCommand() {
    this.myMainView.sendCommand(textField.getText());
    myTextFields.addText(textField.getText());
  }



  // Public Set Methods
  public void setTextField(TextFields tf){this.myTextFields = tf;}

  private Button makeButton (String property, EventHandler<ActionEvent> handler) {
    Button result = new Button(property);

    result.setOnAction(handler);
    return result;
  }

  /**
   * Display given URL.

  public void showPage (String url) {
    try {
      myTextFields.addText(myModel.go(url));
    }
    catch (BrowserException e) {
      showError(e.getMessage());
    }
  }
   */

}
