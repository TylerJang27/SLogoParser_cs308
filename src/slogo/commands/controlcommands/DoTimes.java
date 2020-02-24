package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.Collection;

/**
 *
 * @author Tyler Jang
 */
public class DoTimes implements ControlCommand {

    public static final int NUM_ARGS = 3;
    private static final int SINGLE_STEP = 1;

    private ControlCommand loop;

    //TODO Dennis: PLEASE CHECK IN THE PARSER TO ENSURE THAT arg1 CONTAINS A VARIABLE INSTANCE
    //refer to spiral2 example
    //please pass this in as a Variable instance
    public DoTimes(Variable var, Command varCap, Collection<Command> commands) {
        loop = new For(var, new Constant(), varCap, new Constant(SINGLE_STEP), commands);
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return loop.execute(ts);
    }

    @Override
    public double returnValue() {
        return loop.returnValue();
    }
}
