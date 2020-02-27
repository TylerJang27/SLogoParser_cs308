package slogo.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.*;

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

    this.myMainView.getPane().setBackground(new Background(new BackgroundFill(backgroundMenu.getValue(), CornerRadii.EMPTY, new Insets(0))));
    this.myMainView.getTurtle().getPenView().setMyPenColor(penMenu.getValue());

    if(!turtleMenu.getSelectionModel().isEmpty()) {
      myMainView.getTurtle().setImageView(new ImageView(new Image("/slogo/view/imagesFolder/" + turtleMenu.getValue() + ".png")));

      myMainView.getTurtle().myImageView.setFitWidth(50);
      myMainView.getTurtle().myImageView.setFitHeight(50);

      myMainView.getTurtle().myImageView.setLayoutX(myMainView.getTurtle().getMyStartXPos());
      myMainView.getTurtle().myImageView.setLayoutY(myMainView.getTurtle().getMyStartYPos());
    }

    myMainView.getPane().getChildren().set(0, myMainView.getTurtle().myImageView);
  }

  private void handleHelp(ActionEvent actionEvent) {
    WebView wv = new WebView();
    wv.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

      @Override
      public WebEngine call(PopupFeatures p) {
        Stage stage = new Stage(StageStyle.UTILITY);
        WebView wv2 = new WebView();
        stage.setScene(new Scene(wv2));
        stage.show();
        return wv2.getEngine(); }
    });

    StackPane root = new StackPane();
    root.getChildren().add(wv);
    Scene scene = new Scene(root, 1300 , 1000);

    Stage stage = new Stage();
    stage.setTitle("List of Commands");
    stage.setScene(scene);
    stage.show();

    wv.getEngine().load("https://www2.cs.duke.edu/courses/spring20/compsci308/assign/03_parser/commands.php");
  }

  private void handleCommand(ActionEvent actionEvent) {
    this.myMainView.sendCommand(textField.getText());
    myTextFields.addText(textField.getText());
  }

  /** Methods for useful Getters and Setters */

  // Public Set Methods
  public void setTextField(TextFields tf){this.myTextFields = tf;}

  // Public Get Methods
  public TextField getTextField(){ return textField; }
}
