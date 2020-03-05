package slogo.commands.displaycommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.DisplayCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Shape implements DisplayCommand {
    public static final int NUM_ARGS = 0;
    private Supplier<Integer> sup;
    private double returnVal;

    public Shape(Supplier<Integer> sup){
        this.sup = sup;
    }


    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        returnVal=sup.get();
        return List.of(ts);
    }

    @Override
    public double returnValue() {
        return returnVal;
    }
}
