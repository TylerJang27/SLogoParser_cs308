package slogo.view;

import java.util.Collection;
import slogo.backendexternal.TurtleStatus;


/**
 *
 * @author Shruthi Kumar
 */
public interface MainViewAPI {

  /**
   * Reads in the command so that it can be sent to the back end
   */
  String readCommand();

  /**
   * Sends the command to the back end
   */
  void sendCommand(String command);

  /**
   * Send information on whether the user changes the pen or the turtle
   */
  int sendUpdates();

  /**
   * Tells the backend side to update the language
   */
  void changeLanguage(int choice);

  /**
   * Returns all current runtime commands
   */
  Collection<TurtleStatus> getCommands();

  /**
   * Stores commands that have been sent in history
   * Removes these commands from current runtime set
   */
  void updatePastCommands();

  /**
   * Clears all current and past runtime commands
   */
  void clearCommands();

  /**
   * Reset commands
   */
  void resetCommands();

  void setSkin(int choice);

}
