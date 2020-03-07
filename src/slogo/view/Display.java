package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
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
  private Tab tab = new Tab("");
  private VBox vBox;
  private Slider mySlider;


  private List<MainView> myMainViewList;
  private Button addTabButton, addTabFromPreferences;
  private ResourceBundle buttonBundle;
  public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth() - 100;
  public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight() - 100;
  private static final int SLIDERMINNUM = 0;
  private static final int SLIDERMAXNUM = 50;
  private static final int SLIDERUNIT = 5;

  private int tabNo;

  public Display() {

    myMainViewList = new ArrayList<>();
    buttonBundle = ResourceBundle.getBundle("slogo.view.resources.buttons");
    myMainViewList = new ArrayList<>();
    addTabButton = new Button(buttonBundle.getString("AddTab"));
    addTabFromPreferences = new Button(buttonBundle.getString("AddTabPreferences"));

    vBox = new VBox();
    vBox.setMinSize(SCREEN_WIDTH, SCREEN_HEIGHT);

    myMainView = new MainView();
    tab.setGraphic(myMainView);
    tab.setClosable(false);
    tabPane.getTabs().addAll(tab);

    HBox hBox = new HBox();
    hBox.setSpacing(10.0);
    hBox.setAlignment(Pos.CENTER);
    makeSlider();
    hBox.getChildren().addAll(addTabButton, addTabFromPreferences, mySlider);

    vBox.getChildren().addAll(hBox, tabPane);

    tabPane.setTabMaxHeight(760);
    tabPane.setTabMaxWidth(1050);
    tabPane.setTabMinHeight(760);
    tabPane.setTabMinWidth(1050);
    //tabPane.setOnScroll(event -> {});
    //tabPane.setOnScroll(event -> {});
    tabPane.addEventFilter(SwipeEvent.ANY, new EventHandler<SwipeEvent>() {

      @Override
      public void handle(SwipeEvent event) {
        event.consume();
      }
    });

    vBox.setAlignment(Pos.TOP_CENTER);

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

  public Button getAddTabFromPreferencesButton() {
    return addTabFromPreferences;
  }

  public void addTab(MainView newMainView) {
    tabNo++;
    if(newMainView == null) {
      newMainView = new MainView();
    }
    Tab newTab = new Tab("SLogo " + tabNo);
    newTab.setGraphic(newMainView);
    tabPane.getTabs().add(newTab);
    //selectionModel.select(newTab);
  }

  /**
   * Makes the slider which dictates the speed that the simulation and grid is updating. Also changes the elapsed speed
   * timer to accurately display the elapsed time based on the relative speed.
   */
  private void makeSlider() {
    this.mySlider = new Slider();
    mySlider.setMin(SLIDERMINNUM);
    mySlider.setMax(SLIDERMAXNUM);
    mySlider.setValue(1);
    mySlider.setShowTickLabels(true);
    mySlider.setShowTickMarks(true);
    mySlider.setMajorTickUnit(SLIDERUNIT);
    mySlider.setBlockIncrement(SLIDERUNIT);

    mySlider.valueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov,
          Number old_val, Number new_val) {
        getMainView().getTurtles().setAnimationRate(((Double) new_val).intValue());
      }
    });
  }

}
