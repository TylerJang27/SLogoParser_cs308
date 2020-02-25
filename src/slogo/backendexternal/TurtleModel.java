package slogo.backendexternal;

import slogo.commands.controlcommands.RunFunction;
import slogo.commands.Command;
import slogo.commands.controlcommands.Variable;

import java.util.*;

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

    public Collection<TurtleStatus> executeCommands(List<Command> commandList) {
        List<TurtleStatus> statusList = new ArrayList<>();
        TurtleStatus status = INITIAL_STATUS;
        for (Command c: commandList) {
            System.out.println(c);
            statusList.addAll(c.execute(status));
            status = statusList.get(statusList.size() - 1);
        }
        return statusList;
    }
}
