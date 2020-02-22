package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Sin implements MathCommand {

    private Command arg1;
    private double returnVal;
    public static final int NUM_ARGS = 1;

    public Sin(Command argA){
        arg1 = argA;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        int val = (int)arg1.returnValue();
        returnVal = Math.sin(val);
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }

}
