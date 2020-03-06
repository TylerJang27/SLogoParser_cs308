package slogo.commands.displaycommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.DisplayCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SetPenSize implements DisplayCommand {
    public static final int NUM_ARGS = 1;
    private Consumer<Double> con;
    private Command arg1;
    private double returnVal;

    public SetPenSize(Command argA, Consumer<Double> consumer){
        arg1 = argA;
        con = consumer;
    }


    //TODO: TRY TO REFACTOR
    @Override
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(manifest));

        returnVal = arg1.returnValue();
        TurtleStatus t = new TurtleStatus(ret.get(ret.size()-1));
        t.setRunnable(() -> con.accept(returnVal));

        ret.add(t);
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }
}
