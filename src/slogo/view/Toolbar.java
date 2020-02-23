package slogo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ResourceBundle;

public class Toolbar extends ToolBar {

  private MainView myMainView;
  private TextFields myTextFields;

  private Button runCommand;


  private static final int FRAMES_PER_SECOND = 60;
  private static final double MILLISECOND_DELAY = 10000/FRAMES_PER_SECOND;
  private Timeline animation;


  /**
   *
   * @author Shruthi Kumar
   */
  public Toolbar(MainView mainview) {
    this.myMainView = mainview;
    this.myTextFields = myMainView.getTextFields();

    TextField textField = new TextField("Enter Command: ");
    textField.setOnAction(this:: handleCommand);

    this.runCommand = new Button("Run");
    runCommand.setOnAction(this:: handlePlay);

    animationFunctions();
    this.getItems().addAll(textField, runCommand);


  }

  private void handleCommand(ActionEvent actionEvent) {

  }


  /**
   * Method that sets up the animation, in which the myMainview step method is called every second which updates the
   * grid on the screen.
   */
  public void animationFunctions() {

    KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
      try {
        myMainView.step();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
  }

  /**
   * Starts the animation and timer
   * @param actionEvent The play button pressed
   */
  private void handlePlay(ActionEvent actionEvent) {
    animation.play();
    myTextFields.addText();
    //timer.start();
  }

  public void setTextField(TextFields tf){this.myTextFields = tf;}



}
