package slogo.view;

import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

public class TextFields extends HBox {

    private MainView myMainView;
    private ToolBar myToolBar;

    private TextArea commands;
    private TextArea variables;

    private VBox commandBox;
    private VBox variableBox;


    public TextFields(MainView mainview) {
        setMyMainView(mainview);
        this.myToolBar = mainview.getToolBar();

        Label comLabel = new Label("History of Commands:");
        Label varLabel = new Label("List of Variables:");

        this.commands = new TextArea();
        this.variables = new TextArea();

        this.commandBox = new VBox();
        this.variableBox = new VBox();

        this.commandBox.getChildren().addAll(comLabel, commands);
        this.variableBox.getChildren().addAll(varLabel, variables);

        this.getChildren().addAll(commandBox, variableBox);
    }

    public void addText(){
        commands.appendText("b\n");
    }

    public MainView getMyMainView() {
        return myMainView;
    }

    public ToolBar getMyToolBar() {
        return myToolBar;
    }

    public TextArea getCommands() {
        return commands;
    }

    public TextArea getVariables() {
        return variables;
    }

    public VBox getCommandBox() {
        return commandBox;
    }

    public VBox getVariableBox() {
        return variableBox;
    }

    public void setMyMainView(MainView mainView) {
        myMainView = mainView;
    }

    public void setMyToolBar(ToolBar toolBar) {
        myToolBar = toolBar;
    }

    public void setCommands(TextArea newCommands) {
        commands = newCommands;
    }

    public void setVariables(TextArea newVariables) {
        variables = newVariables;
    }

    public void setCommandBox(VBox vBox) {
        commandBox = vBox;
    }

    public void setVariableBox(VBox vBox) {
        variableBox = vBox;
    }

}
