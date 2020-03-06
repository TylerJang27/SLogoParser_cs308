package slogo.commands.turtlecommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;
import slogo.commands.controlcommands.Constant;

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

    public Home(double xMax, double yMax, String mode){
        go = new SetPosition(new Constant(homeX), new Constant(homeY), xMax, yMax, mode);
    }

    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret =  go.execute(manifest);
        double deltaHeading = -1*manifest.getActiveState().getBearing();
        TurtleCommand.turnDeltaHeading(manifest, ret, deltaHeading);
        return ret;
    }

    @Override
    public double returnValue() {
        return go.returnValue();
    }


}
