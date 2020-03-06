package slogo.frontendexternal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.backendexternal.TurtleStatus;

public class TurtleViewManager {
  private PenView penView;
  private int currentTurtleId;
  private Map<Integer, TurtleView> turtleViewMap;
  private Map<Integer, TurtleStatus> turtleStatusMap;
  private double startX;
  private double startY;
  private List<Line> penViewLines;
  private List<ImageView> turtleImageViewList;
  private final String picFileName = "raphael";

  public TurtleViewManager(double x, double y) {
    startX = x;
    startY = y;
    turtleViewMap = new HashMap<Integer, TurtleView>();
    turtleStatusMap = new HashMap<Integer, TurtleStatus>();
    turtleViewMap.put(1, new TurtleView(startX, startY, "raphael.png"));
    //turtleStatusMap.put(1, new TurtleStatus());
    turtleImageViewList = new ArrayList<ImageView>();
    penViewLines = new ArrayList<Line>();
  }

  public void execute(List<TurtleStatus> ts) {
    SequentialTransition seq = new SequentialTransition();
    for(int i = 0; i < ts.size(); i++) {
      TurtleStatus end = ts.get(i);
      int currID = end.getID();
      turtleViewMap.putIfAbsent(currID, new TurtleView(startX, startY, picFileName));
      turtleStatusMap.putIfAbsent(currID, new TurtleStatus(currID));
      TurtleView tempTurtle = turtleViewMap.get(currID);
      TurtleStatus start = turtleStatusMap.get(currID);
      tempTurtle.executeState(seq, start, end);
      turtleStatusMap.put(currID, end);
      penViewLines.addAll(tempTurtle.getPenView().getMyLines());
    }
    seq.play();

  }


  /** Update with correct IDs */
  public void initializeTurtleViews(int numTurtles) {
    for(int i = 1; i <= numTurtles; i++) {
      turtleViewMap.put(i, new TurtleView(startX, startY, picFileName));
      TurtleView tempTurtle = turtleViewMap.get(i);
      tempTurtle.setUpMyImageView();
    }
  }



  public TurtleView getTurtle(int id) {
    return turtleViewMap.get(id);
  }

  public Collection<TurtleView> getTurtleViews() {
    return turtleViewMap.values();
  }



  public void addTurtle(int newID) {
    turtleViewMap.put(newID, new TurtleView(startX, startY, picFileName));
  }

  public void removeTurtle(int newID) {
    turtleViewMap.remove(newID);
  }

  public double getStartX() {
    return startX;
  }

  public double getStartY() {
    return startY;
  }

  public void setPenView(int ID, Color color) {
    turtleViewMap.get(ID).getPenView().setMyPenColor(color);
  }

  public void setAllPenViewColors(Color color) {
    for(Map.Entry<Integer, TurtleView> temp : turtleViewMap.entrySet()) {
      temp.getValue().getPenView().setMyPenColor(color);
    }
  }

  public List<Line> getMyLines() {
    return penViewLines;
  }

  public List<ImageView> getImageViews() {
    for(Map.Entry<Integer, TurtleView> temp : turtleViewMap.entrySet()) {
      if(!turtleImageViewList.contains(temp.getValue().getMyImageView())) {
        turtleImageViewList.add(temp.getValue().getMyImageView());
      }
    }
    return turtleImageViewList;
  }

  public void setImageViews(ImageView newImageView) {
    for(Map.Entry<Integer, TurtleView> temp : turtleViewMap.entrySet()) {
      temp.getValue().setImageView(newImageView);
    }
  }

}
