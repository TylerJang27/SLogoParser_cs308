package slogo.backendexternal;

import slogo.commands.Command;

import java.util.*;

/**
 * Class used to execute the Commands produced by Parser. This class then returns a List of TurtleStatus instances.
 *
 * @author Tyler Jang, Lucy Gu
 */
    //TODO: REFACTOR OUT TURTLEMANAGER
public class TurtleModel {
    Map<Integer, TurtleStatus> availableTurtles;
    List<Integer> activeTurtles;
    Integer activeTurtle;

    double lastReturn;

    /**
     * Constructor for TurtleModel, initializing its availableTurtles and activeTurtles fields
     */
    public TurtleModel() {
        initialize();
    }

    /**
     * Resets/clears the functions and variables for the scope.
     */
    public void initialize() {
        availableTurtles = new HashMap<>();
        initializeNewTurtle(1);
        activeTurtles = new ArrayList<>();
        activeTurtles.add(1);
        setActiveTurtles(Arrays.asList(1));
    }

    /**
     * Updates the active turtles to be a new List of turtles
     *
     * @param turtles List of turtles to replace activeTurtles
     */
    public void setActiveTurtles(List<Integer> turtles) {
        activeTurtles = turtles;
        for (Integer k: activeTurtles) {
            initializeNewTurtle(k);
        }
    }

    /**
     * Creates a new turtle with a new id if one does not already exist
     *
     * @param k ID of new turtle to add
     */
    private void initializeNewTurtle(Integer k) {
        if (!availableTurtles.containsKey(k)) {
            availableTurtles.put(k, new TurtleStatus());
        }
    }

    /**
     * Updates the active turtles to include a singular turtle
     *
     * @param turtle singular turtle to update activeTurtles
     */

//    public void setActiveTurtles(Integer turtle) {
//        setActiveTurtles(turtle);
//        setActiveTurtles(List.of(turtle));
//    }

    /**
     * Returns an unmodifiable copy of the List of active turtles
     *
     * @return unmodifiableList of activeTurtles
     */
    public List<Integer> getActiveTurtles() {
        return Collections.unmodifiableList(activeTurtles);
    }

    /**
     * Returns the Integer corresponding to the singular current active turtle
     *
     * @return Integer corresponding to a turtle ID
     */
    public Integer getActiveTurtle() {
        return activeTurtle;
    }

    /**
     * Returns the most recent turtle status for a given turtle ID
     *
     * @param k the indexable ID of the turtle queried
     * @return the TurtleStatus corresponding to the most recent status for the given ID
     */
    public TurtleStatus getTurtleState(Integer k) {
        initializeNewTurtle(k);
        return availableTurtles.get(k);
    }

    /**
     * Returns a map of all the turtles' most recent statuses
     *
     * @return a Map of IDs to TurtleStatuses
     */
    public Map<Integer, TurtleStatus> getAllTurtles() {
        return Collections.unmodifiableMap(availableTurtles);
    }

    /**
     * Updates the TurtleStatus in availableTurtles to account for TurtleStatus updates
     *
     * @param k the indexable ID of the turtle queried
     * @param ts the TurtleStatus corresponding to the most recent status for the given ID
     */
    public void updateTurtleState(Integer k, TurtleStatus ts) {
        availableTurtles.put(k, ts); //TODO: MAKE SURE TELL CALLS THIS
    }

    /**
     * Checks for equality without using fail condition
     *
     * @param firstList first list to check equality
     * @param secondList second list to check equality
     * @return whether or not the two lists have the same values
     */
    private boolean listEquality(List<Integer> firstList, List<Integer> secondList) {
        List<Integer> list1 = List.copyOf(firstList);
        for (int k = firstList.size() - 1; k >= 0; k --) {
            if (k < secondList.size() && list1.get(k).equals(secondList.get(k))) {
                list1.remove(k);
            }
        }
        return list1.isEmpty() && firstList.size() == secondList.size();
    }

    //TODO: REFACTOR ONCE CONFIRMED IT'S WORKING
    /**
     * Executes the parsed Command instances and returns a List of TurtleStatus instances.
     *
     * @param commandList a List of all parsed commands.
     * @return            a List of resulting TurtleStatus instances.
     */
    public List<TurtleStatus> executeCommands(List<Command> commandList) {
        List<TurtleStatus> statusList = new ArrayList<>();

        for (Command c: commandList) {
            List<Integer> myActives = List.copyOf(getActiveTurtles());
            for (Integer turtleID : myActives) {
                setActiveTurtles(Arrays.asList(turtleID));
                TurtleStatus status = getTurtleState(turtleID);
                statusList.add(status);

                List<TurtleStatus> newStatuses = c.execute(status);

                if (!newStatuses.isEmpty()) {
                    for (TurtleStatus ts : newStatuses) {
                        updateTurtleState(ts.getID(), ts);
                    }
                    statusList.addAll(newStatuses.subList(1, Math.max(newStatuses.size(), 0)));
                } else {
                    System.out.println("ALERT, PLEASE TELL TYLER: TURTLE STATUSES ARE EMPTY");
                }
                //statusList.addAll(newStatuses);
                //TODO: VERIFY THAT THIS WORKS ^^^
                lastReturn = c.returnValue();
            }
        }
        return Collections.unmodifiableList(statusList);
    }

    //TODO: ARRIVED AT SIMILAR BUT ALTERNATE IMPLEMENTATION, REMOVE ONCE CONFIRMED WITH LUCY
    public List<TurtleStatus> executeCommands(List<Command> commandList, List<Integer> turtleIDs) {
        List<TurtleStatus> statusList = new ArrayList<>();
        for(int id:turtleIDs) {
            TurtleStatus ts;
            if(!availableTurtles.keySet().contains(id)){
                ts = new TurtleStatus(id);
                availableTurtles.put(id, ts);
            }
            else ts = availableTurtles.get(id);

            statusList.add(ts);
            for (Command c : commandList) {
                List<TurtleStatus> newStatuses = c.execute(ts);
                if (!newStatuses.isEmpty()) {
                    statusList.addAll(newStatuses.subList(1, Math.max(newStatuses.size(), 0)));
                }
                ts = statusList.get(statusList.size() - 1);
                availableTurtles.put(id, ts);
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
