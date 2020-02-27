package slogo.controller;


import static javafx.application.Platform.exit;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.backendexceptions.InvalidCommandException;
import slogo.backendexternal.parser.Parser;
import slogo.commands.Command;
import slogo.frontendexternal.TurtleView;
import slogo.view.Display;

public class Controller extends Application {

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
    input.setOnKeyPressed(key -> quit(key.getCode()));
    runButton = myDisplay.getMainView().getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand(input));
    language = myDisplay.getMainView().getToolBar().getLanguageBox();
    language.setOnAction(event -> setLanguage(language));
    Scene myScene = myDisplay.getScene();
    myParser = new Parser();
    myModel = new TurtleModel();
    currentStatus = INITIAL_STATUS;
    myStage.setScene(myScene);
    myStage.setTitle(TITLE);
    myStage.setWidth(WIDTH);
    myStage.setHeight(HEIGHT);
    myStage.setResizable(false);
    myStage.show();
  }

  private void quit(KeyCode key) {
    if(key == KeyCode.ESCAPE){
      exit();
    }
  }

  private void sendCommand(TextField field){
    String input = field.getText();
    try{
      myParser.parseLine(input);
      List<Command> toSend = myParser.sendCommands();
      System.out.println("Parser Command");
      List<TurtleStatus> statuses = (List<TurtleStatus>) myModel
          .executeCommands(toSend, currentStatus);
//      myDisplay.getMainView().get.addText(myModel.getLastReturn());
      System.out.println("Status Size");
      System.out.println(statuses.size());

      if(statuses.size() > 1){
        setStatus(statuses.get(statuses.size() - 1));
        myDisplay.getMainView().moveTurtle(statuses);
      }
    }
    catch(InvalidCommandException e){
      System.out.println("are we catching?");
      myParser.addError();
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

  private void setStatus(TurtleStatus ts){
    currentStatus = ts;
  }

  private void displayVariables(){
    myDisplay.getMainView().getTextFields().clearVariables();
    myDisplay.getMainView().getTextFields().addVariableText(myParser.getVariableString());
  }


  private void displayQueries() {
    myDisplay.getMainView().getTextFields().clearQueries();
    myDisplay.getMainView().getTextFields().addCommandText("");
  }

  private void setLanguage(ComboBox language){
    myParser.setLanguage(language.getValue().toString());
  }
}
