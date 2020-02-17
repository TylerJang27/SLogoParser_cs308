package BackEndExternal;

public class TurtleStatus {
    private double x;
    private double y;
    private double bearing;
    private boolean smooth;

    public TurtleStatus(double xPos, double yPos, double bearing, boolean smooth){
        x = xPos;
        y = yPos;
        this.bearing = bearing;
        this.smooth = smooth;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getBearing(){
        return bearing;
    }

    public boolean getSmooth(){
        return smooth;
    }

}
