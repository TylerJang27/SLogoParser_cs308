package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.Collection;

/**
 *
 * @author Tyler Jang, Lucy Gu
 */
public interface Command {
    public static final int NUM_ARGS = 0;

    public Collection<TurtleStatus> execute(TurtleStatus ts);
    public double returnValue();

    public static boolean toClear() {
        return false;
    }
}
