package slogo.view.InputFields;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.backendexternal.parser.Translator;

public class Console {
  private static final String DEFAULT_ERROR_MESSAGE = "The last command could not be recognized.";
  private static final Label comLabel = new Label("Console:");
  private VBox box;
  private TextField entry;
  private ScrollPane pane;
  private List<String> history;
  private double boxHeight = 200;

  private double boxWidth;

  /**
   * Console object which allows user to enter commands as well as stores and displays history
   * of previous commands
   * @param width the width of the console in the display
   */
  public Console(double width){
    history = new ArrayList<>();
    box = new VBox();
    comLabel.setStyle("-fx-text-fill: white");
    box.getChildren().add(comLabel);
    boxWidth = width;
    addEditable();
    setDetails();
    pane = new ScrollPane(box);
    pane.setMinHeight(boxHeight);
    pane.setMaxHeight(boxHeight);
  }

  /**
   * Returns the pane in which the console is located
   * @return ScrollPane
   */
  public ScrollPane getPane(){ return pane; }

  /**
   * Returns the current text in the user entry field
   * @return String
   */
  public String getText(){
    return entry.getText();
  }

  /**
   * Adds current user entry to list of previous entries
   */
  public void addHistory(){
    history.add(entry.getText());
  }

  /**
   * Adds uneditable text fields to the scroll pane to display past command history in console
   */
  public void displayHistory(){
    clear();
    addEditable();
    ListIterator<String> iter = history.listIterator(history.size());
    while(iter.hasPrevious()){
      String past = "> " + iter.previous();
      addUneditable(past);
    }
    pane.setContent(box);
  }

  /**
   * Clears all entries and textfields in the console outside of its label.
   */
  public void clear(){
    box.getChildren().clear();
    box.getChildren().add(comLabel);
  }

  /**
   * Changes current text to display an error message to the user in the console
   * @param message to be displayed
   */
  public void addError(String message){
    addHistory();
    history.add(DEFAULT_ERROR_MESSAGE);
    entry.setText(message);
    entry.setEditable(false);
  }

  /**
   * Sets current editable text in the console
   * @param text the String displayed to the user
   */
  public void setText(String text){
    entry.clear();
    entry.setText(text);
  }

  /**
   * Returns the editable text field atop the console for use in Controller in handling prompting the user.
   * @return TextField
   */
  public TextField getEntry(){
    return entry;
  }

  /**
   * Iterates over past entries and displays them in the selected language
   * @param translator Translator object to translate objects from one language to another specified language
   * @param newLanguage String which specifies the language strings will be translated to
   */
  public void translateHistory(Translator translator, String newLanguage) {
    clear();
    addEditable();
    ListIterator<String> iter = history.listIterator(history.size());
    while(iter.hasPrevious()){
      StringBuilder translatedLine = new StringBuilder();
      for(String command : iter.previous().split(" ")){
        translatedLine.append(translator.translateCommand(command, newLanguage));
        translatedLine.append(" ");
      }
      String past = "> " + translatedLine.toString();
      addUneditable(past);
    }
    pane.setContent(box);
  }

  private void setDetails(){
    box.setMaxHeight(boxHeight);
    pane = new ScrollPane();
    pane.setContent(box);
    pane.setPannable(true);
    Background backing = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0)));
    box.setBackground(backing);
  }

  private void addUneditable(String input){
    TextField current = new TextField(input);
    current.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));
    current.setPrefWidth(boxWidth);
    current.setStyle("-fx-text-fill: green");
    current.setOnMouseClicked(event -> onClick(current.getText()));
    box.getChildren().add(current);
  }

  private void addEditable(){
    entry = new TextField();
    entry.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0, 0, 0, 20))));
    entry.setPrefWidth(boxWidth);
    entry.setStyle("-fx-text-fill: green");
    box.getChildren().add(entry);
  }

  private void onClick(String input){
    entry.setText(input.substring(2));
  }
}
