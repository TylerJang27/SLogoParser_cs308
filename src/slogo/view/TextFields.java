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
import slogo.frontendexternal.CommandReader;

public class TextFields extends HBox {

    private MainView myMainView;
    private ToolBar myToolBar;

    private CommandReader commandReader;
    private TextArea commands;
    private TextArea variables;
    private TextArea queries;

    private VBox commandBox;
    private VBox variableBox;
    private VBox queriesBox;


    public TextFields(MainView mainview) {
        setMyMainView(mainview);
        this.myToolBar = mainview.getToolBar();

        Label comLabel = new Label("History of Commands:");
        Label varLabel = new Label("List of Variables:");
        Label queryLabel = new Label("Queries:");

        this.commands = new TextArea();
        this.variables = new TextArea();
        this.queries = new TextArea();

        this.commandBox = new VBox();
        this.variableBox = new VBox();
        this.queriesBox = new VBox();

        this.commandBox.getChildren().addAll(comLabel, commands);
        this.variableBox.getChildren().addAll(varLabel, variables);
        this.queriesBox.getChildren().addAll(queryLabel, queries);

        this.getChildren().addAll(commandBox, variableBox, queriesBox);
    }

    public void addCommandText(String text){
        commands.appendText(text + "\n");
    }
    public void addVariableText(String text){
        variables.appendText(text + "\n");
    }
    public void addQueriesText(Double value) { queries.appendText("Value: " + value + "\n"); }


    public MainView getMyMainView() {
        return myMainView;
    }

    public ToolBar getMyToolBar() {
        return myToolBar;
    }

    public TextArea getCommands() {
        return commands;
    }

    public void clearCommands() { commands.clear(); }

    public void clearVariables() { variables.clear(); }

    public void clearQueries() { queries.clear(); }


    public TextArea getVariables() {
        return variables;
    }

    public TextArea getQueries() { return queries; }

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
