package slogo.view.InputFields;

import java.net.UnknownServiceException;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class UserDefinitions {
  private static final Label varLabel = new Label("List of Variables:");
  private VBox variableBox;
  private TextArea variables;

  public UserDefinitions(){
    variableBox = new VBox();
    variables = new TextArea();
    variableBox.getChildren().addAll(variables, varLabel);
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
}
