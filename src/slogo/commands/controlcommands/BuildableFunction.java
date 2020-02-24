package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.TurtleCommand;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tyler Jang
 */
public class BuildableFunction implements ControlCommand {

    //SHOULD BE SEPARATE FROM MAKE--THIS REPRESENTS A SKELETON FOR A FUNCTION THAT THE USER CAN DEFINE

    Map<String, Integer> myVariables;
    Collection<TurtleCommand> myContent;
    double finalVal;
    public static final int NUM_ARGS = 2;

    public BuildableFunction(Map<String, Integer> variables, Collection<TurtleCommand> content) {
        myVariables = variables;
        myContent = content;
        finalVal = 0;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> statusList = new ArrayList<>();
        statusList.add(ts);
        for (TurtleCommand c: myContent) {
            statusList.addAll(c.execute(statusList.get(statusList.size() - 1)));
            finalVal = c.returnValue();
            //TODO: Figure out how the internal commands have access to the local variables
            //TODO: ^^create a new TurtleModel instance with these local variables???
        }
        return statusList;
    }

    @Override
    public double returnValue() {
        return finalVal;
    }
}
