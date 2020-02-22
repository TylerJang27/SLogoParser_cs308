package slogo.commands.mathcommands;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.MathCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Pi implements MathCommand {

    private double returnVal;
    public static final int NUM_ARGS = 0;

    public Pi(){
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts){
        List<TurtleStatus> ret = new ArrayList<>();
        returnVal = Math.PI;
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }

}
