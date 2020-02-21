package slogo.commands.queriescommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.QueriesCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Tyler Jang
 */
public class IsShowing implements QueriesCommand {
    public static final int NUM_ARGS = 0;
    private static final double VISIBLE = 1;
    private static final double INVISIBLE = 0;

    private boolean showing;

    public IsShowing() {}

    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        showing = ts.getVisible();
        return Collections.unmodifiableCollection(new ArrayList<>());
    }

    public double returnValue() {
        return showing ? VISIBLE : INVISIBLE;
    }
}
