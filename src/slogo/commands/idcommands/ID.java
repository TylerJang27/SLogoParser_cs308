package slogo.commands.idcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.IdCommand;
import java.util.List;
import java.util.function.Supplier;

/**
 * Class that implements IdCommand, used to query the active turtle.
 *
 * @author Tyler Jang
 */
public class ID implements IdCommand {
    public static final int NUM_ARGS = 0;
    private Supplier<Integer> supp;

    private double activeID;

    /**
     * Constructor for Tell. Takes 1 argument.
     *
     * @param supplier Supplier for retrieving the active turtle in TurtleModel.
     */
    public ID(Supplier<Integer> supplier) {
        supp = supplier;
    }

    /**
     * Executes the Tell instance, retrieving and setting the activeTurtles based on args.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        activeID = supp.get();
        return List.of(ts);
    }

    /**
     * Retrieves the value returned by ID's execution, the active turtle ID.
     *
     * @return the active turtle's ID.
     */
    @Override
    public double returnValue() {
        return activeID;
    }
}
