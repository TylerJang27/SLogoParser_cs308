package slogo.controller;

//import java.util.ArrayList;
//import java.util.List;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
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

public class Controller extends Application {

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

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    myStage = new Stage();
    layout = new Group();
    myScene = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
    myParser = new Parser();
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.show();
    TextField input = new TextField();
    input.setOnKeyPressed(key -> sendCommand(key.getCode(), input));

    // If we want animation
//    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
//    Timeline animation = new Timeline();
//    animation.setCycleCount(Timeline.INDEFINITE);
//    animation.getKeyFrames().add(frame);
//    animation.play();
  }

  private void sendCommand(KeyCode key, TextField field){
    String input = field.getText();
    if(key == KeyCode.SHIFT){
      // FRONT END STORE COMMAND IN HISTORY
      myParser.parseLine(input);
      field.clear();
    }
    if(key == KeyCode.ENTER){
      // FRONT END STORE COMMAND IN HISTORY
      myParser.sendCommands();
    }
  }


  private void step(double elapsedTime) {}

}
