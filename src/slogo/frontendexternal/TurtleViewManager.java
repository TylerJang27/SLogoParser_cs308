package slogo.frontendexternal;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import slogo.backendexternal.TurtleStatus;

public class TurtleViewManager {
  private PenView penView;
  private int currentTurtleId;
  private Map<Integer, TurtleView> turtleViewMap;
  private Map<Integer, TurtleStatus> turtleStatusMap;
  private double startX;
  private double startY;

  public TurtleViewManager(int x, int y) {
    startX = x;
    startY = y;
    turtleViewMap.put(1, new TurtleView(startX, startY));
    turtleStatusMap.put(1, null);
  }

  public void execute(List<TurtleStatus> ts) {
    for(int i = 0; i < ts.size() - 1; i++) {
      TurtleStatus end = ts.get(i);
      int currID = 1; //end.getID(); /** TO DO: Update with correct method from TS */
      TurtleView tempTurtle = turtleViewMap.get(currID); /** TO DO: Update with correct method from TS */
      TurtleStatus start = turtleStatusMap.get(currID);
      //tempTurtle.executeState(start, end);
      turtleStatusMap.put(currID, end);
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

}
