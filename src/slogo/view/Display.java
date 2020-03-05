package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
<<<<<<< HEAD
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

=======
  private Tab tab = new Tab("Main Tab");
  SingleSelectionModel<Tab> selectionModel;
  private int tabNo;


  public Display() {
    tabNo = 1;
    selectionModel = tabPane.getSelectionModel();
>>>>>>> 1276ca207805f21cbc6b06866078f49459ba8b6f

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
