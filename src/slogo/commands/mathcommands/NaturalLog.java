package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NaturalLog implements MathCommand {

    private Command arg1;
    private double returnVal;
    public static final int NUM_ARGS = 1;

    public NaturalLog(Command argA){
        arg1 = argA;
    }

    @Override
    public List<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        int val = (int)arg1.returnValue();  //TODO: Should this be an int or double
        returnVal = Math.log(val);
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }

}
