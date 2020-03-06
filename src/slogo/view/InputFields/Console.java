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
  private double boxHeight;
  private double boxWidth;

  public Console(){ this(200, 410); }

  public Console(double height, double width){
    history = new ArrayList<>();
    box = new VBox();
    comLabel.setStyle("-fx-text-fill: white");
    box.getChildren().add(comLabel);
    boxHeight = height;
    boxWidth = width;
    setDetails();
    addEditable();
    pane = new ScrollPane(box);
  }

  public String getText(){
    return entry.getText();
  }

  public void addHistory(){
    history.add(entry.getText());
  }

  public void displayHistory(){
    clear();
    addEditable();
    ListIterator<String> iter = history.listIterator(history.size());
    while(iter.hasPrevious()){
      String past = "> " + iter.previous();
      addUneditable(past);
    }
  }

  public VBox getVBox(){
    return box;
  }

  public void clear(){
    box.getChildren().clear();
    box.getChildren().add(comLabel);
  }

  public void addError(String message){
    addHistory();
    history.add(DEFAULT_ERROR_MESSAGE);
    entry.setText(message);
    entry.setEditable(false);
  }

  public TextField getEntry(){
    return entry;
  }

  private void setDetails(){
    box.setMinHeight(boxHeight);
    box.setMinWidth(boxWidth);
    box.setMaxHeight(boxHeight);
    box.setMaxWidth(boxWidth);
    System.out.println("WIDTH: " + box.getWidth());
    System.out.println("height: " + box.getHeight());
    Background backing = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0)));
    box.setBackground(backing);
  }

  private void addUneditable(String input){
    TextField current = new TextField(input);
    current.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));
    current.setPrefWidth(box.getWidth());
    current.setStyle("-fx-text-fill: green");
    current.setOnMouseClicked(event -> onClick(current.getText()));
    box.getChildren().add(current);
  }

  private void addEditable(){
    entry = new TextField();
    entry.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0, 0, 0, 20))));
    entry.setPrefWidth(box.getWidth());
    entry.setStyle("-fx-text-fill: green");
    box.getChildren().add(entry);
  }

  private void onClick(String input){
    entry.setText(input.substring(2));
  }

  public void translateHistory(Translator translator, String newLanguage) {
    clear();
    addEditable();
    ListIterator<String> iter = history.listIterator(history.size());
    while(iter.hasPrevious()){
      StringBuilder translatedLine = new StringBuilder();
      for(String command : iter.previous().split(" ")){
        System.out.println(command);
        translatedLine.append(translator.translateCommand(command, newLanguage));
        translatedLine.append(" ");
      }
      String past = "> " + translatedLine.toString();
      addUneditable(past);
    }
  }
}
