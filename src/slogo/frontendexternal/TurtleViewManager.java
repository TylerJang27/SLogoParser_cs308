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
  private int currentTurtleId;
  private Map<Integer, TurtleView> turtleViewMap;
  private Map<Integer, TurtleStatus> turtleStatusMap;
  private double startX;
  private double startY;
  private List<Line> penViewLines;
  private List<ImageView> turtleImageViewList;
  private final String turtlePicFileName;
  private Color penColor;
  private int animationRate;

  public TurtleViewManager(double x, double y, String picFileName, Color color) {
    startX = x;
    startY = y;
    turtlePicFileName = picFileName;
    penColor = color;
    turtleViewMap = new HashMap<Integer, TurtleView>();
    turtleStatusMap = new HashMap<Integer, TurtleStatus>();
    turtleViewMap.put(1, new TurtleView(startX, startY, picFileName, penColor));
    setAnimationRate(1);
    turtleImageViewList = new ArrayList<ImageView>();
    penViewLines = new ArrayList<Line>();
  }

  public void execute(List<TurtleStatus> ts) {
    SequentialTransition seq = new SequentialTransition();
    for(int i = 0; i < ts.size(); i++) {
      TurtleStatus end = ts.get(i);
      int currID = end.getID();
      turtleViewMap.putIfAbsent(currID, new TurtleView(startX, startY, turtlePicFileName, penColor));
      turtleStatusMap.putIfAbsent(currID, new TurtleStatus(currID));
      TurtleView tempTurtle = turtleViewMap.get(currID);
      TurtleStatus start = turtleStatusMap.get(currID);
//<<<<<<< HEAD
//
//      if(tempTurtle.getIsActive()) {
//        tempTurtle.executeState(seq, start, end);
//        turtleStatusMap.put(currID, end);
//        penViewLines.addAll(tempTurtle.getPenView().getMyLines());
//      }
//=======
      tempTurtle.executeState(seq, start, end);
      if(end.hasRunnable()){
        end.modify();
      }
      turtleStatusMap.put(currID, end);
      penViewLines.addAll(tempTurtle.getPenView().getMyLines());
      //if(end.hasRunnable()) end.modify();
    }
    seq.setRate(animationRate);
    seq.play();


  }


  /** Update with correct IDs */
  public void initializeTurtleViews(int numTurtles) {
    for(int i = 1; i <= numTurtles; i++) {
      turtleViewMap.put(i, new TurtleView(startX, startY, turtlePicFileName, penColor));
      turtleStatusMap.putIfAbsent(i, new TurtleStatus(i));
    }
  }

  public TurtleView getTurtle(int id) {
    return turtleViewMap.get(id);
  }

  public Collection<TurtleView> getTurtleViews() {
    return turtleViewMap.values();
  }

  public void setAnimationRate(int rate) {
    animationRate = rate;
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
    turtleImageViewList.clear();
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

  public SequentialTransition correctPositions() {
    SequentialTransition sq = new SequentialTransition();
    for (Integer k: turtleStatusMap.keySet()) {
      TurtleStatus tsFinal = turtleStatusMap.get(k);
      TurtleStatus tsAdjusted = new TurtleStatus(tsFinal.getID(), tsFinal.getX(), tsFinal.getY()-0.0001, 0, tsFinal.getTrail(), tsFinal.getVisible(), tsFinal.getPenDown());
      TurtleView tv = turtleViewMap.get(k);
      tv.executeState(sq, tsAdjusted, tsFinal);
      //TODO: REFACTOR AND CALL EXECUTE
    }
    return sq;
  }


  public TurtleStatus getTurtleStatus(int id) {
    return turtleStatusMap.get(id);
  }

}
