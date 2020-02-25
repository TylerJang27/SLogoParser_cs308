package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Remainder implements MathCommand {

    private Command arg1;
    private Command arg2;
    private double returnVal;
    public static final int NUM_ARGS = 2;

    public Remainder(Command argA, Command argB){
        arg1 = argA;
        arg2 = argB;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        double[] val = MathCommand.twoArgOperation(ret, ts, arg1, arg2);
        returnVal = (val[0])%(val[1]);
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }

}
