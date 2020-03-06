package slogo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import slogo.backendexternal.TurtleModel;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.parser.ErrorHandler;
import slogo.backendexternal.parser.Parser;
import slogo.backendexternal.parser.Translator;
import slogo.commands.Command;
import slogo.view.Display;
import slogo.view.InputFields.Console;
import slogo.view.InputFields.MoveArrows;
import slogo.view.InputFields.UserDefinitions;
import slogo.view.MainView;

public class Controller extends Application {

  private static final String TITLE = "SLogo";
  private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();
  private Display myDisplay;
  private Parser myParser;
  private TurtleModel myModel;
  private Console console;
  private UserDefinitions userDefinitions;
  private Button runButton;
  private ComboBox language;
  private ComboBox modeMenu;
  private MoveArrows arrows;
  private TurtleStatus currentStatus;
  private ErrorHandler errorHandler;
  private Button addTabButton;
  private Map<Tab, TurtleModel> tabTurtleModelMap;
  private List<Tab> tabs;
  private Translator translator;
  private Tab currentTab;
  private MainView mainView;

  /**
   * Start of the program.
   */
  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage currentStage) {
    myDisplay = new Display();
    translator = new Translator();
    myParser = new Parser(translator);
    myParser.setDisplay(myDisplay);
    errorHandler = new ErrorHandler();
    translator = new Translator();
    tabTurtleModelMap = new HashMap<Tab, TurtleModel>();
    setTabs();
    changeOnWrite();
    myModel = getModel(tabs.get(0));
    setListeners(tabs.get(0));
    addTabButton = myDisplay.getAddTabButton();
    addTabButton.setOnAction(event -> addTab());
    Scene myScene = myDisplay.getScene();
    currentStage.setScene(myScene);
    currentStage.setTitle(TITLE);
    currentStage.setWidth(1070);
    currentStage.setHeight(800);
    currentStage.setResizable(false);
    currentStage.show();
  }


  private void setTabs() {
    tabs = myDisplay.getTabPane().getTabs();
    for(Tab tab : tabs){
      tab.setOnSelectionChanged(event -> setListeners(tab));
    }
  }

  private void changeOnWrite(){
    for(Tab tab : tabs){
      MainView tabMainView = (MainView) tab.getGraphic();
      TextField tabConsole = tabMainView.getTextFields().getConsole().getEntry();
      tabConsole.setOnMouseClicked(event -> setListeners(tab));
    }
  }

  private void addTab() {
    myDisplay.addTab();
    setTabs();
    changeOnWrite();
  }

  private TurtleModel getModel(Tab tab) {
    tabTurtleModelMap.putIfAbsent(tab, new TurtleModel());
    return tabTurtleModelMap.get(tab);
  }

  private void setListeners(Tab tab) {
    currentTab = tab;
    myModel = getModel(currentTab);
    mainView = (MainView) currentTab.getGraphic();
    console = mainView.getTextFields().getConsole();
    userDefinitions = mainView.getTextFields().getUserDefinitions();
    runButton = mainView.getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand());
    language = mainView.getToolBar().getLanguageBox();
    language.setOnAction(event -> setLanguage(language));
    modeMenu = mainView.getToolBar().getModeMenu();
    modeMenu.setOnAction(event -> setMode(modeMenu));
    arrows = mainView.getTextFields().getMoveArrows();
    for(Button arrow : arrows.getButtons()){
      arrow.setOnAction(event -> moveTurtle(arrow, arrows.getIncrement()));
    }
  }

  private void moveTurtle(Button arrow, double increment) {
    //MOVE TURTLE DISTANCE BASED ON WHICH ARROW CLICKED, INCREMENT IS AMOUNT TO MOVE BYlk
  }


  private void sendCommand(){
    try{
      //System.out.println(console.getText());
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
    String newLanguage = language.getValue().toString();
    console.translateHistory(translator, newLanguage);
    userDefinitions.translateDefinitions(translator, newLanguage);
    translator.setLanguage(newLanguage);
    myParser.setLanguage(translator);
  }

  private void setMode(ComboBox menu){
    myParser.setMode(menu.getValue().toString());
  }
}
