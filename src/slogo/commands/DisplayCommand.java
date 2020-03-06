package slogo.commands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;

import java.util.List;
import java.util.function.Consumer;

/**
 * Interface that extends Command, for instances of commands related to querying data related to the turtle.
 * Date is returned from the most recent TurtleStatus instance.
 *
 * All implementations of this interface should have 0 arguments in their constructors.
 *
 * @author Lucy Gu
 */
public interface DisplayCommand extends Command {


    static double indexAndAddRunnable(List<TurtleStatus>ret, Command arg1, TurtleManifest manifest, Consumer<Integer> con){

        ret.addAll(arg1.execute(manifest));
        double rv = arg1.returnValue();

        //TODO: DOUBLE CHECK THIS WORKS WITH THE NEW IMPLEMENTATION
        //TurtleStatus t = new TurtleStatus(ret.get(ret.size()-1));
        TurtleStatus t = new TurtleStatus(manifest.getActiveState());
        t.setRunnable(() -> con.accept((int)rv));

        ret.add(t);
        return rv;
    }
}
