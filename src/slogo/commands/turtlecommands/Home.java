package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;

import java.util.Collection;

/**
 *
 * @author Lucy Gu
 */
public class Home implements TurtleCommand {

    public static final int NUM_ARGS = 0;

    private static final double homeX = 0;
    private static final double homeY = 0;
    private TurtleCommand go;

    public Home(int xMax, int yMax, String mode){
        go = new SetPosition(new Constant(homeX), new Constant(homeY), xMax, yMax, mode);
    }


    @Override
<<<<<<< HEAD
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret =  go.execute(ts);
        double deltaHeading = -1*ret.get(ret.size()-1).getBearing();
        TurtleCommand.turnDeltaHeading(ret.get(ret.size()-1), ret, deltaHeading);
        return ret;
=======
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return go.execute(ts);
>>>>>>> origin/master
    }


    @Override
    public double returnValue() {
        return go.returnValue();
    }


}
