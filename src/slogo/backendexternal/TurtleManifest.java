package slogo.backendexternal;

import java.util.*;

/**
 * Class used to manage information about the TurtleStatus information for each turtle ID.
 *
 * @author Tyler Jang
 */
public class TurtleManifest {
    Map<Integer, TurtleStatus> availableTurtles;
    List<Integer> activeTurtles;
    Integer activeTurtle;

    /**
     * Default Constructor for TurtleManifest, initializing its availableTurtles and activeTurtles fields
     */
    public TurtleManifest() {
        initialize();
    }

    /**
     * Constructor for TurtleManifest that creates a new TurtleManifest based on the available turtles and states in tm.
     *
     * @param tm TurtleManifest to base TurtleManifest upon.
     */
    public TurtleManifest(TurtleManifest tm) {
        this();
        for (Integer id: tm.getAvailableTurtles()) {
            updateTurtleState(id, tm.getTurtleState(id));
        }
    }

    /**
     * Resets/clears the turtle information for the scope.
     */
    public void initialize() {
        availableTurtles = new HashMap<>();
        initializeNewTurtle(1);
        activeTurtles = new ArrayList<>();
        activeTurtles.add(1);
        setActiveTurtles(Arrays.asList(1));
    }

    /**
     * Updates the active turtles to be a new List of turtles.
     *
     * @param turtles List of turtles to replace activeTurtles
     */
    public void setActiveTurtles(List<Integer> turtles) {
        activeTurtles = turtles;
        for (Integer k: activeTurtles) {
            initializeNewTurtle(k);
        }
        //makeActiveTurtle(activeTurtles.get(activeTurtles.size() - 1));
        makeActiveTurtle(activeTurtles.get(0));
    }

    /**
     * Updates the active turtles to include a singular turtle.
     *
     * @param turtle singular turtle to replace activeTurtles
     */
    public void setActiveTurtles(Integer turtle) {
        makeActiveTurtle(turtle);
        setActiveTurtles(List.of(turtle));
    }

    /**
     * Sets the singular active turtle (must already be initialized).
     *
     * @param turtle
     */
    public void makeActiveTurtle(Integer turtle) {
        activeTurtle = turtle;
    }

    /**
     * Creates a new turtle with a new ID if one does not already exist.
     *
     * @param id ID of new turtle to add
     */
    private void initializeNewTurtle(Integer id) {
        if (!availableTurtles.containsKey(id)) {
            availableTurtles.put(id, new TurtleStatus(id));
        }
    }

    /**
     * Returns the List of all active turtles.
     *
     * @return List of active turtles
     */
    public List<Integer> getAllActiveTurtles() {
        return List.copyOf(activeTurtles);
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
     * @param id the indexable ID of the turtle queried
     * @return the TurtleStatus corresponding to the most recent status for the given ID
     */
    public TurtleStatus getTurtleState(Integer id) {
        initializeNewTurtle(id);
        return availableTurtles.get(id);
    }

    /**
     * Returns a set of all the available turtles.
     *
     * @return a Map of IDs to TurtleStatuses
     */
    public List<Integer> getAvailableTurtles() {
        return List.copyOf(availableTurtles.keySet());
    }

    /**
     * Returns the state of the active turtle.
     *
     * @return the TurtleStatus instance corresponding to getActiveTurtle()
     */
    public TurtleStatus getActiveState() {
        return getTurtleState(getActiveTurtle());
    }

    /**
     * Updates the TurtleStatus in availableTurtles to account for TurtleStatus updates
     *
     * @param k the indexable ID of the turtle queried
     * @param ts the TurtleStatus corresponding to the most recent status for the given ID
     */
    public void updateTurtleState(Integer k, TurtleStatus ts) {
        availableTurtles.put(k, ts);
    }

    /**
     * Updates the TurtleStatus in availableTurtles to account for TurtleStatus updates
     *
     * @param ts the TurtleStatus corresponding to the most recent status
     */
    public void updateTurtleState(TurtleStatus ts) {
        updateTurtleState(ts.getID(), ts);
    }
}
