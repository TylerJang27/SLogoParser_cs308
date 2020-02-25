package slogo.controller;

//import java.util.ArrayList;
//import java.util.List;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
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
import slogo.backendexternal.parser.Parser;
import slogo.commands.Command;
import slogo.view.Display;
import slogo.view.TextFields;

public class Controller extends Application {

  public static final String TITLE = "SLogo";
  public static final Paint BACKGROUND = Color.WHEAT;
  public static final int FRAMES_PER_SECOND = 60;
  public static final int MILLISECOND_DELAY = 100000 / FRAMES_PER_SECOND;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private Stage myStage;
  private Group layout;
  private Scene myScene;
  private Display myDisplay;
  private Parser myParser;
  private TextField input;
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
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.show();
    input.setOnKeyPressed(key -> sendCommand(key.getCode(), input));
  }

  private void sendCommand(KeyCode key, TextField field){
    String input = field.getText();
    if(key == KeyCode.SHIFT){
      myParser.parseLine(input);
      field.clear();
    }
    if(key == KeyCode.ENTER){
      List<Command> toSend = myParser.sendCommands();
    }
  }
}
