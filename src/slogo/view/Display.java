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
  private ResourceBundle buttonBundle;
  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth() - 100;
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() - 100;

  private int tabNo;

  public Display() {

    myMainViewList = new ArrayList<>();
    addTabButton = new Button("Add Tab");
    buttonBundle = ResourceBundle.getBundle("slogo.view.resources.buttons");
    myMainViewList = new ArrayList<>();
    addTabButton = new Button(buttonBundle.getString("AddTab"));

    vBox = new VBox();
    vBox.setMinSize(SCREEN_WIDTH, SCREEN_HEIGHT);


    BorderStroke borderStroke = new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5));
    Border border = new Border(borderStroke);

    myMainView = new MainView();
    tab.setGraphic(myMainView);
    tab.setClosable(false);
    tabPane.getTabs().addAll(tab);
//<<<<<<< HEAD
//    tabPane.setTabMaxHeight(TAB_HEIGHT);
//    tabPane.setTabMaxWidth(TAB_WIDTH);
//    tabPane.setTabMinHeight(TAB_HEIGHT);
//    tabPane.setTabMinWidth(TAB_WIDTH);
////
////    anchorPane.setTopAnchor(addTabButton, 10.0);
////    anchorPane.setLeftAnchor(addTabButton, 5.0);
////
////    anchorPane.setTopAnchor(tabPane, 40.0);
////    anchorPane.setLeftAnchor(tabPane, 5.0);

//    //anchorPane.getChildren().addAll(addTabButton, tabPane);
//    vBox.getChildren().addAll(addTabButton, tabPane);
//=======

//    anchorPane.setTopAnchor(tabPane, 40.0);
//    anchorPane.setLeftAnchor(tabPane, 5.0);


    //anchorPane.getChildren().addAll(addTabButton, tabPane);
    vBox.getChildren().addAll(addTabButton, tabPane);

    tabPane.setTabMaxHeight(760);
    tabPane.setTabMaxWidth(1040);
    tabPane.setTabMinHeight(760);
    tabPane.setTabMinWidth(1040);

    //selectionModel = tabPane.getSelectionModel();

    vBox.setAlignment(Pos.CENTER);
    //vBox.getChildren().addAll(addTabButton,tabPane);

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
    //selectionModel.select(newTab);
  }

}
