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
 * Class that implements IdCommand, used to run a list of commands for specific turtles.
 *
 * @author Tyler Jang
 */
public class Ask implements IdCommand {
    public static final int NUM_ARGS = 2;
    private Consumer<List<Integer>> idConsumer;
    private Supplier<Integer> singleIdSupplier;
    private Supplier<List<Integer>> idSupplier;
    private Consumer<Integer> idSetter;
    private Supplier<TurtleStatus> statusSupplier;
    private List<Command> args;
    private List<Command> commands;

    private double lastResult;

    /**
     * Constructor for Tell. Takes 1 command argument.
     *
     * @param ids Commands representing the different commands to set turtle IDs.
     * @param commandList Commands representing commands to run for specified IDs.
     * @param consumeIDs Consumer for modifying the activeTurtles in TurtleModel.
     * @param supplySingleID Supplier for retrieving the activeTurtle in TurtleModel.
     * @param supplyID Supplier for retrieving the activeTurtles in TurtleModel.
     * @param consumeID Consumer for modifying the activeTurtle in TurtleModel.
     * @param supplyStat Supplier for retrieving the new activeTurtle Status in TurtleModel.
     */
    public Ask(List<Command> ids, List<Command> commandList, Consumer<List<Integer>> consumeIDs, Supplier<Integer> supplySingleID, Supplier<List<Integer>> supplyID, Consumer<Integer> consumeID, Supplier<TurtleStatus> supplyStat) {
        args = ids;
        if (ids.isEmpty()) {
            args = List.of(new Constant(1));
        }
        commands = commandList;
        idConsumer = consumeIDs;
        singleIdSupplier = supplySingleID;
        idSupplier = supplyID;
        idSetter = consumeID;
        statusSupplier = supplyStat;
    }

    //TODO: REFACTOR
    /**
     * Executes the Ask instance, retrieving and setting the activeTurtles based on ids, running the commands, and resetting the active turtles.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<Integer> previousIds = idSupplier.get();
        Integer previousId = singleIdSupplier.get();
        List<TurtleStatus> ret = new ArrayList<>();

        List<Integer> ids = new ArrayList<>();
        for (Command c: args) {
            ids.add((int)Command.executeAndExtractValue(c, ts, ret));
        }

        idConsumer.accept(ids);
        for (Integer id: ids) {
            idSetter.accept(id);
            ts = statusSupplier.get();
            for (Command c: commands) {
                lastResult = Command.executeAndExtractValue(c, ts, ret);
                ts = ret.get(ret.size() - 1);
            } //TODO: TEST FOR NESTED TELLS
        }
        idConsumer.accept(previousIds);
        idSetter.accept(previousId);
        ret.add(statusSupplier.get()); //TODO: ADJUST FOR DUPLICATION?

        return ret;
    }

    /**
     * Retrieves the value returned by Asks's execution, the last executed command's return value.
     *
     * @return the last command's return value.
     */
    @Override
    public double returnValue() {
        return lastResult;
    }
}
