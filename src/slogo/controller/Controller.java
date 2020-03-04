package slogo.controller;


import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.parser.ErrorHandler;
import slogo.backendexternal.parser.Parser;
import slogo.commands.Command;
import slogo.view.Display;
import slogo.view.InputFields.Console;

public class Controller extends Application {

  private static final String TITLE = "SLogo";
  private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();
  private static final int WIDTH = 1000;
  private static final int HEIGHT = 750;
  private Display myDisplay;
  private Parser myParser;
  private TurtleModel myModel;
  private Console console;
  private Button runButton;
  private ComboBox language;
  private TurtleStatus currentStatus;
  private ErrorHandler errorHandler;

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    myDisplay = new Display();
    myParser = new Parser();
    errorHandler = new ErrorHandler();
    myModel = new TurtleModel();
    console = myDisplay.getMainView().getTextFields().getConsole();
    runButton = myDisplay.getMainView().getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand());
    language = myDisplay.getMainView().getToolBar().getLanguageBox();
    language.setOnAction(event -> setLanguage(language));
    Scene myScene = myDisplay.getScene();
    currentStatus = INITIAL_STATUS;
    currentStage.setScene(myScene);
    currentStage.setTitle(TITLE);
    currentStage.setWidth(WIDTH);
    currentStage.setHeight(HEIGHT);
    currentStage.setResizable(false);
    currentStage.show();
  }

  private void sendCommand(){
    try{
      myParser.parseLine(console.getText());
      List<Command> toSend = myParser.sendCommands();
      List<TurtleStatus> statuses = myModel.executeCommands(toSend, currentStatus);
      if(statuses.size() > 1){
        setStatus(statuses.get(statuses.size() - 1));
        myDisplay.getMainView().moveTurtle(statuses);
      }
      console.addHistory();
      console.displayHistory();
      displayVariables();
      displayQueries();
    }
    catch(Exception e){
      console.addError(errorHandler.getErrorMessage(e.getMessage(), myParser.getCommands()));
      console.getEntry().setOnKeyPressed(key -> handlePrompt(key.getCode()));
    }
  }

  private void handlePrompt(KeyCode key){
    if(key == KeyCode.Y){
      console.getEntry().setText(errorHandler.fixLine(myParser.getLastLine()));
      sendCommand();
    }
    if(key == KeyCode.N){
      console.displayHistory();
      displayVariables();
      displayQueries();
    }
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
    myDisplay.getMainView().getTextFields().addQueriesText();
  }

  private void setLanguage(ComboBox language){
    myParser.setLanguage(language.getValue().toString());
  }
}
