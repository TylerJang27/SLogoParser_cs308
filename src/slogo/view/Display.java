package slogo.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Shruthi Kumar, Nevzat Sevim
 */
public class Display extends Application{
  private Scene myScene;
  private MainView myMainView;

  private static final int WIDTH = 1020;
  private static final int HEIGHT = 1020;

  @Override
  public void start(Stage primaryStage) throws Exception {
    myMainView = new MainView();
    myScene = new Scene(myMainView, WIDTH, HEIGHT);
    primaryStage.setScene(myScene);
    primaryStage.setTitle("SLogo");
    primaryStage.show();
  }

  /**
   * Runs the program
   * @param args runs it
   */
  public static void main(String[] args) {launch();}
  /**
   * Runs the program
   * @param args runs the program
   */
  public void display(String[] args){
    launch();
  }

  public Scene getScene(){
    return myScene;
  }

  public TextFields getInputField() {
    return myMainView.getTextFields();
  }

}
