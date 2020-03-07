parser
====

This project implements a development environment that helps users write SLogo programs.

Names: Dennis, Tyler, Lucy, Shruthi, Nevzat


### Timeline

Start Date: 02/13/2020

Finish Date: 3/7/2020

Hours Spent: ~ 250 Hours

### Primary Roles
Dennis Harrsch:
Tyler Jang:
Lucy Gu: 

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

Nevzat Sevim:
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

FrontEnd: 
- Toggle active turtles
- Add multiple turtles
- Add multiple workspaces 
- Set and save preferences for new workspaces
- Execute past commands
- Move turtles graphically
- Change pen's current properties
- Execute list of commands
- display command histories, variables, and commands
- display turtle information (x position, y position, bearing, etc)



### Notes/Assumptions

Assumptions or Simplifications:


Interesting data files:
tabLayout1.xml : can change and load this file to set workspace preferences
0_saved.xml : can save preferences of current workspace to this file

Known Bugs: There are issues where after a certain series of animations the turtle can disappear. Additionally, very long commands with weird spacing can cause issues with parsing, so 
calling functions step by step is better. Clicking on turtles to activate or deactivate them is not fully incorporated. Formatting can be slightly off on some computers.

Extra credit: Implemented almost all of complete outside of unlimited parameters and simulation. Advanced error handling which guesses at what the user
meant to enter, based on distance from strings.


### Impressions

