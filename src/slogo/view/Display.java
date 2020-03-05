package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
  private MainView myMainView2;
  private TabPane tabPane = new TabPane();
  private Tab tab = new Tab("SLogo");
  private Tab tab2 = new Tab("SLogo");
  private List<MainView> myMainViewList;
  private Button addTabButton; // = new Button("Add Tab");
  private AnchorPane anchorPane;
  private VBox vBox;// = new VBox;

  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth() - 100;
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() - 100;
  public static final double TAB_WIDTH = SCREEN_WIDTH - 100;
  public static final double TAB_HEIGHT = SCREEN_HEIGHT - 100;

  private ResourceBundle buttonBundle;

  public Display() {
    buttonBundle = ResourceBundle.getBundle("slogo.view.resources.buttons");

    myMainViewList = new ArrayList<>();
    addTabButton = new Button(buttonBundle.getString("AddTab"));
    anchorPane = new AnchorPane();
    vBox = new VBox();
    vBox.setMinSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    anchorPane.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    anchorPane.setMinSize(SCREEN_WIDTH, SCREEN_HEIGHT);

    BorderStroke borderStroke = new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5));
    Border border = new Border(borderStroke);
    anchorPane.setBorder(border);


    myMainView = new MainView();
    myMainView2 = new MainView();
    tab.setGraphic(myMainView);
//    tab.set
    //tab2.setGraphic(myMainView2);
    tabPane.getTabs().addAll(tab);
    //tabPane.setLayoutX(10.0);
    tabPane.setTabMaxHeight(TAB_HEIGHT);
    tabPane.setTabMaxWidth(TAB_WIDTH);
    tabPane.setTabMinHeight(TAB_HEIGHT);
    tabPane.setTabMinWidth(TAB_WIDTH);

    anchorPane.setTopAnchor(addTabButton, 10.0);
    anchorPane.setLeftAnchor(addTabButton, 10.0);

    anchorPane.setTopAnchor(tabPane, 40.0);
    anchorPane.setLeftAnchor(tabPane, 10.0);


    anchorPane.getChildren().addAll(addTabButton, tabPane);
    vBox.getChildren().addAll(addTabButton, tabPane);

    myScene = new Scene(vBox, SCREEN_WIDTH, SCREEN_HEIGHT);
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

  public Button getAddTabButton(){ return addTabButton; }

  public TabPane getTabPane() {
    return tabPane;
  }


  public void addTab() {
    MainView newMainView = new MainView();
    Tab newTab = new Tab("SLogo");
    newTab.setGraphic(newMainView);
    tabPane.getTabs().add(newTab);
  }

  public void removeTab() {

  }
}
