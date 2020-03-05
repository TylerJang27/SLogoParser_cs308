package slogo.commands.idcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.IdCommand;
import java.util.List;
import java.util.function.Supplier;

/**
 * Class that implements IdCommand, used to query the number of created turtles.
 *
 * @author Tyler Jang
 */
public class Turtles implements IdCommand {
    public static final int NUM_ARGS = 0;
    private Supplier<Integer> supp;

    private double numTurtles;

    /**
     * Constructor for Turtles. Takes 0 command arguments.
     *
     * @param supplier Supplier for retrieving the number of created turtles in TurtleModel.
     */
    public Turtles(Supplier<Integer> supplier) {
        supp = supplier;
    }

    /**
     * Executes the Tell instance, retrieving the number of turtles.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        numTurtles = supp.get();
        return List.of(ts);
    }

    /**
     * Retrieves the value returned by Turtle's execution, the number of turtles.
     *
     * @return the number of turtles.
     */
    @Override
    public double returnValue() {
        return numTurtles;
    }
}
