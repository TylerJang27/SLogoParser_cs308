# API Changes
#### Team members: dmh58, sk593, taj26, wg74, ns217

## Goals
### Frontend Internal API:
The goal of the internal front end API will be to construct various components of the UI that can be easily modifiable, easily accessed, and easily extended by future users. Thus, the internal API should make simple changes around these features and adding new ones. In addition, of course, it will be necessary for the internal API to handle or carry out to fruition the executable actions that the back end determines and gives to the front end via its external API. Additionally, it will be important that the internal API generally relies as little as possible on the specific implementation at hand (JavaFX) such that it could be used in future front end implementations that someone else might want to use.

### Frontend External API:
The external API on the front end should be responsible for sending the command to the backend side. This will allow the backend side to use the command to move the turtle. The external API on the front end will be contained in the CommandLine class, which will not only read in commands with readCommands(), but will store commands until they are run, and upon the run it will send commands as strings to the controller. Thus the primary goal of taking in and handling user input, then sending it to the user is achieved.

### Backend Internal API:
The goal of the internal API on the back end is to facilitate the conversion of Strings representing commands into TurtleStatus instances. This will be accomplished by first parsing the Strings into Command implementation instances, which can then be executed by the TurtleModel to update its status and create these TurtleStatus instances. The front end should not need to know how this processing is handled internally, it just needs to know how to convert TurtleStatus instances into meaningful visual updates.

### Backend External API:
The goal of the external API on the backend will be to communicate with the frontend visualization so that the GUI reflects calculations and changes made on the backend. Much of these commands for the backend will be contained in the TurtleModel, which will necessary have to communicate to the Controller the resulting TurtleStatus instances from the parsing of the command in the Parser and executing the command in the BackendInternalAPI. The Backend external API will also have to communicate to the front end whether or not a command is valid - meaning that error handling will result in communication between the front and back end via the external API.


## API
### Internal Front-End
1. TurtleView
    - Void executeStatus(TurtleStatus t) 
 
    Executes the command that the user enters by doing the action specified in the command
    - Void updateX(int x), updateY(int y) [renamed to setMyStartXPos() and setMyStartYPos()]
    
    Updates the value of the x and y positions of the turtle as it moves
    - Void updateBearing(int degrees) [renamed to setMyBearing()]
    
    Updates the current direction of the turtle by given degree increment
    - Void changeSkin(String filename) [renamed to setImageView()]
    
    Changes image used for turtle
    - Void draw(Point start, Point end) [renamed addPenViewLines()]
    
    Draws a line that is visible on UI from starting point to ending point on a straight line. Does this for all commands given through GUI
    - Void penUp(), penDown() [NOT IN TurtleView - MOVED TO NEW CLASS CALLED PenView]
    
    Changes whether or not draw() is called when turtle moves
    
[CHANGES FOR BASIC/COMPLETE: added PenView]


2. PenView
- Void setPenDown() [ADDED]
    
    Changes whether or not draw() is called when turtle moves
- Void updateMyLines() [ADDED]
    
    Updates the pen lines to be drawn on the pane

- List getLines() [ADDED]

    Returns the list of lines that need to be drawn 

- Color getPenColor [ADDED]

    Returns the pen color

- Void setPenColor [ADDED]

    Sets the pen color 


### External Front-End
1. CommandLine
    - Void readCommand(String input)
    
    Stores command from user input in current set of runtime commands
    - String getCommands()
    
    Returns all current runtime commands
    - Void updatePastCommands()
    
    Stores commands that have been sent in history
    
    Removes these commands from current runtime set
    - Void clearCommands()
    
    Clears all current and past runtime commands

### Internal Back-End
1. Parser

  * Parses a singular command String, which may include multiple commands, and returns their parsed output as a Collection of one or more commands.
    * public void parseLine(String line) *[SAME]*
  
  * Returns the current list of commands to Controller to be sent to be executed in TurtleModel/TurtleStatus
    * public List<slogo.commands.Command> sendCommands() *[SAME]*
  
  * Iterates over each of the individual parts of the command and returns Command objects based on the input if they can be found.
    * public Stack<Command> parseComponents(Stack<String> components) throws InvalidCommandException 
  
  * Returns a list of the strings input by the user to display as a history and allow for user interaction
    * public List<String> getCommandHistory() *[REMOVED]* Decided that it would be easiest to store this in the console where it is displayed.
  
  * Sets the language for the commands to be read in as.
    * public void setLanguage(String lang) *[ALTERED]* Now takes in a translator object which can pass in the translated commands to be used.
  
  * Adds the error message with information to the user to Controller to be shownin the conosle.
    * public void addError(String message) *[ADDED]* Created to implement more advanced error handling that returns the default message as well as prompt user to correct input
  
  * Returns the history of variables to be displayed by the controller.
    * public String getVariableString() *[ADDED]* Moved from turtle model after the implementation of the Variable Factory

  * Returns formalized version of a command, throws exception if the command cannot be found.
    * public String validateCommand(String command) *[ADDED]*
	 
   *[ADDED]*
	 Note: The following components to Parser were not originally planned, but were implemented internally
    	 when it became clear that it would make the division of labor clearer to the user, and make the Parser API
    	 cleaner and more compact - were previously to be included in the Parser, or in TurtleModel, but added to the 
    	 Parser API.
    	 
   Used in Parser API:
   
   A. CommandFactory
   * Builds commands based on input from Parser
        * public CommandFactory(Map<String, List<String>> commands)
   * Returns a command after determining parameters, and a call to buildCommand
        * public Command makeCommand(String command, Stack<Command> previous, Stack<List<Command>> listCommands, Map<String, List<String>> myCommands) throws InvalidArgumentException
        
   * Uses a constructor of the name of the key and inputs from make command to call the declaredConstructor and return a Command
        * public Command buildCommand(String key, List<Command> commands, Stack<List<Command>> listCommands) throws InvalidCommandException
        
   * Returns a new constant based on the current value
        * public Command makeConstant(String current) 
      
   * Changes mode based on user input so that commands execute according to current mode.
        * public void setMode(String mode)
      
  B. VariableFactory
   * Returns variables if previously defined, else makes new variables
        * public VariableFactory()
      
   * Returns a makeVariable from the given command, variable from map of variables
        * public MakeVariable makeVariable(Command previous)
      
   * Returns true if the variable has been previously handled/created, else creates variable (puts in local map)
        * public boolean handleVariable(String current)
      
   * Returns variable from local map
        * public Variable getVariable(String varName)
      
   * Returns a new make variable to define a previous variable with the new given command
        * public MakeVariable setVariable(Command command)
      
   * Returns a string representation of a variable to its value for display
        * public String getVariableString()
       
  C. FunctionFactory
   * Builds functions and returns runFunctions if previously defined
        * public FunctionFactory(Map<String, List<String>> commands)
      
   * Returns true if a function of a given name has been previously defined
        * public boolean hasFunction(String funcName)
      
   * Returns a RunFuction for a predetermined Function that has been made
        * public RunFunction runFunction(String funcName, Stack<Command> commands)
      
   * Returns a MakeUserInstruction from the given components
        * public MakeUserInstruction handleFunction(Stack<String> components)
      
   * Returns a Function from the given components to be used in a MakeUserInstruction
        * public Function buildFunction(String key, List<Command> commands, Stack<List<Command>> listCommands)
      
  D. Translator
   * Changes the internal mappings of commands to their representation in the new language
        * public void SetLanguage(String language)
      
   * Returns the Map of current commands to their representation in the current language
        * public Map<String, List<String>> getCommands()
      
   * Returns the translated version of a given command from the current language to the new language entered
        * public String translateCommand(String command, String language)
      
2. TurtleManager *[RENAMED]* 
    Used for modeling the motion of the turtle on the back-end, by executing Commands.
	- Collection<String> getVariables() *[REMOVED]*
	
	Returns a Collection of the defined variables for this runtime environment.
	*[This functionality was moved to the Parser section of the backend]*
	- void clearVariables() *[REMOVED]*
	
    Clears all variable previously defined by user
    *[This functionality was moved to the Parser section of the backend]*
	- Collection<NestedCommand> getFunctions() *[REMOVED]*
	
	Returns a Collection of the defined functions for this runtime environment.
    - TurtleStatus createStatus(Command c) *[REMOVED]*

    Parses a singular command to return a TurtleStatus instance by calling Command's execute method.
    *[This functionality was moved to the TurtleManifest section of the backend]*
	- List<TurtleStatus> executeCommands(List<Command> c) *[MODIFIED]*
    
    Takes in a List of Commands commands and converts them into a List of Command implementation instances by executing them. 	
    *[Changed the name and converted Collections to Lists]*
    - double getLastReturn() *[ADDED]*
    
    Returns the value of the last executed command.

3. TurtleManifest *[ADDED]*
    Account for different turtle IDs, storing the TurtleStatus instances associated with the most recent status of each ID.
    - void initialize() *[ADDED]*
    
    Resets all the turtles to only include a singular turtle at the default status.
    - void setActiveTurtles(List<Integer> turtles), setActiveTurtles(Integer turtle) *[ADDED]*
    
    Sets the List of active turtles for each command to run. Initializes new turtles for each ID if necessary.
    - void makeActiveTurtle(Integer turtle) *[ADDED]*
    
    Sets the active turtle ID for current commands to retrieve.
    - List<Integer> getActiveTurtles() *[ADDED]*
    
    Retrieves the list of active turtles.
    - Integer getActiveTurtle() *[ADDED]*
    
    Retrieves the singular active turtle.
    - TurtleStatus getTurtleState(Integer k) *[ADDED]*
    
    Retrieves the TurtleStatus instance associated with the specified turtle.
    - List<Integer> getAvailableTurtles() *[ADDED]*
    
    Retrieves the IDs associated with all the turtles that have been created.
    -void updateTurtleState(Integer id, TurtleStatus ts) *[ADDED]*
    
    Updates the TurtleStatus associated with the given ID.

4. Interface Command
    Used for defining norms for commands to execute to create new TurtleStatus instances. Each command must implement this interface to provide its own functionality.
	- TurtleStatus execute (TurtleStatus ts)

    A command will use its instance variables, and the previous TurtleStatus instance to generate and return a new TurtleStatus instance.
	- Number returnValue()
	
	Returns the value that results from executing some commands.

5. Interface NestedCommand implements Command
    Used for defining commands that may have nested components, such as loops, conditionals, or functions.
	- Collection<Command> getChildren()
    
    Returns a NestedCommand instance?s children, held within its brackets.
	- Collection<Command> getAllChildren()

    Returns a NestedCommand instance?s children and recursively calls its children?s getChildren() methods.

### External Back-End
1. TurtleStatus
    
    A data class for storing information about a turtle's status, to be returned from the back end and used for updating the front end's visualization.
    - int getX()
	
	Returns the int for the turtle's x-direction.
	- int getY()
	
	Returns the int for the turtle's y-direction.
	- int getBearing()
	
	Returns the int for the turtle's direction, starting North and going clockwise.
	- boolean getTrail() *[RENAMED]*
    
    Returns whether or not the line should be drawn smoothly, with animation, or in a single step.
    - boolean getPenDown() *[ADDED]*
    
    Returns whether the pen should be down for the given TurtleStatus instance.
    - boolean getPenDraw() *[ADDED]*
    
    Returns whether the pen should draw a line connecting the previous and current points.
	- boolean getVisible() *[ADDED]*
	
	Returns whether the turtle is visible or hiding.
	- void setClear() *[ADDED]*
	
	Sets whether the screen should be cleared when the TurtleStatus is done being processed.
	- boolean hasRunnable() *[ADDED]*
	
	Returns whether or not this TurtleStatus instance carries a runnable functional interface.
	- void setRunnable() *[ADDED]*
	
	Sets this TurtleStatus instance's runnable functional interface.
	- void modify() *[ADDED]*
	
	Calls this TurtleStatus instance's runnable's run() method.
	- void clearVar() *[REMOVED]*
	
	Clears the stored variables
	*[TurtleStatus does not hold variable information.]*
	- Map<String,Number> getVars() *[REMOVED]*
    
    Returns a Map of any variables created by a command's execution, to be read and forwarded to a variable explorer if desired.
    *[TurtleStatus does not hold variable information.]*
    
2. Parser throws InvalidCommandError
    
    The hub of the back end that takes Strings and turns them into Commands and TurtleStatus instances.
	- void validateCommand(String s) throws InvalidCommandError
    
    Checks if a given command is recognized as a possible command. If not, throws error.
	- Collection<TurtleStatus> parseCommands(Collection<String> commandStrings)
    
    Takes in a collection of String commands and converts them into a Collection of TurtleStatus instances.
    Catches and handles invalid command errors by not executing commands and returning to Controller with the error, which in turn will update the GUI


## Reflection

The API changes made so far have been to compensate for our team's under-engineering of some components of the design. For example, we did not expect TurtleStatus to include as much information as it does now, and we also expected TurtleModel (now TurtleManager and TurtleManifest) to have more responsibilites. On the frontend, the Views were also likewise divided up into smaller classes with more delegation. 

Most of these changes have been relatively minor and have been thoroughly communicated across the team so that the team members' code could be adjusted accordingly to compensate. These changes have nevertheless stuck to our original pipeline, and our general interface for Commands, Parser, and Views.

These changes are for the better, and in dealing with the different challenges of Complete, we have made our program more flexible, rather than more rigid.

