package slogo.controller;


import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
//<<<<<<< HEAD
//  private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();
//  public static final int FRAMES_PER_SECOND = 60;
//  public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
//
//
//=======
//>>>>>>> 96032fc18f2aca4a4f2caa7548b44d84ab439890
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
//<<<<<<< HEAD
  private Button addTabButton, addTabPreferencesButton;
//=======
  //private Button addTabButton;
  private Button uploadFile;
//>>>>>>> 96032fc18f2aca4a4f2caa7548b44d84ab439890
  private Map<Tab, TurtleManager> tabTurtleModelMap;
  private List<Tab> tabs;
  private Translator translator;
  private Tab currentTab;
  public static final String DATA_FILE_EXTENSION = "*.xml";
  public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
  private MainView mainView;


  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    myDisplay = new Display();
    translator = new Translator();
    myParser = new Parser(translator);
    //myParser.setView(myDisplay.getMainView());
    errorHandler = new ErrorHandler();
    translator = new Translator();
    tabTurtleModelMap = new HashMap<>();
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
    currentStage.setResizable(true);
    currentStage.show();
  }

  private void setTabs() {
    tabs = myDisplay.getTabPane().getTabs();
    for (Tab tab : tabs) {
      tab.setOnSelectionChanged(event -> setListeners(tab));
    }
  }

  private void uploadNewFile() {
     readFileSimulation(new Stage());
  }

  public void readFileSimulation(Stage primaryStage) {
    File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
    while(dataFile != null) {
      try {
        XMLReader reader = new XMLReader("media");
        primaryStage.close();
        myDisplay.addTab(reader.getMainView(dataFile.getPath()));
        setTabs();
        return;
      }
      catch (XMLException e) {
        showMessage(AlertType.ERROR, e.getMessage());
      }
    }
    primaryStage.close();
  }

  private void changeOnWrite() {
    for (Tab tab : tabs) {
      MainView tabMainView = (MainView) tab.getGraphic();
      TextField tabConsole = tabMainView.getTextFields().getConsole().getEntry();
      tabConsole.setOnMouseClicked(event -> setListeners(tab));
    }
  }

  private void showMessage (AlertType type, String message) {
    new Alert(type, message).showAndWait();
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
//    currentStatus = INITIAL_STATUS; //TODO GET CURRENT STATUS FROM FRONT END
    currentTab = tab;
    myModel = getModel(tab);
    mainView = (MainView) currentTab.getGraphic();
    console = mainView.getTextFields().getConsole();
    userDefinitions = mainView.getTextFields().getUserDefinitions();
    runButton = mainView.getToolBar().getCommandButton();
    runButton.setOnAction(event -> sendCommand());

    uploadFile = mainView.getToolBar().getUploadFile();
    uploadFile.setOnAction(event -> chooseFile());
    language = mainView.getToolBar().getLanguageBox();

    language.setOnAction(event -> setLanguage(language));
    modeMenu = mainView.getToolBar().getModeMenu();
    modeMenu.setOnAction(event -> setMode(modeMenu));

    arrows = mainView.getTextFields().getMoveArrows();
    for (Button arrow : arrows.getButtons()) {
      arrow.setOnAction(event -> moveTurtle(arrow, arrows.getIncrement()));
    }
  }

  private void moveTurtle(Button arrow, int increment) {
    console.setText(arrow.getId() + " " + increment);
    sendCommand();
  }


  private void sendCommand() {
    try {
      myParser.parseLine(console.getText());
      List<Command> toSend = myParser.sendCommands();
      List<TurtleStatus> statuses = myModel.executeCommands(toSend);

      if (statuses.size() > 0) {
        setStatus(statuses.get(statuses.size() - 1));
        myDisplay.getMainView().moveTurtle(statuses);
      }
      console.addHistory();
      console.displayHistory();
      displayVariables();
      displayQueries();
    } catch (Exception e) {
      console.addError(errorHandler.getErrorMessage(e.getMessage(), myParser.getCommands()));
      console.getEntry().setOnKeyPressed(key -> handlePrompt(key.getCode()));
    }
  }

  private void handlePrompt(KeyCode key) {
    if (key == KeyCode.Y) {
      console.getEntry().setText(errorHandler.fixLine(myParser.getLastLine()));
      sendCommand();
    }
    if (key == KeyCode.N) {
      console.displayHistory();
      displayVariables();
      displayQueries();
    }
  }

  private void setStatus(TurtleStatus ts) {
    currentStatus = ts;
  }

  private void displayVariables() {
    myDisplay.getMainView().getTextFields().clearVariables();
    List<String> variables = myParser.getVariableString();
    List<String> functions = myParser.getFunctionString();
    if (variables.size() + functions.size() <= 0) {
      myDisplay.getMainView().getTextFields().addVariableText("");
    } else {
      for (String variable : myParser.getVariableString()) {
        myDisplay.getMainView().getTextFields().addVariableText(variable);
      }
      for (String function : myParser.getFunctionString()) {
        myDisplay.getMainView().getTextFields().addVariableText(function);
      }
    }
    myDisplay.getMainView().getTextFields().setVariableListeners();
  }


  private void displayQueries() {
    myDisplay.getMainView().getTextFields().clearQueries();
    myDisplay.getMainView().getTextFields().addQueriesText();
  }

  private void setLanguage(ComboBox language) {
    String newLanguage = language.getValue().toString();
    console.translateHistory(translator, newLanguage);
    userDefinitions.translateDefinitions(translator, newLanguage);
    translator.setCurrentLanguage(newLanguage);
    myParser.setLanguage(translator);
  }

  private void setMode(ComboBox menu) {
    myParser.setMode(menu.getValue().toString());
  }


  private void chooseFile() {
    FileChooser fileChooser = new FileChooser();
    Stage fileStage = new Stage();
    File file = fileChooser.showOpenDialog(fileStage);
    try {
      readFile(file);
    } catch (Exception e) {
      console.setText("File could not be read");
    }
  }

  private void readFile(File file) {
    try {
      String contents = new String(Files.readAllBytes(Paths.get(file.getPath())));
      StringBuilder builder = new StringBuilder();
      for (String s : contents.split("\n")) {
        builder.append(s);
        builder.append(" ");
      }
      console.setText(builder.toString());
    } catch (Exception e) {
      console.setText("File could not be read");
    }
  }


  private static FileChooser makeChooser (String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Open Data File");
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(System.getProperty("user.dir")));
    result.getExtensionFilters()
        .setAll(new FileChooser.ExtensionFilter("Text Files", extensionAccepted));
    return result;
  }

}