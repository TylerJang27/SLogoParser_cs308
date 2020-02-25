package slogo.commands.controlcommands;

import slogo.backendexternal.TurtleStatus;
import slogo.commands.Command;
import slogo.commands.ControlCommand;

import java.util.Collection;
import java.util.List;

/**
 * Class that implements ControlCommand, used to define a function/user instruction, assigning it commands and variables.
 * MakeUserInstruction is used for the TO command. The Function myFunction should be created with the default constructor before calling
 * MakeUserInstruction's constructor. Ensure that this same Function is passed to future RunFunction instances so that a function
 * may have continuity over a program.
 *
 * @author Tyler Jang
 */
public class MakeUserInstruction implements ControlCommand {

    public static final int NUM_ARGS = 3;

    private List<Variable> myVariables;
    private Collection<Command> myCommands;
    private Function myFunction;

    /**
     * Constructor for MakeUserInstruction, used to apply the variables and commands to the Function instance.
     *
     * @param func      the function to be defined.
     * @param variables the variables to be identified for the function.
     * @param commands  the commands for the function to execute, which may include instances of those variables.
     */
    //TODO Dennis: PLEASE CHECK IN THE PARSER TO ENSURE THAT arg1 CONTAINS A Function INSTANCE
    //TODO Dennis: PLEASE CHECK IN PARSER TO ENSURE THAT arg2 CONTAINS A Collection of Variable INSTANCES
    //TODO Dennis: Please ensure that the parser accounts for these variables occurring within the function
    public MakeUserInstruction(Function func, List<Variable> variables, Collection<Command> commands) {
        myFunction = func;
        myVariables = variables;
        myCommands = commands;
    }

    /**
     * Executes the MakeUserInstruction, used to apply the variables and commands to the Function.
     *
     * @param ts a singular TurtleStatus instance upon which to build subsequent TurtleStatus instances.
     *           TurtleStatus instances are given in absolutes, and thus may require other TurtleStatus values.
     * @return   a Collection of TurtleStatus instances, containing only the parameter ts.
     */
    @Override
    public Collection<TurtleStatus> execute(TurtleStatus ts) {
        myFunction.setVariables(myVariables);
        myFunction.setCommands(myCommands);
        return List.of(ts);
    }

    /**
     * Retrieves the value returned by MakeUserInstruction, the status of declaration.
     *
     * @return 1 if the function definition process is successful.
     */
    @Override
    public double returnValue() {
        return 1;
        //TODO: IT SAYS TO RETURN 0 IF NOT SUCCESSFULLY DEFINED; WHEN WOULD THAT BE?
        // Should I have try-catch stuff?
    }

    /**
     * Retrieves the Function instance for this Command.
     *
     * @return myFunction.
     */
    //TODO: Remove if possible
    public Function getFunctionHolder() {
        return myFunction;
    }
}
