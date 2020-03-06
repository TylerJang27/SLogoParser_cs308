package slogo.commands.displaycommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.DisplayCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SetPenColor implements DisplayCommand {
    public static final int NUM_ARGS = 1;
    private Consumer<Integer> con;
    private Command arg1;
    private double returnVal;

    public SetPenColor(Command argA, Consumer<Integer> consumer){
        arg1 = argA;
        con = consumer;
    }


    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        //TODO: BUG: NEED TO UPDATE IN THE BACKEND
        returnVal = DisplayCommand.indexAndAddRunnable(ret, arg1, manifest, con);
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }
}
