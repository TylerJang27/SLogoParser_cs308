package slogo.controller;

<<<<<<< HEAD

import static javafx.application.Platform.exit;

import java.util.List;
=======
//import java.util.ArrayList;
//import java.util.List;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
>>>>>>> origin/master
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
<<<<<<< HEAD
import org.w3c.dom.Text;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
=======
//import javafx.util.Duration;
//import javax.swing.KeyStroke;
>>>>>>> origin/master
import slogo.backendexternal.parser.Parser;

public class Controller extends Application {

<<<<<<< HEAD
  private static final String TITLE = "SLogo";
  private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();
  private static final int WIDTH = 1075;
  private static final int HEIGHT = 758;
  private Display myDisplay;
  private Parser myParser;
  private TurtleModel myModel;
  private TextField input;
  private Button runButton;
  private ComboBox language;
  private TurtleStatus currentStatus;
=======
  public static final String TITLE = "SLogo";
  public static final Paint BACKGROUND = Color.WHEAT;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth()/2.0;
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight()/2.0;
  public static final int MILLISECOND_DELAY = 100000 / FRAMES_PER_SECOND;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private Stage myStage;
  private Group layout;
  private Scene myScene;
  private Parser myParser;
  private int speed;
>>>>>>> origin/master

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
<<<<<<< HEAD
    Stage myStage = new Stage();
    myDisplay = new Display();
    input = myDisplay.getMainView().getToolBar().getTextField();
    input.setOnKeyPressed(key -> quit(key.getCode()));
    runButton = myDisplay.getMainView().getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand(input));
    language = myDisplay.getMainView().getToolBar().getLanguageBox();
    language.setOnAction(event -> setLanguage(language));
    Scene myScene = myDisplay.getScene();
=======
    myStage = new Stage();
    layout = new Group();
    myScene = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
>>>>>>> origin/master
    myParser = new Parser();
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.setWidth(WIDTH);
    myStage.setHeight(HEIGHT);
    myStage.setResizable(false);
    myStage.show();
<<<<<<< HEAD
=======
    TextField input = new TextField();
    input.setOnKeyPressed(key -> sendCommand(key.getCode(), input));

    // If we want animation
//    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
//    Timeline animation = new Timeline();
//    animation.setCycleCount(Timeline.INDEFINITE);
//    animation.getKeyFrames().add(frame);
//    animation.play();
>>>>>>> origin/master
  }

  private void quit(KeyCode key) {
    if(key == KeyCode.ESCAPE){
      exit();
    }
  }

  private void sendCommand(TextField field){
    String input = field.getText();
<<<<<<< HEAD
    try{
      myParser.parseLine(input);
      List<Command> toSend = myParser.sendCommands();
      System.out.println("Parser Command");
      List<TurtleStatus> statuses = (List<TurtleStatus>) myModel
          .executeCommands(toSend, currentStatus);
      System.out.println("Status Size");
      System.out.println(statuses.size());

      if(statuses.size() > 1){
        setStatus(statuses.get(statuses.size() - 1));
        myDisplay.getMainView().moveTurtle(statuses);
      }
    }
    catch(Exception e){
      myParser.addError(e.getMessage());
=======
    if(key == KeyCode.SHIFT){
      // FRONT END STORE COMMAND IN HISTORY
      myParser.parseLine(input);
      field.clear();
    }
    if(key == KeyCode.ENTER){
      // FRONT END STORE COMMAND IN HISTORY
      myParser.sendCommands();
>>>>>>> origin/master
    }
    field.clear();
    displayHistory();
    displayVariables();
    displayQueries();
  }

  private void displayHistory(){
    myDisplay.getMainView().getTextFields().clearCommands();
    StringBuilder display = new StringBuilder();
    for(String s : myParser.getCommandHistory()){
      display.append(s);
      display.append("\n");
    }
    myDisplay.getMainView().getTextFields().addCommandText(display.toString());
  }

<<<<<<< HEAD
  private void setStatus(TurtleStatus ts){
    currentStatus = ts;
  }

  private void displayVariables(){
    myDisplay.getMainView().getTextFields().clearVariables();
    myDisplay.getMainView().getTextFields().addVariableText(myParser.getVariableString());
  }


  private void displayQueries() {
    myDisplay.getMainView().getTextFields().clearQueries();
    myDisplay.getMainView().getTextFields().addQueriesText(myModel.getLastReturn());
  }

  private void setLanguage(ComboBox language){
    myParser.setLanguage(language.getValue().toString());
  }
=======

  private void step(double elapsedTime) {}

>>>>>>> origin/master
}
