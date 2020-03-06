package slogo.commands.idcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.IdCommand;
import slogo.commands.controlcommands.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class that implements IdCommand, used to run a list of commands for turtles specified by conditions.
 *
 * @author Tyler Jang
 */
public class AskWith implements IdCommand {
    public static final int NUM_ARGS = 2;
    private Consumer<List<Integer>> idConsumer;
    private Supplier<Integer> singleIdSupplier;
    private Supplier<List<Integer>> idSupplier;
    private Supplier<Map<Integer, TurtleStatus>> mapSupplier;
    private Consumer<Integer> idSetter;
    private Supplier<TurtleStatus> statusSupplier;
    private Command condition;
    private List<Command> commands;

    private double lastResult;

    /**
     * Constructor for Tell. Takes 2 command arguments.
     *
     * @param cond Command representing the condition for turtles.
     * @param commandList Commands representing commands to run for specified IDs.
     * @param consumeIDs Consumer for modifying the activeTurtles in TurtleManager.
     * @param supplySingleID Supplier for retrieving the activeTurtle in TurtleManager.
     * @param supplyID Supplier for retrieving the activeTurtles in TurtleManager.
     * @param supplyMap Supplier for retrieving information on all activeTurtles in TurtleManager.
     * @param consumeID Consumer for modifying the activeTurtle in TurtleManager.
     * @param supplyStat Supplier for retrieving the new activeTurtle Status in TurtleManager.
     */
    public AskWith(Command cond, List<Command> commandList, Consumer<List<Integer>> consumeIDs, Supplier<Integer> supplySingleID, Supplier<List<Integer>> supplyID, Supplier<Map<Integer, TurtleStatus>> supplyMap, Consumer<Integer> consumeID, Supplier<TurtleStatus> supplyStat) {
        condition = cond;
        commands = commandList;
        idConsumer = consumeIDs;
        singleIdSupplier = supplySingleID;
        idSupplier = supplyID;
        mapSupplier = supplyMap;
        idSetter = consumeID;
        statusSupplier = supplyStat;
    }

    //TODO: REFACTOR
    /**
     * Executes the AskWith instance, determining ids to use, retrieving and setting the activeTurtles based on ids, running the commands, and resetting the active turtles.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        Map<Integer, TurtleStatus> allStatuses = mapSupplier.get();
        List<TurtleStatus> ret = new ArrayList<>();

        List<Command> validIds = new ArrayList<>();

        for (Map.Entry<Integer, TurtleStatus> e: allStatuses.entrySet()) {
            ret.addAll(condition.execute(e.getValue()));
            if (condition.returnValue()==1) {
                validIds.add(new Constant(e.getKey()));
            }
        }

        Ask asker = new Ask(validIds, commands, idConsumer, singleIdSupplier, idSupplier, idSetter, statusSupplier);
        ret.addAll(asker.execute(ret.get(ret.size() - 1)));
        lastResult = asker.returnValue();
        return ret;
    }

    /**
     * Retrieves the value returned by AskWith's execution, the last executed command's return value.
     *
     * @return the last command's return value.
     */
    @Override
    public double returnValue() {
        return lastResult;
    }
}
