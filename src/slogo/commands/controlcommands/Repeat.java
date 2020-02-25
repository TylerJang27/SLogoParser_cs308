package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.Collection;

//TODO Dennis: PLEASE REPLACE ANY INSTANCE OF ":repcount" in the parsing process with:
//getRepCount()
public class Repeat implements ControlCommand {

    public static final int NUM_ARGS = 2;

    //should not be static because that would break the nested repeat case
    private Variable repCount = new Variable();
    private ControlCommand loop;

    public Repeat(Command varCap, Collection<Command> commands) {
        loop = new DoTimes(getRepCount(), varCap, commands);
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return loop.execute(ts);
    }

    @Override
    public double returnValue() {
        return loop.returnValue();
    }

    public Variable getRepCount() {
        return repCount;
    }
}
