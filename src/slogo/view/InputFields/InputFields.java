package slogo.view.InputFields;

import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import slogo.view.MainView;

public class InputFields extends HBox {

    private MainView myMainView;
    private ToolBar myToolBar;

    private Console console;
    private UserDefinitions userDefinitions;
    private StatusView statusView;

    public InputFields(MainView mainview) {
        setMyMainView(mainview);
        this.myToolBar = mainview.getToolBar();
        userDefinitions = new UserDefinitions();
        console = new Console();
        statusView = new StatusView();
        this.getChildren().addAll(console.getVBox(), userDefinitions.getVBox(), statusView.getVBox());
    }

    public void addVariableText(String text){
        userDefinitions.addVariableText(text);
    }
    public void addQueriesText(Double value) { statusView.addStatusText(value); }

    public String getCommands() {
        return console.getText();
    }

    public void clearVariables() { userDefinitions.clear(); }
    public void clearQueries() { statusView.clear(); }

    public Console getConsole(){return console;}
    public UserDefinitions getUserDefinitions(){return userDefinitions; }
    public TextArea getVariables() {
        return userDefinitions.getDefinitions();
    }

    public TextArea getQueries() { return statusView.getStatus(); }

//    public VBox getCommandBox() {
//        return commandBox;
//    }

    public VBox getVariableBox() {
        return userDefinitions.getVBox();
    }

    public void setMyMainView(MainView mainView) {
        myMainView = mainView;
    }

    public void setMyToolBar(ToolBar toolBar) {
        myToolBar = toolBar;
    }

    public void setVariables(TextArea newVariables) {
        userDefinitions.setArea(newVariables);
    }

//    public void setCommandBox(VBox vBox) {
//        commandBox = vBox;
//    }

    public void setVariableBox(VBox vBox) {
        userDefinitions.setVBox(vBox);
    }

}
