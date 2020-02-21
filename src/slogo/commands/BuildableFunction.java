package slogo.commands;

import slogo.backendexternal.TurtleStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tyler Jang
 */
public class BuildableFunction implements TurtleCommand {

    //SHOULD BE SEPARATE FROM MAKE--THIS REPRESENTS A SKELETON FOR A FUNCTION THAT THE USER CAN DEFINE

    Map<String, Integer> myVariables;
    Collection<TurtleCommand> myContent;
    double finalVal;

    public BuildableFunction(Map<String, Integer> variables, Collection<TurtleCommand> content) {
        myVariables = variables;
        myContent = content;
        finalVal = 0;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> statuses = new ArrayList<>();
        statuses.add(ts);
        for (TurtleCommand c: myContent) {
            statuses.addAll(c.execute(statuses.get(statuses.size() - 1)));
            finalVal = c.returnValue();
            //TODO: Figure out how the internal commands have access to the local variables
            //TODO: ^^create a new TurtleModel instance with these local variables???
        }
        return statuses;
    }

    @Override
    public double returnValue() {
        return finalVal;
    }
}
