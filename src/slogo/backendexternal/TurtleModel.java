package slogo.backendexternal;

import java.util.*;

public class TurtleModel {

    //TODO: make ObservableMap?
    Map<String, Double> declaredVariables;

    //TODO: change implementation/display method/remove altogether?
    Collection<String> declaredFunctions;

    public TurtleModel() {
        declaredVariables = new HashMap<>();
        declaredFunctions = new ArrayList<>();
    }

    public Collection<String> getVariables() {
        return declaredVariables.keySet();
    }

    public Collection<String> getFunctions() {
        return declaredFunctions;
    }

    public void clearVariables() {
        declaredVariables.clear();
        declaredFunctions.clear();
    }
}
