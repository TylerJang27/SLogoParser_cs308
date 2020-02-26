package slogo.backendexternal.parser;

public enum Counts {
  Forward (1),
  Home(0),
  Backward (1);
  private int count;
  Counts(int count){this.count = count;}
  public int getNum(){return this.count;}
}
