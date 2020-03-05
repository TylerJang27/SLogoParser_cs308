package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Interface that extends Command, for instances of commands related to querying data related to the turtle.
 * Date is returned from the most recent TurtleStatus instance.
 *
 * All implementations of this interface should have 0 arguments in their constructors.
 */
public interface DisplayCommand extends Command {


    static double indexAndAddRunnable(List<TurtleStatus>ret, Command arg1, TurtleStatus ts, Consumer<Integer> con){

        ret.addAll(arg1.execute(ts));
        double rv = arg1.returnValue();

        TurtleStatus t = new TurtleStatus(ret.get(ret.size()-1));
        t.setRunnable(() -> con.accept((int)rv));

        ret.add(t);

        return rv;
    }
}
