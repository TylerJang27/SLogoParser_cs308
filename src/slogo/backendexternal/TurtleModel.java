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

    Map<Integer, TurtleStatus> availableID;

    double lastReturn;

    public TurtleModel(List<Variable> vars, List<RunFunction> functions) {
        declaredVariables = vars;
        declaredFunctions = functions;
        availableID = new HashMap<>();
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
    public List<TurtleStatus> executeCommands(List<Command> commandList, TurtleStatus ts) {
        List<TurtleStatus> statusList = new ArrayList<>();
        statusList.add(ts);
        TurtleStatus status = ts;
        for (Command c: commandList) {
            System.out.println(c);
            List<TurtleStatus> newStatuses = c.execute(status);
            if (!newStatuses.isEmpty()) {
                statusList.addAll(newStatuses.subList(1, Math.max(newStatuses.size(), 0)));
            }
            //statusList.addAll(newStatuses);
            //TODO: VERIFY THAT THIS WORKS ^^^
            status = statusList.get(statusList.size() - 1);
            lastReturn = c.returnValue();
        }
        for (TurtleStatus t: statusList) {
//            System.out.println(t); //TODO: REMOVE
        }
        return Collections.unmodifiableList(statusList);
    }

    public List<TurtleStatus> executeCommands(List<Command> commandList, List<Integer> turtleIDs) {
        List<TurtleStatus> statusList = new ArrayList<>();
        for(int id:turtleIDs) {
            TurtleStatus ts;
            if(!availableID.keySet().contains(id)){
                ts = new TurtleStatus(id);
                availableID.put(id, ts);
            }
            else ts = availableID.get(id);
            
            statusList.add(ts);
            for (Command c : commandList) {
                List<TurtleStatus> newStatuses = c.execute(ts);
                if (!newStatuses.isEmpty()) {
                    statusList.addAll(newStatuses.subList(1, Math.max(newStatuses.size(), 0)));
                }
                ts = statusList.get(statusList.size() - 1);
                availableID.put(id, ts);
                lastReturn = c.returnValue();
            }
        }
        return Collections.unmodifiableList(statusList);
    }


    /**
     * @return the return value of the last command executed.
     */
    public double getLastReturn() {
        return lastReturn;
    }
}
