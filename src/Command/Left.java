package Command;

import BackEndExternal.TurtleStatus;
import java.util.*;

public class Left implements Command {

    double degree;

    public Left(double turn){
        degree = turn;
    }


    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        Collection<TurtleStatus> ret = new ArrayList<>();
        double deltaHeading = 0-degree;
        return Command.turnDeltaHeading(ts, ret, deltaHeading);
    }


    @Override
    public int returnValue() {
        return (int)degree;
    }
}
