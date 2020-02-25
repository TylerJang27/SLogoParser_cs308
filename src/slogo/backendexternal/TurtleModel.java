package slogo.backendexternal;

import slogo.commands.controlcommands.RunFunction;
import slogo.commands.Command;
import slogo.commands.controlcommands.Variable;

import java.util.*;

/**
 * Class used to execute the Commands produced by Parser. This class then returns
 */
//TODO Dennis: at the moment, this class does not really need to hold variables and functions.
// Would you like it to, or should this all just stay in Parser/CommandFactory?
public class TurtleModel {

    //TODO: make ObservableMap?
    Collection<Variable> declaredVariables;

    //TODO: change implementation/display method/remove altogether?
    Collection<RunFunction> declaredFunctions;

    private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();

    public TurtleModel() {
        declaredVariables = new ArrayList<>();
        declaredFunctions = new ArrayList<>();
    }

    public TurtleModel(List<Variable> vars, List<RunFunction> functions) {
        declaredVariables = vars;
        declaredFunctions = functions;
    }

    public Collection<Variable> getVariables() {
        return declaredVariables;
    }

    public Collection<RunFunction> getFunctions() {
        return declaredFunctions;
    }

    public void clearVariables() {
        declaredVariables.clear();
        declaredFunctions.clear();
    }

    public List<TurtleStatus> executeCommands(List<Command> commandList) {
        List<TurtleStatus> statusList = new ArrayList<>();
        TurtleStatus status = INITIAL_STATUS;
        for (Command c: commandList) {
            statusList.addAll(c.execute(status));
            status = statusList.get(statusList.size() - 1);
        }
        return Collections.unmodifiableList(statusList);
    }
}
