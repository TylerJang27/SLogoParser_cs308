package slogo.commands.idcommands;

import slogo.backendexternal.TurtleManifest;
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
    private List<Command> turtleIds;
    private List<Command> commands;

    private double lastResult;

    /**
     * Constructor for Tell. Takes 2 command arguments.
     *
     * @param ids Commands representing the different commands to set turtle IDs.
     * @param commandList Commands representing commands to run for specified IDs.
     */
    public Ask(List<Command> ids, List<Command> commandList) {
        turtleIds = ids;
        if (ids.isEmpty()) {
            turtleIds = List.of(new Constant(1));
        }
        commands = commandList;
    }

    //TODO: REFACTOR
    /**
     * Executes the Ask instance, retrieving and setting the activeTurtles based on ids, running the commands, and resetting the active turtles.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<Integer> previousIds = manifest.getAllActiveTurtles();
        Integer previousId = manifest.getActiveTurtle();
        List<TurtleStatus> ret = new ArrayList<>();

        List<Integer> ids = new ArrayList<>();
        for (Command c: turtleIds) {
            ids.add((int)Command.executeAndExtractValue(c, manifest, ret));
        }

        manifest.setActiveTurtles(ids);
        for (Integer id: ids) {
            manifest.makeActiveTurtle(id);
            for (Command c: commands) {
                lastResult = Command.executeAndExtractValue(c, manifest, ret);
            }
        }
        manifest.setActiveTurtles(previousIds);
        manifest.makeActiveTurtle(previousId);
        ret.add(manifest.getActiveState()); //TODO: REDUNDANCY ALLOWED

        return ret;
    }

    /**
     * Retrieves the value returned by Ask's execution, the last executed command's return value.
     *
     * @return the last command's return value.
     */
    @Override
    public double returnValue() {
        return lastResult;
    }
}
