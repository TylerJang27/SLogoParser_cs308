package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;

public class If implements ControlCommand {
    public static final int NUM_ARGS = 2;
    private ControlCommand conditional;

    public If(Command condition, Collection<Command> trueCommands) {
        conditional = new IfElse(condition, trueCommands, new ArrayList<>());
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        return conditional.execute(ts);
    }

    @Override
    public double returnValue() {
        return conditional.returnValue();
    }
}
