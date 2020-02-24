package slogo.commands.booleancommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.BooleanCommand;
import slogo.commands.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class And implements BooleanCommand{
    private Command arg1;
    private Command arg2;
    private double returnVal;
    public static final int NUM_ARGS = 2;

    public And(Command argA, Command argB){
        arg1 = argA;
        arg2 = argB;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        double[] val = BooleanCommand.twoArgOperation(ret, ts, arg1, arg2);
        returnVal = (val[0]!=0 && val[1]!=0) ? TRUE : FALSE;
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }

}
