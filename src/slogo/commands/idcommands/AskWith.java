package slogo.commands.idcommands;

import slogo.backendexternal.TurtleManifest;
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
    private Command condition;
    private List<Command> commands;

    private double lastResult;

    /**
     * Constructor for Tell. Takes 2 command arguments.
     *
     * @param cond Command representing the condition for turtles.
     * @param commandList Commands representing commands to run for specified IDs.
     */
    public AskWith(Command cond, List<Command> commandList) {
        condition = cond;
        commands = commandList;
    }

    //TODO: REFACTOR
    /**
     * Executes the AskWith instance, determining ids to use, retrieving and setting the activeTurtles based on ids, running the commands, and resetting the active turtles.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<Integer> allTurtles = manifest.getAvailableTurtles();
        List<Integer> previousIds = manifest.getAllActiveTurtles();
        Integer previousId = manifest.getActiveTurtle();
        List<TurtleStatus> ret = new ArrayList<>();

        List<Command> validIds = new ArrayList<>();

        TurtleManifest dummyManifest = new TurtleManifest(manifest);

        for (Integer turtleId: allTurtles) {
            //Does not modify information
            dummyManifest.makeActiveTurtle(turtleId);
            manifest.makeActiveTurtle(turtleId);
            dummyManifest.updateTurtleState(turtleId, manifest.getTurtleState(turtleId));

            ret.addAll(condition.execute(dummyManifest));
            if (condition.returnValue()==1) {
                validIds.add(new Constant(turtleId));
            }
        }
        manifest.setActiveTurtles(previousIds);
        manifest.makeActiveTurtle(previousId);

        Ask asker = new Ask(validIds, commands);
        ret.addAll(asker.execute(manifest));
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
