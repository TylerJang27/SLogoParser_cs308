package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 * @author Shruthi Kumar, Nevzat Sevim
 */
public class Display {
  private Scene myScene;
  private MainView myMainView;

  private TabPane tabPane = new TabPane();
  private Tab tab = new Tab("SLogo 0");
  private VBox vBox = new VBox();

  private List<MainView> myMainViewList;
  private Button addTabButton;
  private SingleSelectionModel<Tab> selectionModel;

  private int tabNo;

  public Display() {

    myMainViewList = new ArrayList<>();
    addTabButton = new Button("Add Tab");

    myMainView = new MainView();
    tab.setGraphic(myMainView);
    tab.setClosable(false);
    tabPane.getTabs().addAll(tab);
    tabPane.setTabMaxHeight(760);
    tabPane.setTabMaxWidth(1040);
    tabPane.setTabMinHeight(760);
    tabPane.setTabMinWidth(1040);

    selectionModel = tabPane.getSelectionModel();

    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(addTabButton,tabPane);

    myScene = new Scene(vBox);
  }


  /**
   * Returns display scene
   */
  public Scene getScene(){
    return myScene;
  }

  public TabPane getTabPane(){ return tabPane; }

  public MainView getMainView(){
    Tab tab = tabPane.getSelectionModel().getSelectedItem();
    return (MainView) tab.getGraphic();
  }

  public Button getAddTabButton() {
    return addTabButton;
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
