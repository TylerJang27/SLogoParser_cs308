package slogo.view;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * This class sets up the Display that holds each MainView. Users can add tabs (default tabs or based on preferences).
 * Purpose: This holds the MainView display and allows user to add tabs to their workspace
 * Assumptions: The class will work assuming all dependencies on MainView are working.
 * Dependencies: This class relies on the Controller to instantiate it and MainView to load the turtle display area
 * @author Shruthi Kumar, Nevzat Sevim
 **/
public class Display {

  public static final int HEIGHT = 760;
  public static final int WIDTH = 1050;
  private Scene myScene;
  private MainView myMainView;

  private TabPane tabPane = new TabPane();
  private Tab tab = new Tab("");
  private VBox vBox;
  private Slider mySlider;
  private HBox hBox;

  private Button addTabButton, addTabFromPreferences;
  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth() - 100;
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() - 100;
  private static final int SLIDER_MIN_NUM = 0;
  private static final int SLIDER_MAX_NUM = 50;
  private static final int SLIDER_UNIT = 5;

  private int tabNo;

  /**
   * Constructor for the Display
   */
  public Display() {
    myMainView = new MainView();

    setUpButtons();
    setUpVBox();
    setUpTabs();
    setUpHBox();

    vBox.getChildren().addAll(hBox, tabPane);
    myScene = new Scene(vBox);
  }

  /**
   * Returns display scene
   * @return myScene
   */
  public Scene getScene(){
    return myScene;
  }

  /**
   * Gets the tab pane
   * @return tabPane : tabPane
   */
  public TabPane getTabPane(){ return tabPane; }

  /**
   * Gets the MainView from a given tab
   * @return given tab's mainview
   */
  public MainView getMainView(){
    Tab tab = tabPane.getSelectionModel().getSelectedItem();
    return (MainView) tab.getGraphic();
  }

  /**
   * Gets the add tab button
   * @return addTabButton : add tab button
   */
  public Button getAddTabButton() {
    return addTabButton;
  }

  /**
   * Gets the add tab from preferences button
   * @return addTabFromPreferences : addTabFromPreferences button
   */
  public Button getAddTabFromPreferencesButton() {
    return addTabFromPreferences;
  }

  /**
   * Adds a tab to the tabPane
   * @param newMainView : the new main view tab to be added
   */
  public void addTab(MainView newMainView) {
    tabNo++;
    if(newMainView == null) {
      newMainView = new MainView();
    }
    Tab newTab = new Tab("SLogo " + tabNo);
    newTab.setGraphic(newMainView);
    tabPane.getTabs().add(newTab);
  }

  /**
   * Makes the slider to change rate of animation
   */
  private void makeSlider() {
    this.mySlider = new Slider();
    mySlider.setMin(SLIDER_MIN_NUM);
    mySlider.setMax(SLIDER_MAX_NUM);
    mySlider.setValue(1);
    mySlider.setShowTickLabels(true);
    mySlider.setShowTickMarks(true);
    mySlider.setMajorTickUnit(SLIDER_UNIT);
    mySlider.setBlockIncrement(SLIDER_UNIT);

    mySlider.valueProperty().addListener(
        (ov, old_val, new_val) -> getMainView().getTurtles().setAnimationRate(
            new_val.intValue()));
  }

  private void setUpHBox() {
    hBox = new HBox();
    hBox.setSpacing(10.0);
    hBox.setAlignment(Pos.CENTER);
    makeSlider();
    hBox.getChildren().addAll(addTabButton, addTabFromPreferences, mySlider);
  }

  private void setUpTabs() {
    tab.setGraphic(myMainView);
    tab.setClosable(false);
    tabPane.getTabs().addAll(tab);

    tabPane.setTabMaxHeight(HEIGHT);
    tabPane.setTabMaxWidth(WIDTH);
    tabPane.setTabMinHeight(HEIGHT);
    tabPane.setTabMinWidth(WIDTH);
  }

  private void setUpVBox() {
    vBox = new VBox();
    vBox.setAlignment(Pos.TOP_CENTER);
    vBox.setMinSize(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  private void setUpButtons() {
    ResourceBundle buttonBundle = ResourceBundle.getBundle("slogo.view.resources.buttons");
    addTabButton = new Button(buttonBundle.getString("AddTab"));
    addTabFromPreferences = new Button(buttonBundle.getString("AddTabPreferences"));
  }

}
