package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.Collection;

/**
 *
 * @author Tyler Jang, Lucy Gu
 */
public interface Command {
    public static final int NUM_ARGS = 0;
    double xMax = 100;
    double yMax = 100;        //TODO: change to front end display size
    public final static String[] MODES = {"normal", "edge", "toroidal"};

    public Collection<TurtleStatus> execute(TurtleStatus ts);
    public double returnValue();

    public static boolean toClear() {
        return false;
    }
}
