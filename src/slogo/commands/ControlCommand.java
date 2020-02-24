package slogo.commands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;

import java.util.List;

public interface ControlCommand extends Command {

    static double executeAndExtractValue(Command command, TurtleStatus ts, List<TurtleStatus> ret) {
        TurtleStatus currentStatus = (ret.isEmpty()) ? ts : ret.get(ret.size()-1);
        ret.addAll(command.execute(currentStatus));
        return command.returnValue();
    }
}
