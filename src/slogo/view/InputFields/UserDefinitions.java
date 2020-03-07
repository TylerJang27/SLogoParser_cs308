package slogo.view.InputFields;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.backendexternal.parser.Translator;

public class UserDefinitions {
  private static final Label varLabel = new Label("List of Variables:");
  private VBox variableBox;
  private List<TextField> variables;
  private double size;

  public UserDefinitions(double length){
    size = length;
    variableBox = new VBox();
    variables = new ArrayList<>();
    variableBox.getChildren().addAll(varLabel);
    addVariableText("");
  }

  public VBox getVBox(){
    return variableBox;
  }

  public void addVariableText(String text){
    TextField variable = new TextField(text);
    variable.setPrefWidth(size);
    variable.setEditable(false);
    variables.add(variable);
    variableBox.getChildren().add(variable);
  }

  public void setVBox(VBox box){ variableBox = box; }

  public void clear(){
    variableBox.getChildren().clear();
    variableBox.getChildren().addAll(varLabel);
    variables.clear();
  }

  public List<TextField> getDefinitions(){ return variables; }

  public void translateDefinitions(Translator translator, String newLanguage) {
    variables.clear();
    for(TextField variable : variables){
      for(String line : variable.getText().split("\n")){
        StringBuilder translatedLine = new StringBuilder();
        for(String command : line.split(" ")){
          translatedLine.append(translator.translateCommand(command, newLanguage));
          translatedLine.append(" ");
        }
        addVariableText(translatedLine.toString());
      }
    }
  }
}
