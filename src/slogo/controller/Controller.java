package slogo.controller;


import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.parser.Parser;
import slogo.commands.Command;
import slogo.frontendexternal.TurtleView;
import slogo.view.Display;

public class Controller extends Application {

  public static final String TITLE = "SLogo";
  public static final Paint BACKGROUND = Color.WHEAT;
  public static final int FRAMES_PER_SECOND = 60;
  public static final int MILLISECOND_DELAY = 100000 / FRAMES_PER_SECOND;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final TurtleStatus INITIAL_STATUS = new TurtleStatus();

  private Stage myStage;
  private Scene myScene;
  private Display myDisplay;
  private Parser myParser;
  private TurtleModel myModel;
  private TextField input;
  private TurtleStatus currentStatus;

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
    currentStatus = INITIAL_STATUS;
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.show();
    input.setOnKeyPressed(key -> sendCommand(key.getCode(), input));
  }

  private void sendCommand(KeyCode key, TextField field){
    String input = field.getText(); //TODO: TYLER LOOK HERE
    if(key == KeyCode.ENTER){
      myParser.parseLine(input);
      field.clear();
      List<Command> toSend = myParser.sendCommands();
      List<TurtleStatus> statuses = myModel.executeCommands(toSend, currentStatus);
      if (statuses.size() > 0) {
        setStatus(statuses.get(statuses.size() - 1));
      }
      myDisplay.getMainView().moveTurtle(statuses);
    }
  }

  private void setStatus(TurtleStatus ts){
    currentStatus = ts;
  }
}
