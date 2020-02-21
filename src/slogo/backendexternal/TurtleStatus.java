package slogo.backendexternal;

public class TurtleStatus {
    private double x;
    private double y;
    private double bearing;
    private boolean smooth;
//    private String mode;

    public TurtleStatus(double xPos, double yPos, double bearing, boolean smooth){// ,String mode){
//        x = parse(xPos, 0);
//        y = parse(yPos, 0);
//        if(bearing>360) bearing-=360;
//        if(bearing<0) bearing+=360;
        x = xPos;
        y = yPos;
        this.bearing = bearing;
        this.smooth = smooth;
//        this.mode = mode;
    }
//    public TurtleStatus(double xPos, double yPos, double bearing, boolean smooth) {
//       this(xPos, yPos, bearing, smooth, "normal");
//    }

//    private double parse(double position, double size){
//        if(mode.equals("toroidal")){
//
//        }
//        else if(mode.equals("edge")){
//            if(position<0) return 0;
//            if(position>size) return size;
//            return position;
//        }
//        return position;
//    }

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

//    public String getMode(){return mode;}

}
