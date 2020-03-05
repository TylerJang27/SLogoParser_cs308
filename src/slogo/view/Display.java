package slogo.view;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author Shruthi Kumar, Nevzat Sevim
 */
public class Display {
  private Scene myScene;
  private MainView myMainView;
  private TabPane tabPane = new TabPane();
  private Tab tab = new Tab("Main Tab");
  SingleSelectionModel<Tab> selectionModel;
  private int tabNo;


  public Display() {
    tabNo = 1;
    selectionModel = tabPane.getSelectionModel();

    myMainView = new MainView();
    tab.setGraphic(myMainView);
    tab.setClosable(false);

    tabPane.getTabs().addAll(tab);

    tabPane.setTabMaxHeight(750);
    tabPane.setTabMaxWidth(1040);
    tabPane.setTabMinHeight(750);
    tabPane.setTabMinWidth(1040);

    myScene = new Scene(tabPane);
  }


  /**
   * Returns display scene
   */
  public Scene getScene(){
    return myScene;
  }

  public MainView getMainView(){
    Tab tab = tabPane.getSelectionModel().getSelectedItem();
    return (MainView) tab.getGraphic();
  }

  public void addTab() {
    tabNo++;
    MainView newMainView = new MainView();
    Tab newTab = new Tab("SLogo " + tabNo);
    newTab.setGraphic(newMainView);
    tabPane.getTabs().add(newTab);
    selectionModel.select(newTab);
  }

}
