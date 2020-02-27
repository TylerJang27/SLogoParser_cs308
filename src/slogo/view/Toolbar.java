package slogo.view;

import javafx.event.ActionEvent;
<<<<<<< HEAD
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
import javafx.scene.paint.Color;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.*;
=======
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
>>>>>>> origin/master

import javax.xml.parsers.ParserConfigurationException;
import java.util.ResourceBundle;

public class Toolbar extends ToolBar {
  private MainView myMainView;
<<<<<<< HEAD
  private TextFields myTextFields;

  //The Drop Down Menus Themselves
  private ColorPicker penMenu, backgroundMenu;
  private ComboBox languageMenu, turtleMenu;

  //The Buttons
  private Button commandButton, helpButton, changesButton;
  TextField textField;

  //Timeline Inputs
=======
>>>>>>> origin/master
  private static final int FRAMES_PER_SECOND = 60;
  private static final double MILLISECOND_DELAY = 10000/FRAMES_PER_SECOND;
  private Timeline animation;



  public Toolbar(MainView mainview) {
    myMainView = mainview;

    TextField textField = new TextField("Enter Command: ");
    textField.setOnAction(this:: handleCommand);

<<<<<<< HEAD
    Label penLabel = new Label("Pen:");
    Label backgroundLabel = new Label("Background:");
    Label turtleLabel = new Label("Turtle:");
    Label languageLabel = new Label("Language:");
=======
    Button runCommand = new Button("Run");
    runCommand.setOnAction(this:: handlePlay);

    animationFunctions();
    this.getItems().addAll(textField, runCommand);
>>>>>>> origin/master


<<<<<<< HEAD
  public Button getCommandButton(){
    return commandButton;
  }

  public ComboBox getLanguageBox() {return languageMenu; }

  /**
   * Helping methods to import menus and buttons to the toolbar
   */

  private void createMenus() {
    //Color Menus
    this.penMenu = new ColorPicker();
    penMenu.setValue(Color.BLACK);
    penMenu.setMaxWidth(50);

    this.backgroundMenu = new ColorPicker();
    backgroundMenu.setValue(Color.LIGHTGRAY);
    backgroundMenu.setMaxWidth(50);

    //Turtle Menu
    this.turtleMenu = new ComboBox();
    turtleMenu.setPromptText("raphael");
    turtleMenu.getItems().addAll("mickey", "raphael", "turtle");

    //Language Menu
    this.languageMenu = new ComboBox();
    languageMenu.setPromptText("English");
    languageMenu.getItems().addAll("English", "Chinese", "French", "German", "Italian",
        "Portuguese", "Russian", "Spanish", "Urdu");
  }

  private void createButtons() {
    this.commandButton = new Button("Run");

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

      myMainView.getTurtle().myImageView.setLayoutX(myMainView.getTurtle().getMyStartXPos());
      myMainView.getTurtle().myImageView.setLayoutY(myMainView.getTurtle().getMyStartYPos());
      myMainView.getTurtle().myImageView.setFitWidth(myMainView.getTurtleSize());
      myMainView.getTurtle().myImageView.setFitHeight(myMainView.getTurtleSize());

      myMainView.getPane().getChildren().set(0, myMainView.getTurtle().myImageView);
      myMainView.getTurtle().myImageView.setX(myMainView.getTurtle().myImageView.getX() - myMainView.getTurtle().myImageView.getFitWidth() / 2);
      myMainView.getTurtle().myImageView.setY(myMainView.getTurtle().myImageView.getY() - myMainView.getTurtle().myImageView.getFitHeight() / 2);
    }
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


  /** Methods for useful Getters and Setters */

  // Public Set Methods
  public void setTextField(TextFields tf){this.myTextFields = tf;}
=======
  }

  private void handleCommand(ActionEvent actionEvent) {

  }


  /**
   * Method that sets up the animation, in which the myMainview step method is called every second which updates the
   * grid on the screen.
   */
  public void animationFunctions() {

    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
      try {
        myMainView.step();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
  }

  /**
   * Starts the animation and timer
   * @param actionEvent The play button pressed
   */
  private void handlePlay(ActionEvent actionEvent) {
    animation.play();
    //timer.start();
  }


>>>>>>> origin/master

  // Public Get Methods
  public TextField getTextField(){ return textField; }
}
