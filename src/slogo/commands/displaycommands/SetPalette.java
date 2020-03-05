package slogo.commands.displaycommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.DisplayCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SetPalette implements DisplayCommand {

    private Consumer<int[]> con;
    private Command arg1;
    private Command arg2;
    private Command arg3;
    private Command arg4;
    private double returnVal;

    public SetPalette(Command argA, Command argB, Command argC, Command argD, Consumer<int[]> consumer){
        arg1 = argA;
        arg2 = argB;
        arg3 = argC;
        arg4 = argD;
        con = consumer;
    }


    @Override
    public List<TurtleStatus> execute(TurtleStatus ts) {
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(ts));
        returnVal = arg1.returnValue();


        ret.addAll(arg2.execute(ts));
        ret.addAll(arg3.execute(ts));
        ret.addAll(arg4.execute(ts));

        TurtleStatus t = new TurtleStatus(ret.get(ret.size()-1));
        t.setRunnable(() -> con.accept(new int[]{(int)arg1.returnValue(), (int)arg2.returnValue(), (int)arg3.returnValue(), (int)arg4.returnValue()}));

        ret.add(t);
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }
}
