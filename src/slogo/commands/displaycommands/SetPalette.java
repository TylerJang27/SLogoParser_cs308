package slogo.commands.displaycommands;

import slogo.backendexternal.TurtleManifest;
import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.DisplayCommand;

import java.lang.reflect.Array;
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
    public List<TurtleStatus> execute(TurtleManifest manifest) {
        System.out.println("execute set palette");
        List<TurtleStatus> ret = new ArrayList<>();
        ret.addAll(arg1.execute(manifest));
        returnVal = arg1.returnValue();


        ret.addAll(arg2.execute(manifest));
        ret.addAll(arg3.execute(manifest));
        ret.addAll(arg4.execute(manifest));

        TurtleStatus t = new TurtleStatus(manifest.getActiveState());
        int[] argval = {(int)arg1.returnValue(), (int)arg2.returnValue(), (int)arg3.returnValue(), (int)arg4.returnValue()};
        System.out.println(argval[0] + " " + argval[1] + " " + argval[2] + ' ' + argval[3]);
        t.setRunnable(() -> con.accept(argval));
        ret.add(t);
        return ret;
    }

    @Override
    public double returnValue() {
        return returnVal;
    }
}
