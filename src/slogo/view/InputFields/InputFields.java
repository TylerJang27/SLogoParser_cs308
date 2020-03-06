package slogo.view.InputFields;

import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import slogo.backendexternal.TurtleStatus;
import slogo.view.MainView;

public class InputFields extends HBox {

    private Console console;
    private UserDefinitions userDefinitions;
    private StatusView statusView;
    private MoveArrows moveArrows;
    private MainView mw;

    public InputFields(MainView mainview) {
        this.mw = mainview;

        userDefinitions = new UserDefinitions();
        console = new Console();
        statusView = new StatusView();
        moveArrows = new MoveArrows(100, 10);
        this.getChildren().addAll(console.getVBox(), userDefinitions.getVBox(), statusView.getVBox(), moveArrows.getVBox());
    }

    public void addVariableText(String text){
        userDefinitions.addVariableText(text);
    }

    public void addQueriesText() {
        TurtleStatus ts = mw.getTurtleStatus();
        //TODO: TYLER FIX: mw.getTurtle()); is null
        System.out.println(ts);
        statusView.addStatusText(ts.getID(), ts.getX(),-ts.getY(),ts.getBearing(),mw.getTurtle().getPenView().getMyPenColor(),ts.getPenDown());
    }

    public String getCommands() {
        return console.getText();
    }

    public void clearVariables() { userDefinitions.clear(); }
    public void clearQueries() { statusView.clear(); }

    public Console getConsole(){return console;}
    public UserDefinitions getUserDefinitions(){return userDefinitions; }
    public MoveArrows getMoveArrows(){ return moveArrows;}
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

//    public void setMyMainView(MainView mainView) {
//        myMainView = mainView;
//    }
//
//    public void setMyToolBar(ToolBar toolBar) {
//        myToolBar = toolBar;
//    }

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
