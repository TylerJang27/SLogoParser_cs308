package slogo.frontendexternal;

import java.util.List;
import java.util.Map;
import slogo.backendexternal.TurtleStatus;

public class Turtle {
  private PenView penView;
  private int currentTurtleId;
  private Map<Integer, TurtleView> turtleViewMap;
  private double startX;
  private double startY;

  public Turtle(int x, int y) {
    startX = x;
    startY = y;
    turtleViewMap.put(1, new TurtleView(startX, startY));
  }

  public void execute(List<TurtleStatus> ts) {

  }


  public TurtleView getTurtle(int id) {
    return turtleViewMap.get(id);
  }

  public void addTurtle(int newID) {
    turtleViewMap.put(newID, new TurtleView(startX, startY));
  }

}
