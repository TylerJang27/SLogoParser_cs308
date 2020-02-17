package Command;
import BackEndExternal.TurtleStatus;

public interface Command {

    public TurtleStatus execute(TurtleStatus ts);
}
