package slogo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.parser.ErrorHandler;
import slogo.backendexternal.parser.Parser;
import slogo.commands.Command;
import slogo.view.Display;
import slogo.view.InputFields.Console;
import slogo.view.MainView;

public class Controller extends Application {

  private static final String TITLE = "SLogo";
  private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();
  private static final int WIDTH = (int) Screen.getPrimary().getBounds().getWidth() - 100;
  private static final int HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() - 100;
  public static final int FRAMES_PER_SECOND = 60;
  public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;


  private Display myDisplay;
  private Parser myParser;
  private TurtleModel myModel;
  private Console console;
  private Button runButton;
  private ComboBox language;
  private TurtleStatus currentStatus;
  private ErrorHandler errorHandler;
  private Button addTabButton;
  private Map<MainView, TurtleModel> mainViewTurtleModelMap;

  /**
   * Start of the program.
   */
  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage currentStage) {
    myDisplay = new Display();
    myParser = new Parser();
    errorHandler = new ErrorHandler();
    mainViewTurtleModelMap = new HashMap<MainView, TurtleModel>();
    //mainViewTurtleModelMap.put(myDisplay.getMainView(), myModel);


    myModel = new TurtleModel();
    setUpTurtle();


    /*
    console = myDisplay.getMainView().getTextFields().getConsole();
    runButton = myDisplay.getMainView().getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand());
    language = myDisplay.getMainView().getToolBar().getLanguageBox();
    language.setOnAction(event -> setLanguage(language));
    addTabButton = myDisplay.getAddTabButton();
    addTabButton.setOnAction(event -> addTab());
    currentStatus = INITIAL_STATUS;
     */

    addTabButton = myDisplay.getAddTabButton();
    addTabButton.setOnAction(event -> addTab());
    Scene myScene = myDisplay.getScene();
    currentStage.setScene(myScene);
    currentStage.setTitle(TITLE);
    currentStage.setWidth(WIDTH);
    currentStage.setHeight(HEIGHT);
    currentStage.setResizable(false);
    currentStage.show();

    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
      try {
        step();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void step() {
    switchTurtle();
  }

  private void addTab() {
    myDisplay.addTab();
    setUpTurtle();
  }

  private void switchTurtle() {
    console = myDisplay.getMainView().getTextFields().getConsole();
    runButton = myDisplay.getMainView().getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand());
    language = myDisplay.getMainView().getToolBar().getLanguageBox();
    language.setOnAction(event -> setLanguage(language));
  }

  private void setUpTurtle() {
    System.out.println(myDisplay.getMainView());
    switchTurtle();
    currentStatus = INITIAL_STATUS;
  }

  private void sendCommand(){
    try{
      myParser.parseLine(console.getText());
      List<Command> toSend = myParser.sendCommands();
      List<TurtleStatus> statuses = myModel.executeCommands(toSend);
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
