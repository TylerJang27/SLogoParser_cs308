package slogo.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author Shruthi Kumar, Nevzat Sevim
 */
public class Display {
  private Scene myScene;
  private MainView myMainView;
  private TabPane tabPane = new TabPane();
  private Tab tab = new Tab();

  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();

  public Display() {
    myMainView = new MainView();
    tab.setGraphic(myMainView);
    tabPane.getTabs().add(tab);
    tabPane.setTabMaxHeight(SCREEN_HEIGHT);
    tabPane.setTabMaxWidth(SCREEN_WIDTH);
    tabPane.setTabMinHeight(600);
    tabPane.setTabMinWidth(1075);
    myScene = new Scene(tabPane, SCREEN_WIDTH, SCREEN_HEIGHT);
  }


  /**
   * Returns display scene
   */
  public Scene getScene(){
    return myScene;
  }

  public MainView getMainView(){
    return myMainView;
  }
}
