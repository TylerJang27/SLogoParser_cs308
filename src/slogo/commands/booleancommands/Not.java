package slogo.commands.booleancommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.BooleanCommand;
import slogo.commands.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Not implements BooleanCommand{
    private Command arg1;
    private double returnVal;
    public static final int NUM_ARGS = 1;

    public Not(Command argA){
        arg1 = argA;
    }

    @Override
    public List<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        double val = arg1.returnValue();
        returnVal = (val==0) ? TRUE : FALSE;
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }

}
