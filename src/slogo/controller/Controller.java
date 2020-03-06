package slogo.controller;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.backendexternal.TurtleManager;
import slogo.backendexternal.TurtleStatus;
import slogo.backendexternal.parser.ErrorHandler;
import slogo.backendexternal.parser.Parser;
import slogo.backendexternal.parser.Translator;
import slogo.commands.Command;
import slogo.configuration.XMLException;
import slogo.configuration.XMLReader;
import slogo.view.Display;
import slogo.view.InputFields.Console;
import slogo.view.InputFields.MoveArrows;
import slogo.view.InputFields.UserDefinitions;
import slogo.view.MainView;

public class Controller extends Application {

  private static final String TITLE = "SLogo";
  private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();
  public static final int FRAMES_PER_SECOND = 60;
  public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;


  private Display myDisplay;
  private Parser myParser;
  private TurtleManager myModel;
  private Console console;
  private UserDefinitions userDefinitions;
  private Button runButton;
  private ComboBox language;
  private ComboBox modeMenu;
  private MoveArrows arrows;
  private TurtleStatus currentStatus;
  private ErrorHandler errorHandler;
  private Button addTabButton, addTabPreferencesButton;
  private Map<Tab, TurtleManager> tabTurtleModelMap;
  private List<Tab> tabs;
  private Translator translator;
  private Tab currentTab;
  public static final String DATA_FILE_EXTENSION = "*.xml";
  public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);


  /**
   * Start of the program.
   */
  public static void main(String[] args) { launch(args); }

  @Override
  public void start(Stage currentStage) {
    myDisplay = new Display();
    translator = new Translator();
    myParser = new Parser(translator);
    errorHandler = new ErrorHandler();
    translator = new Translator();
    tabTurtleModelMap = new HashMap<Tab, TurtleManager>();
    setTabs();
    //mainViewTurtleModelMap.put(myDisplay.getMainView(), myModel);
    myModel = getModel(tabs.get(0));
    setListeners(tabs.get(0));
    addTabButton = myDisplay.getAddTabButton();
    addTabButton.setOnAction(event -> addTab());
    addTabPreferencesButton = myDisplay.getAddTabFromPreferencesButton();
    addTabPreferencesButton.setOnAction(event -> uploadNewFile());
    Scene myScene = myDisplay.getScene();

    currentStage.setScene(myScene);
    currentStage.setTitle(TITLE);
    currentStage.setWidth(1070);
    currentStage.setHeight(800);
    currentStage.setResizable(false);
    currentStage.show();
  }

  private void uploadNewFile() {
     readFileSimulation(new Stage());
  }

  public void readFileSimulation(Stage primaryStage) {
    File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
    while(dataFile != null) {
      try {
        XMLReader reader = new XMLReader("media");
        myDisplay.addTab(reader.getMainView(dataFile.getPath()));
        setTabs();
        primaryStage.close();
      }
      catch (XMLException e) {
        showMessage(AlertType.ERROR, e.getMessage());
      }
      dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
    }
    primaryStage.close();
  }


  private void showMessage (AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  private void setTabs() {
    tabs = myDisplay.getTabPane().getTabs();
    for(Tab tab : tabs){
      tab.setOnSelectionChanged(event -> setListeners(tab));
    }
  }

  private void addTab() {
    myDisplay.addTab(null);
    setTabs();
  }

  private TurtleManager getModel(Tab tab) {
    tabTurtleModelMap.putIfAbsent(tab, new TurtleManager());
    return tabTurtleModelMap.get(tab);
  }

  private void setListeners(Tab tab) {
    currentStatus = INITIAL_STATUS; //TODO GET CURRENT STATUS FROM FRONT END
    myModel = getModel(tab);
    console = myDisplay.getMainView().getTextFields().getConsole();
    userDefinitions = myDisplay.getMainView().getTextFields().getUserDefinitions();
    runButton = myDisplay.getMainView().getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand());
    language = myDisplay.getMainView().getToolBar().getLanguageBox();
    language.setOnAction(event -> setLanguage(language));
//    modeMenu = myDisplay.getMainView().getToolBar().getModeMenu();
//    modeMenu.setOnAction(event -> setMode(modeMenu));
    arrows = myDisplay.getMainView().getTextFields().getMoveArrows();
    for(Button arrow : arrows.getButtons()){
      arrow.setOnAction(event -> moveTurtle(arrow, arrows.getIncrement()));
    }
  }

  private void moveTurtle(Button arrow, double increment) {
    //MOVE TURTLE DISTANCE BASED ON WHICH ARROW CLICKED, INCREMENT IS AMOUNT TO MOVE BYlk
  }


  private void sendCommand(){
    try{
      myParser.parseLine(console.getText());
      List<Command> toSend = myParser.sendCommands();
      List<TurtleStatus> statuses = myModel.executeCommands(toSend);
      if(statuses.size() > 0){
        setStatus(statuses.get(statuses.size() - 1));
        myDisplay.getMainView().moveTurtle(statuses);
      }
      console.addHistory();
      console.displayHistory();
      displayVariables();
      displayQueries();
    }
    catch(Exception e){
      e.printStackTrace();
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

  private static FileChooser makeChooser (String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Open Data File");
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(System.getProperty("user.dir")));
    result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", extensionAccepted));
    return result;
  }
}
