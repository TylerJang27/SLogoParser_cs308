package slogo.view.InputFields;

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
import slogo.frontendexternal.CommandReader;
import slogo.view.MainView;

public class InputFields extends HBox {

    private MainView myMainView;
    private ToolBar myToolBar;

    private CommandReader commandReader;
    private TextArea commands;
    private TextArea queries;

    private Console console;
    private VBox commandBox;
    private UserDefinitions userDefinitions;
    private VBox queriesBox;


    public InputFields(MainView mainview) {
        setMyMainView(mainview);
        userDefinitions = new UserDefinitions();
        console = new Console();

        this.myToolBar = mainview.getToolBar();

        Label queryLabel = new Label("Current Status:");
        this.queries = new TextArea();
        this.queriesBox = new VBox();
        this.queriesBox.getChildren().addAll(queryLabel, queries);

        this.getChildren().addAll(commandBox, userDefinitions.getVBox(), queriesBox);
    }

    public void addCommandText(){ console.addHistory(); }
    public void addVariableText(String text){
        userDefinitions.addVariableText(text);
    }
    public void addQueriesText(Double value) { queries.appendText("Value: " + value + "\n"); }

    public TextArea getCommands() {
        return commands;
    }

    public void clearCommands() { console.clear(); }
    public void clearVariables() { userDefinitions.clear(); }
    public void clearQueries() { queries.clear(); }

    public TextArea getVariables() {
        return userDefinitions.getDefinitions();
    }

    public TextArea getQueries() { return queries; }

    public VBox getCommandBox() {
        return commandBox;
    }

    public VBox getVariableBox() {
        return userDefinitions.getVBox();
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
        userDefinitions.setArea(newVariables);
    }

    public void setCommandBox(VBox vBox) {
        commandBox = vBox;
    }

    public void setVariableBox(VBox vBox) {
        userDefinitions.setVBox(vBox);
    }

}
