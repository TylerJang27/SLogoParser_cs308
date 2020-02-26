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

  //The Drop Down Menus Themselves
  private ColorPicker penMenu, backgroundMenu;
  private ComboBox languageMenu, turtleMenu;

  //The Buttons
  private Button commandButton, helpButton, changesButton;
  TextField textField;

  //Timeline Inputs
  private static final int FRAMES_PER_SECOND = 60;
  private static final double MILLISECOND_DELAY = 10000/FRAMES_PER_SECOND;


  public Toolbar(MainView mainview) {
    this.myMainView = mainview;
    this.myTextFields = myMainView.getTextFields();
    textField = new TextField("Enter Command: ");

    createMenus();
    createButtons();

    Label penLabel = new Label("Pen:");
    Label backgroundLabel = new Label("Background:");
    Label turtleLabel = new Label("Turtle:");
    Label languageLabel = new Label("Language:");

    this.getItems().addAll(textField, commandButton, new Separator(),
                            turtleLabel, turtleMenu, penLabel, penMenu,
                            languageLabel, languageMenu, backgroundLabel, backgroundMenu,  changesButton, new Separator(),
                            helpButton);
  }

  /** Helping methods to import menus and buttons to the toolbar*/
  private void createMenus() {
    //Color Menus
    this.penMenu = new ColorPicker();
    penMenu.setMaxWidth(50);
    this.backgroundMenu = new ColorPicker();
    backgroundMenu.setMaxWidth(50);

    //Turtle Menu
    this.turtleMenu = new ComboBox();
    turtleMenu.setPromptText("raphael");
    turtleMenu.getItems().addAll("mickey", "raphael", "turtle");

    //Language Menu
    this.languageMenu = new ComboBox();
    languageMenu.setPromptText("Choose Language");
    languageMenu.getItems().addAll("English", "French", "Not Turkish");
  }

  private void createButtons() {
    this.commandButton = new Button("Run");
    commandButton.setOnAction(this:: handleCommand);

    this.helpButton = new Button("?");
    helpButton.setOnAction(this:: handleHelp);

    this.changesButton = new Button("Apply");
    changesButton.setOnAction(this::handleChanges);
  }


  /** Methods that define the function of each Button */
  private void handleChanges(ActionEvent actionEvent) {

    this.myMainView.setBackgroundColor(backgroundMenu.getValue());
    this.myMainView.setPenColor(penMenu.getValue());

    if(!turtleMenu.getSelectionModel().isEmpty())
    this.myMainView.getTurtle().setImageView(new ImageView(new Image("/slogo/view/imagesFolder/" + turtleMenu.getValue() + ".png")));
  }

  private void handleHelp(ActionEvent actionEvent) { }

  private void handleCommand(ActionEvent actionEvent) {
    this.myMainView.sendCommand(textField.getText());
    myTextFields.addText(textField.getText());
  }

  // Public Set Methods
  public void setTextField(TextFields tf){this.myTextFields = tf;}

  // Public Get Methods
  public TextField getTextField(){ return textField; }

}
