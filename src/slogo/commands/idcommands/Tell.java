package slogo.commands.idcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.IdCommand;
import slogo.commands.controlcommands.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class that implements IdCommand, used to change the active turtle.
 *
 * @author Tyler Jang
 */
public class Tell implements IdCommand {
    public static final int NUM_ARGS = 1;
    private Consumer<List<Integer>> con;
    private Supplier<TurtleStatus> supp;
    private List<Command> args;

    private double lastId;

    /**
     * Constructor for Tell. Takes 1 command argument.
     *
     * @param ids Commands representing the different commands to set turtle IDs.
     * @param consumer Consumer for modifying the activeTurtles in TurtleModel.
     * @param supplier Supplier for retrieving the new activeTurtle Status in the event of tree recursion (e.g. fd tell 2).
     */
    public Tell(List<Command> ids, Consumer<List<Integer>> consumer, Supplier<TurtleStatus> supplier) {
        args = ids;
        if (ids.isEmpty()) {
            args = List.of(new Constant(1));
        }
        con = consumer;
        supp = supplier;
    }

    /**
     * Executes the Tell instance, retrieving and setting the activeTurtles based on args. Also sets the last returned TurtleStatus instance to be for the new ID.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        for (Command c: args) {
            ids.add((int)Command.executeAndExtractValue(c, ts, ret));
        }
        con.accept(ids);
        lastId = ids.get(ids.size() - 1);
        ret.add(supp.get());
        return ret;
    }

    /**
     * Retrieves the value returned by Tell's execution, the last turtle ID.
     *
     * @return the turtle's ID.
     */
    @Override
    public double returnValue() {
        return lastId;
    }
}
