package slogo.view;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
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
  private Tab tab = new Tab("SLogo");
  //private Button addTabButton;
  private AnchorPane anchorPane;
  private VBox vBox;

  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth() - 100;
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() - 100;
  public static final double TAB_WIDTH = SCREEN_WIDTH - 100;
  public static final double TAB_HEIGHT = SCREEN_HEIGHT - 100;

  public Display() {
    anchorPane = new AnchorPane();
    vBox = new VBox();
    vBox.setMinSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    anchorPane.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    anchorPane.setMinSize(SCREEN_WIDTH, SCREEN_HEIGHT);

    BorderStroke borderStroke = new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5));
    Border border = new Border(borderStroke);
    anchorPane.setBorder(border);


    myMainView = new MainView(this);
    tab.setGraphic(myMainView);

    tabPane.getTabs().addAll(tab);

    tabPane.setTabMaxHeight(TAB_HEIGHT);
    tabPane.setTabMaxWidth(TAB_WIDTH);
    tabPane.setTabMinHeight(TAB_HEIGHT);
    tabPane.setTabMinWidth(TAB_WIDTH);



    anchorPane.setTopAnchor(tabPane, 40.0);
    anchorPane.setLeftAnchor(tabPane, 10.0);


    anchorPane.getChildren().addAll(tabPane);
    vBox.getChildren().addAll(tabPane);

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


  public void addTab() {
    MainView newMainView = new MainView(this);
    Tab newTab = new Tab("SLogo");
    newTab.setGraphic(newMainView);
    tabPane.getTabs().add(newTab);
  }

}
