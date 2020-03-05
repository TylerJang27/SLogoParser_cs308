package slogo.frontendexternal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

  public TurtleViewManager(double x, double y) {
    startX = x;
    startY = y;
    turtleViewMap.put(1, new TurtleView(startX, startY));
    turtleStatusMap.put(1, null);
    penViewLines = new ArrayList<Line>();
  }

  public void execute(List<TurtleStatus> ts) {
    for(int i = 0; i < ts.size(); i++) {
      TurtleStatus end = ts.get(i);
      int currID = 1; //end.getID(); /** TO DO: Update with correct method from TS */
      turtleViewMap.putIfAbsent(currID, new TurtleView(startX, startY));
      TurtleView tempTurtle = turtleViewMap.get(currID); /** TO DO: Update with correct method from TS */
      TurtleStatus start = turtleStatusMap.get(currID);
      tempTurtle.executeState(start, end);
      turtleStatusMap.put(currID, end);
      penViewLines.addAll(tempTurtle.getPenView().getMyLines());
    }

  }


  /** Update with correct IDs */
  public void initializeTurtleViews(int numTurtles) {
    for(int i = 2; i < numTurtles; i++) {
      turtleViewMap.put(i, new TurtleView(startX, startY));
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
    turtleViewMap.put(newID, new TurtleView(startX, startY));
  }

  public void removeTurtle(int newID) {
    turtleViewMap.remove(newID);
  }

  public void setPenView(int ID, Color color) {
    turtleViewMap.get(ID).getPenView().setMyPenColor(color);
  }

  public List<Line> getMyLines() {
    return penViewLines;
  }

}
