package slogo.backendexternal;

import slogo.commands.Command;

import java.util.*;

/**
 * Class used to execute the Commands produced by Parser. This class then returns a List of TurtleStatus instances.
 *
 * @author Tyler Jang, Lucy Gu
 */
public class TurtleManager {

    TurtleManifest myManifest;
    double lastReturn;

    /**
     * Constructor for TurtleManager, initializing its TurtleManifest.
     */
    public TurtleManager() {
        myManifest = new TurtleManifest();
    }

    /**
     * Checks for equality without using fail condition
     *
     * @param firstList first list to check equality
     * @param secondList second list to check equality
     * @return whether or not the two lists have the same values
     */
    private boolean listEquality(List<Integer> firstList, List<Integer> secondList) {
        List<Integer> copied = new ArrayList<>(firstList.size());
        Collections.copy(copied, firstList);
        for (int k = firstList.size() - 1; k >= 0; k --) {
            if (k < secondList.size() && copied.get(k).equals(secondList.get(k))) {
                copied.remove(k);
            }
        }
        return copied.isEmpty() && firstList.size() == secondList.size();
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
            List<Integer> myActives = myManifest.getAllActiveTurtles();
            for (Integer turtleID : myActives) {
                myManifest.setActiveTurtles(Arrays.asList(turtleID));
                myManifest.makeActiveTurtle(turtleID);

                List<TurtleStatus> newStatuses = c.execute(myManifest);

                statusList.addAll(newStatuses);
                //TODO: VERIFY THAT THIS WORKS ^^^
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
