package slogo.controller;

//import java.util.ArrayList;
//import java.util.List;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
//import javafx.util.Duration;
//import javax.swing.KeyStroke;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.parser.Parser;
import slogo.commands.Command;
import slogo.frontendexternal.TurtleView;
import slogo.view.Display;
import slogo.view.TextFields;

public class Controller extends Application {

  public static final String TITLE = "SLogo";
  public static final Paint BACKGROUND = Color.WHEAT;
  public static final int FRAMES_PER_SECOND = 60;
  public static final int MILLISECOND_DELAY = 100000 / FRAMES_PER_SECOND;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final TurtleStatus INITIAL_STATUS = new TurtleStatus();

  private Stage myStage;
  private Group layout;
  private Scene myScene;
  private Display myDisplay;
  private Parser myParser;
  private TurtleModel myModel;
  private TurtleView myView;
  private TextField input;
  private TurtleStatus currentStatus;
  private int speed;

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    myStage = new Stage();
    myDisplay = new Display();
    input = myDisplay.getMainView().getToolBar().getTextField();
    myScene = myDisplay.getScene();
    myParser = new Parser();
    myModel = new TurtleModel();
    myView = new TurtleView();
    currentStatus = INITIAL_STATUS;
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.show();
    input.setOnKeyPressed(key -> sendCommand(key.getCode(), input));
  }

  private void sendCommand(KeyCode key, TextField field){
    String input = field.getText();
    if(key == KeyCode.ENTER){
      myParser.parseLine(input);
      field.clear();
      List<Command> toSend = myParser.sendCommands();
      List<TurtleStatus> statuses = (List<TurtleStatus>) myModel.executeCommands(toSend);
      setStatus(statuses.get(statuses.size() - 1));
      myDisplay.getMainView().getTurtle().executeState(statuses);
    }
  }

  private void setStatus(TurtleStatus ts){
    currentStatus = ts;
  }
}
