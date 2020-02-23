# Parser Team 20 - API Lab Discussion
## SLOGO High Level Design
### Names: Dennis, Tyler, Lucy, Shruthi, Nevzat
General concerns: 
* Maneuvering between front and back end and making sure that they?re separate
* Implementing functions that the user could write - intentional about hierarchy we make 
* Collections-like interface for Commands:
* Commands (interface)
* NestableCommands (interface)
* For loops, functions
* Forward
* Bearing
* Methods like execute(), getChildren(), etc.

#

### When does parsing need to take place and what does it need to start properly?
Parsing takes place when a command (or set of commands) has been entered by the user. To start 
properly it needs some confirmation of the command(s) being entered, and then a way of communicating 
exactly what the command was. Additionally, to truly function properly, the command will have to be 
recognized by the backend and implemented in the current version of the project (meaning that it will
have to have a way of handling the case where this isn?t true).
 
### What is the result of parsing and who receives it?
The result of parsing is storing the command that is inputted. The command should be deconstructed 
(into direction and amount of movement) into something that the backend side can read and act accordingly on.
The external API should receive the command but it should be processed and acted upon internally.

### When are errors detected and how are they reported?
Errors should probably be detected when the command has been entered, during parsing. It will be 
important to catch errors at this early stage so that no work is attempted on a bad command or in a 
way that will break the whole program. This will also allow for quick feedback to the user, and it 
will be important to have the channels in the external API for these errors to be thrown, recognized,
and handled/represented on the front end.
 
### What do commands know, when do they know it, and how do they get it?
This is one of the parts here that is definitely most likely to change during the actual implementation, 
but after discussing briefly we decided that a command should know exactly enough for its implementation, 
and nothing more (and should have no more power than knowledge). Meaning that a command to move the turtle 
shouldn?t have access to change the turtle?s position, but it might need to know the x and y position
to enact its implementation properly. 
 
### How is the GUI updated after a command has completed execution?
The GUI should move the turtle based on the command inputted. It should show the direction the turtle
is moving in and how far the turtle has moved. There should be some indication (likely through a trail)
of the direction the turtle has moved in. This would technically happen before a command has completed
execution, but if there is an error in the command, the GUI should report the error so that the user
can input a new command. 
