package slogo.controller;


import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.w3c.dom.Text;
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
  private Button runButton;
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
    runButton = myDisplay.getMainView().getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand(input));
    Scene myScene = myDisplay.getScene();
    myParser = new Parser();
    myModel = new TurtleModel();
    currentStatus = INITIAL_STATUS;
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.setWidth(1075); myStage.setHeight(858);
    myStage.setResizable(false);
    myStage.show();
  }

  private void sendCommand(TextField field){
    String input = field.getText();
    System.out.println(input);
    System.out.println("Are we getting here");
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
    displayVariables();
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

  private void setStatus(TurtleStatus ts){
    currentStatus = ts;
  }

  private void displayVariables(){
    myDisplay.getMainView().getTextFields().clearVariables();
    myDisplay.getMainView().getTextFields().addVariableText(myParser.getVariableString());
  }
}
