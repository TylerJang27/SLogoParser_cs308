# API Changes
#### Team members: dmh58, sk593, taj26, wg74, ns217

## Goals
### Frontend Internal API:
The goal of the internal front end API will be to construct various components of the UI that can be easily modifiable, easily accessed, and easily extended by future users. Thus, the internal API should make simple changes around these features and adding new ones. In addition, of course, it will be necessary for the internal API to handle or carry out to fruition the executable actions that the back end determines and gives to the front end via its external API. Additionally, it will be important that the internal API generally relies as little as possible on the specific implementation at hand (JavaFX) such that it could be used in future front end implementations that someone else might want to use.

### Frontend External API:
The external API on the front end should be responsible for sending the command to the backend side. This will allow the backend side to use the command to move the turtle. The external API on the front end will be contained in the CommandLine class, which will not only read in commands with readCommands(), but will store commands until they are run, and upon the run it will send commands as strings to the controller. Thus the primary goal of taking in and handling user input, then sending it to the user is achieved.

### Backend Internal API:
The goal of the internal API on the back end is to facilitate the conversion of Strings representing commands into TurtleStatus instances. This will be accomplished by first parsing the Strings into Command implementation instances, which can then be ?executed? by the TurtleModel to update its status and create these TurtleStatus instances. The front end should not need to know how this processing is handled internally, it just needs to know how to convert TurtleStatus instances into meaningful visual updates.

### Backend External API:
The goal of the external API on the backend will be to communicate with the frontend visualization so that the GUI reflects calculations and changes made on the backend. Much of these commands for the backend will be contained in the TurtleModel, which will necessary have to communicate to the Controller the resulting TurtleStatus instances from the parsing of the command in the Parser and executing the command in the BackendInternalAPI. The Backend external API will also have to communicate to the front end whether or not a command is valid - meaning that error handling will result in communication between the front and back end via the external API.


## API
### Internal Front-End
1. TurtleView
    - Void executeStatus(TurtleStatus t)
    
    Executes the command that the user enters by doing the action specified in the command
    - Void updateX(int x), updateY(int y)
    
    Updates the value of the x and y positions of the turtle as it moves
    - Void updateBearing(int degrees)
    
    Updates the current direction of the turtle by given degree increment
    - Void changeSkin(String filename)
    
    Changes image used for turtle
    - Void draw(Point start, Point end)
    
    Draws a line that is visible on UI from starting point to ending point on a straight line
    - Void penUp(), penDown()
    
    Changes whether or not draw() is called when turtle moves

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


  public void parseLine(String line)
  public List<slogo.commands.Command> sendCommands()
  public Stack<Command> parseComponents(Stack<String> components) throws InvalidCommandException 
  public List<String> getCommandHistory()
  public void setLanguage(String lang)
  public void addError(String message)
  public String getVariableString() 

    Parses a singular command String, which may include multiple commands, and returns their parsed output as a Collection of one or more commands.
	- Collection<Command> parseLine(String s) *[SAME]* (Note: refactoring in process with reflection)
	
	Returns formalized version of a command, throws exception if the command cannot be found.
	 - String validateCommand(String command) *[ADDED]*
	 
	 Note: The following components to Parser were not originally planned, but were implemented internally
	 when it became clear that it would make the division of labor clearer to the user, and make the Parser API
	 cleaner and more compact.
	*[ADDED]*
    Used in Parser API:
    1a. CommandFactory
        Returns
        Command makeCommand
          public CommandFactory(Map<String, List<String>> commands)
          public Command makeCommand(String command, Stack<Command> previous, Stack<List<Command>> listCommands, Map<String, List<String>> myCommands) throws InvalidArgumentException
          public Command buildCommand(String key, List<Command> commands, Stack<List<Command>> listCommands) throws InvalidCommandException
          public Command makeConstant(String current) 
          public void setMode(String mode)
    1b. VariableFactory
        Variable makeVariable
          public VariableFactory()
          public MakeVariable makeVariable(Command previous)
          public boolean handleVariable(String current)
          public Variable getVariable(String varName)
          public MakeVariable setVariable(Command command)
          public String getVariableString()
    1c. FunctionFactory
        Function makeFunction
      public FunctionFactory(Map<String, List<String>> commands)
      public boolean hasFunction(String funcName)
      public RunFunction runFunction(String funcName, Stack<Command> commands)
      public MakeUserInstruction handleFunction(Stack<String> components)
      public Function buildFunction(String key, List<Command> commands, Stack<List<Command>> listCommands)
    1d. Translator
        void SetLanguage
        
    

2. TurtleModel 
    Used for modeling the motion of the turtle on the back-end, by ?executing? Commands.
	- Collection<String> getVariables() *[REMOVED]*
	
	Returns a Collection of the defined variables for this runtime environment.
	- void clearVariables()
	
    Clears all variable previously defined by user
	- Collection<NestedCommand> getFunctions()
	
	Returns a Collection of the defined functions for this runtime environment.
    - TurtleStatus createStatus(Command c)

    Parses a singular command to return a TurtleStatus instance by calling Command?s execute method.
	- Collection<TurtleStatus> updateStatus(Collection<Command> c) *[RENAMED]*
    
    Takes in a collection of String commands and converts them into a Collection of Command implementation instances. 	

3. Interface Command
    Used for defining norms for commands to execute to create new TurtleStatus instances. Each command must implement this interface to provide its own functionality.
	- TurtleStatus execute (TurtleStatus ts)

    A command will use its instance variables, and the previous TurtleStatus instance to generate and return a new TurtleStatus instance.
	- Number returnValue()
	
	Returns the value that results from executing some commands.

4. Interface NestedCommand implements Command
    Used for defining commands that may have nested components, such as loops, conditionals, or functions.
	- Collection<Command> getChildren()
    
    Returns a NestedCommand instance?s children, held within its brackets.
	- Collection<Command> getAllChildren()

    Returns a NestedCommand instance?s children and recursively calls its children?s getChildren() methods.

### External Back-End
1. TurtleStatus
    
    A data class for storing information about a turtle?s status, to be returned from the back end and used for updating the front end?s visualization.
    - int getX()
	
	Returns the int for the turtle?s x-direction.
	- int getY()
	
	Returns the int for the turtle?s y-direction.
	- int getBearing()
	
	Returns the int for the turtle?s direction, starting North and going clockwise.
	- boolean getSmooth()
    
    Returns whether or not the line should be drawn smoothly, with animation, or in a single step.
	- void clearVar()
	
	Clears the stored variables
	- Map<String,Number> getVars()
    
    Returns a Map of any variables created by a command?s execution, to be read and forwarded to a variable explorer if desired.

2. Parser throws InvalidCommandError
    
    The hub of the back end that takes Strings and turns them into Commands and TurtleStatus instances.
	- void validateCommand(String s) throws InvalidCommandError
    
    Checks if a given command is recognized as a possible command. If not, throws error.
	- Collection<TurtleStatus> parseCommands(Collection<String> commandStrings)
    
    Takes in a collection of String commands and converts them into a Collection of TurtleStatus instances.
    Catches and handles invalid command errors by not executing commands and returning to Controller with the error, which in turn will update the GUI





