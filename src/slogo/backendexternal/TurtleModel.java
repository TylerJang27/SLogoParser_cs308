package slogo.backendexternal;

import slogo.commands.controlcommands.RunFunction;
import slogo.commands.Command;
import slogo.commands.controlcommands.Variable;

import java.util.*;

/**
 * Class used to execute the Commands produced by Parser. This class then returns a List of TurtleStatus instances.
 */
//TODO Dennis: at the moment, this class does not really need to hold variables and functions.
// Would you like it to, or should this all just stay in Parser/CommandFactory?
public class TurtleModel {

    //TODO: make ObservableMap?
    Collection<Variable> declaredVariables;

    //TODO: change implementation/display method/remove altogether?
    Collection<RunFunction> declaredFunctions;

    private static final TurtleStatus INITIAL_STATUS = new TurtleStatus();

    //TODO: update as necessary
    /**
     * Constructor for TurtleModel, taking in Lists of variables and functions.
     *
     * @param vars      defined variables in the scope.
     * @param functions defined functions in the scope.
     */
    public TurtleModel(List<Variable> vars, List<RunFunction> functions) {
        declaredVariables = vars;
        declaredFunctions = functions;
    }

    /**
     * Default Constructor for TurtleModel, creating empty ArrayLists.
     */
    public TurtleModel() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Retrieves the declaredVariables for the scope.
     *
     * @return declaredVariables.
     */
    public Collection<Variable> getVariables() {
        return declaredVariables;
    }

    /**
     * Retrieves the declaredFunctions for the scope.
     *
     * @return declaredFunctions
     */
    public Collection<RunFunction> getFunctions() {
        return declaredFunctions;
    }

    /**
     * Resets/clears the functions and variables for the scope.
     */
    public void clearVariables() {
        declaredVariables.clear();
        declaredFunctions.clear();
    }

    /**
     * Executes the parsed Command instances and returns a List of TurtleStatus instances.
     *
     * @param commandList a List of all parsed commands.
     * @param ts          the TurtleStatus for the initial start point
     * @return            a List of resulting TurtleStatus instances.
     */
    public Collection<TurtleStatus> executeCommands(List<Command> commandList, TurtleStatus ts) {
        List<TurtleStatus> statusList = new ArrayList<>();
        statusList.add(ts);
        TurtleStatus status = ts;
        for (Command c: commandList) {
            System.out.println(c);
            List<TurtleStatus> newStatuses = c.execute(status);
            statusList.addAll(newStatuses.subList(0, Math.max(newStatuses.size() - 1, 0)));
            //TODO: VERIFY THAT THIS WORKS ^^^
            status = statusList.get(statusList.size() - 1);
        }
        return Collections.unmodifiableList(statusList);
    }
}
