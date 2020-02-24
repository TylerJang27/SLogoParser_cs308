package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Tyler Jang
 */
public class MakeUserInstruction implements ControlCommand {

    public static final int NUM_ARGS = 3;

    private List<Variable> myVariables;
    private Collection<Command> myCommands;
    private Function myFunction;

    //TODO Dennis: PLEASE CHECK IN THE PARSER TO ENSURE THAT arg1 CONTAINS A Function INSTANCE
    //TODO Dennis: PLEASE CHECK IN PARSER TO ENSURE THAT arg2 CONTAINS A Collection of Variable INSTANCES
    //TODO Dennis: Please ensure that the parser accounts for these variables occurring within the function
    public MakeUserInstruction(Function func, List<Variable> variables, Collection<Command> commands) {
        myFunction = func;
        myVariables = variables;
        myCommands = commands;
    }

    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        myFunction.setVariables(myVariables);
        myFunction.setCommands(myCommands);
        return new ArrayList<>();
    }

    @Override
    public double returnValue() {
        return 1;
        //TODO: IT SAYS TO RETURN 0 IF NOT SUCCESSFULLY DEFINED; WHEN WOULD THAT BE?
        // Should I have try-catch stuff?
    }

    //TODO: Remove if possible
    public Function getFunctionHolder() {
        return myFunction;
    }
}
