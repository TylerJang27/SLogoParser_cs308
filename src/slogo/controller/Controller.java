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

  private static final String TITLE = "SLogo";
  private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();
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
    Stage myStage = new Stage();
    myDisplay = new Display();
    input = myDisplay.getMainView().getToolBar().getTextField();
    Scene myScene = myDisplay.getScene();
    myParser = new Parser();
    myModel = new TurtleModel();
    currentStatus = INITIAL_STATUS;
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.show();
    input.setOnKeyPressed(key -> sendCommand(key.getCode(), input));
  }

  private void sendCommand(KeyCode key, TextField field){
    String input = field.getText();
    if(key == KeyCode.ENTER) {
      myParser.parseLine(input);
      field.clear();

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

      displayHistory();
    }
  }

  private void displayHistory(){
    myDisplay.getMainView().getTextFields().clearCommands();
    StringBuilder display = new StringBuilder();
    for(String s : myParser.getCommandHistory()){
      display.append(s);
      display.append("\n");
    }
    myDisplay.getMainView().getTextFields().addText(display.toString());
  }

  private void setStatus(TurtleStatus ts){
    currentStatus = ts;
  }
}
