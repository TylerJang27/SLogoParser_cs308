parser
====

This project implements a development environment that helps users write SLogo programs.

Names: Dennis, Tyler, Lucy, Shruthi, Nevzat


### Timeline

Start Date: 02/13/2020

Finish Date: 3/7/2020

Hours Spent: ~ 250 Hours

### Primary Roles

Dennis Harrsch (controller and parser):
- Designed and implemented Parser, as well as all factories related to creating Commands, Variables, Functions, and Translations.
- Incorporated Controller as a means of bringing all portions together and reducing dependencies.
- Helped in complete to implemenet Front End advances that were best handled in Controller.
- Refactored InputFields and implemented Console, MoveButtons, and UserDefinitions.

Tyler Jang (back end developer):
- Designed and implemented several commands, specifically ControlCommands.
    - Focused on loops and user-defined instructions.
- Assisted with the implementation of multiple turtles on both the front and back ends
    - Created TurtleManager and TurtleManifest to track turtle states.
    - Modified TurtleView and TurtleViewManager to compensate for multiple turtles.

Lucy Gu (backend developer): 
- Setup backend external and internal API
- Setup command interfaces
- Implemented commands including turtle movement commands, math commands, boolean commands
- Set up display commands to interact with front end using runnables, suppliers, consumers
- Extensively refactored command factory to use reflection

Shruthi Kumar (front end developer):
- Set up the initial Display, MainView, and Toolbar 
- Worked primarily on the TurtleView and TurtleViewManager
    - Implemented the map of IDs to TurtleViews and TurtleStatuses on the front end
    - Developed the execute() and executeState methods that provided animation on the front end
- Loaded data from resource files
    - Reading in data from properties files
- Added slider to let users change the rate of animation 
- XML parsing, reading, and writing from/to files
    - Allowed users to load preferences and save preferences of workspaces

Nevzat Sevim (front end developer):
- Set up the initial Display, MainView, and Toolbar 
- Created Input fields for communication with controller
- GUI optimization and Turtle image implementation
- Created methods for Simulation Settings that are called from the controller

### Resources Used
[https://www.dummies.com/programming/java/javafx-how-to-use-property-events/](Property Events)


### Running the Program

Main class: 
- Controller: sets up the display and handles running the function

Data files needed: 
- Properties files: 
    - Backend: Language property files (Chinese, English, French, etc)
    - Command property files (Command, CommandCounter, CommandFactory, etc)
    - Button/Label/Menu property files (buttons, modes, turtleSkin, etc)
    - Exception files (exceptionMessges)
- Image files:
    - Frontend: mickey, raphael, turtle


Features implemented:

BackEnd: 
- All commands (some parsing bugs remain)
- Recursive function calls
- Multiple turtles
- Commands to change display settings
- Text input suggestion
- Support for multiple languages
- Informative error messages

FrontEnd: 
- Toggle active turtles
- Add multiple turtles
- Add multiple workspaces 
- Set and save preferences for new workspaces
- Execute past commands
- Move turtles graphically
- Change pen's current properties
- Execute list of commands
- Display command histories, variables, and commands
- Display turtle information (x position, y position, bearing, etc)



### Notes/Assumptions

Assumptions or Simplifications:
People know how to code in Slogo (only basic tutorial given). We assume spacing will always be a singular space.

Interesting data files:
tabLayout1.xml : can change and load this file to set workspace preferences
0_saved.xml : can save preferences of current workspace to this file

Known Bugs: There are issues where after a certain series of animations the turtle can disappear. Additionally, very long commands with weird spacing can cause issues with parsing, so 
calling functions step by step is better. Clicking on turtles to activate or deactivate them is not fully incorporated. Formatting can be slightly off on some computers. Ask, AskWith, DoTimes, and For do not currently work.

Extra credit: Implemented almost all of complete outside of unlimited parameters and simulation. Advanced error handling which guesses at what the user
meant to enter, based on distance from strings.


### Impressions
This was by far the heftiest project any of us have ever created, and no singular individual can explain the design and implementation of all the components.


