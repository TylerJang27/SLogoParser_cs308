package slogo.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Display extends Application {
  private Scene myScene;
  private MainView myMainView;

<<<<<<< HEAD
  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
=======
  private static final int WIDTH = 1020;
  private static final int HEIGHT = 570;
>>>>>>> origin/master

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
<<<<<<< HEAD
  public Scene getScene(){
    return myScene;
  }

  public MainView getMainView(){
    return myMainView;
=======
  public static void main(String[] args) {launch();}
  /**
   * Runs the program
   * @param args runs the program
   */
  public void display(String[] args){
    launch();
>>>>>>> origin/master
  }
}
