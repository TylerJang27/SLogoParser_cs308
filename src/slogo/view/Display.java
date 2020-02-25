package slogo.view;

import javafx.application.Application;
import javafx.scene.Scene;
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

  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();

  public Display() {
    myMainView = new MainView();
    myScene = new Scene(myMainView, SCREEN_WIDTH, SCREEN_HEIGHT);
  }


  /**
   * Returns display scene
   */
  public Scene getScene(){
    return myScene;
  }

//  public String getCommandText() {
//    return myMainView.getToolBar().getTextField().getText();
//  }

  public MainView getMainView(){
    return myMainView;
  }
}
