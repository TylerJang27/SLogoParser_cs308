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
 * Class that implements IdCommand, used to change the active turtle.
 *
 * @author Tyler Jang
 */
public class Tell implements IdCommand {
    public static final int NUM_ARGS = 1;
    private List<Command> args;

    private double lastId;

    /**
     * Constructor for Tell. Takes 1 command argument.
     *
     * @param ids Commands representing the different commands to set turtle IDs.
     */
    public Tell(List<Command> ids) {
        args = ids;
        if (ids.isEmpty()) {
            args = List.of(new Constant(1));
        }
    }

    /**
     * Executes the Tell instance, retrieving and setting the activeTurtles based on args. Also sets the last returned TurtleStatus instance to be for the new ID.
     *
     * @param manifest a TurtleManifest containing information about all the turtles
     * @return   a List of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        for (Command c: args) {
            ids.add((int)Command.executeAndExtractValue(c, manifest, ret));
        }

        manifest.setActiveTurtles(ids);
        manifest.makeActiveTurtle(ids.get(0));
        System.out.println(ids.get(0));
        lastId = ids.get(ids.size() - 1);
        ret.add(manifest.getActiveState());
        System.out.println(manifest.getActiveState());
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
