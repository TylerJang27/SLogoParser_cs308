package slogo.view.InputFields;

import java.util.List;
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
    private double width;

    public InputFields(MainView mainview) {
        this.mw = mainview;
        this.width = mw.getWidth();
        console = new Console(300);
        userDefinitions = new UserDefinitions(300);
        statusView = new StatusView(300);
        moveArrows = new MoveArrows(100, 10);
        this.getChildren().addAll(console.getPane(), userDefinitions.getVBox(), moveArrows.getVBox(), statusView.getVBox());
    }

    public void addVariableText(String text){
        userDefinitions.addVariableText(text);
    }


    public void addQueriesText() {
        TurtleStatus ts = mw.getTurtleStatus();
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
    public List<TextField> getVariables() {
        return userDefinitions.getDefinitions();
    }

    public TextArea getQueries() { return statusView.getStatus(); }

    public void setVariableListeners(){
        for(TextField tf : userDefinitions.getDefinitions()){
            tf.setOnMouseClicked(event -> sendVariables(tf.getText()));
        }
    }

    private void sendVariables(String variable){
        if (variable != null && variable.length() > 0) {
            String[] splitString = variable.split(" ");
            if (splitString[0].charAt(0) == ':') {
                console.setText(console.getText() + " set " + splitString[0]);
            } else {
                console.setText(console.getText() + " " + variable);
            }
        }
    }

}
