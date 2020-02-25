package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;
import slogo.commands.controlcommands.Constant;
import java.util.Collection;
import java.util.List;

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
    public List<TurtleStatus> execute(TurtleStatus ts) {
        return go.execute(ts);
    }

    @Override
    public double returnValue() {
        return go.returnValue();
    }


}
