package slogo.backendexternal.parser;

import java.util.List;
import slogo.commands.Backward;
import slogo.commands.Command;
import slogo.commands.Forward;
import slogo.commands.Home;

//NOT COMPLETE
public class CommandFactory {
  private List<Command> POSSIBLE_BETS;

  public CommandFactory() {
    POSSIBLE_BETS = List.of(
        new Backward(1.0),
        new Forward(1.0),
        new Home()
    );
  }

  public Command makeCommand() {

  }
}
