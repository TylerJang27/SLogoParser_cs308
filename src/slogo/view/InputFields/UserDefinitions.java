package slogo.view.InputFields;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.backendexternal.parser.Translator;

public class UserDefinitions {
  private static final Label varLabel = new Label("List of Variables:");
  private VBox variableBox;
  private TextArea variables;

  public UserDefinitions(){
    variableBox = new VBox();
    variables = new TextArea();
    setDetails();
    variableBox.getChildren().addAll(varLabel, variables);
  }

  private void setDetails(){
    variables.setMinHeight(200);
    variables.setMinWidth(400);
    Background backing = new Background(new BackgroundFill(
        Color.BLACK, new CornerRadii(0), new Insets(0)));
    //statusBox.setBackground(backing);
  }

  public VBox getVBox(){
    return variableBox;
  }

  public void addVariableText(String text){
    variables.appendText(text + "\n");
  }

  public void setVBox(VBox box){ variableBox = box; }

  public void clear(){ variables.clear(); }

  public TextArea getDefinitions(){ return variables; }

  public void setArea(TextArea definitions){ variables = definitions; }

  public void translateDefinitions(Translator translator, String newLanguage) {
    variables.clear();
    for(String line : variables.getText().split("\n")){
      StringBuilder translatedLine = new StringBuilder();
      for(String command : line.split(" ")){
        translatedLine.append(translator.translateCommand(command, newLanguage));
      }
      addVariableText(translatedLine.toString());
    }
  }
}
