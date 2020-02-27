package slogo.backendexternal;

import java.util.*;

public class TurtleModel {

    //TODO: make ObservableMap?
    Map<String, Double> declaredVariables;

    //TODO: change implementation/display method/remove altogether?
    Collection<String> declaredFunctions;

<<<<<<< HEAD
    double lastReturn;

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
=======
    public TurtleModel() {
        declaredVariables = new HashMap<>();
        declaredFunctions = new ArrayList<>();
    }

    public Collection<String> getVariables() {
        return declaredVariables.keySet();
    }

    public Collection<String> getFunctions() {
>>>>>>> origin/master
        return declaredFunctions;
    }

    public void clearVariables() {
        declaredVariables.clear();
        declaredFunctions.clear();
    }
<<<<<<< HEAD

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

    /**
     * @return the return value of the last command executed.
     */
    public double getLastReturn() {
        return lastReturn;
    }
=======
>>>>>>> origin/master
}
