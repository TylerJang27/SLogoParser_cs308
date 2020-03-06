package slogo.commands.displaycommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.DisplayCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Shape implements DisplayCommand {
    public static final int NUM_ARGS = 0;
    private Supplier<Integer> sup;
    private double returnVal;

    public Shape(Supplier<Integer> sup){
        this.sup = sup;
    }


    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        returnVal=sup.get();
        return new LinkedList<>();
    }

    @Override
    public double returnValue() {
        return returnVal;
    }
}
