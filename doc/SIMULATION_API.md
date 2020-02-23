# Parser Team 20 - API Lab Discussion
## Simulation API Overview
### Names: Dennis, Tyler, Lucy, Shruthi, Nevzat
This file provides an overview of the API found in a well-designed simulation project.

## Method Publicity
Choose someone's previous Cell Society project and examine it from the perspective of an API by categorizing each public or protected method in the Simulation "module" from following perspectives:

1. A method should not be part of the API (i.e., it should not be public):

    A method should not be public/part of the API when it is crucial to the functioning of the implementation. 
In these cases, a public facing method would potentially be problematic in the case where another class or user accessed or changed something that they’re not supposed to, which might interfere with the assumptions made or the normal functioning of the class in which it originates.

2. A method should be part of the external API because it helps people on the team working in other modules write their code

    This is true - for example, the output from the simulation is needed by the visualization, so there must be some public facing way of accessing these outputs. Similarly, one can imagine methods which might make certain interpretations for other components of a program while not necessarily allowing access to the information itself, such as a function from simulation which determines if a given cell changes in a specific iteration.

3. A method should be part of the internal API because it helps future coders within the module extend it

    This is also true - private methods should make understanding and implementing or extending the functionality easier. For example, methods which might allow for an easy implementation of a different simulation, such as those which handle neighborhood functionalities, so that a future programmer might not have to worry about smaller details like that in their implementation.

## API Description for Simulation
Write a simplified (i.e., less than one page) API description of both the internal and external Simulation API. Note which classes are abstract/interface and which concrete and, for concrete classes that are part of the external API, justify why they do not need to represent an abstraction.
Then describe how to use your new external and internal APIs to do the following tasks:

1. external: as a client of the backend, the frontend selects an existing simulation to display and then starts running that kind of simulation, updating its own grid visualization

    As a client of the backend, there are public facing methods that retrieve the input file from the existing simulation and pass it to the backend, without any chance for the file to be altered. The backend then takes that file, configures its parameters, grid, and rules, and commences running the simulation. Note that there must also be public facing API methods which then communicate from the backend back to the front end which signal the scene to change, and then continually allow for the front end to see (but not change!) the current state of each cell in the grid, and therefore the grid itself. Note that all calculations are kept hidden in the internal API, and all communication with the user is kept hidden from the backend, and only helpful information is communicated between the two.

    Methods used: 
    - public void updateGrid(int width, int height) - called
    - public Cell getCell(int row, int col) - called
    - public boolean gridContainsCell(int row, int col) - called

2. internal: a new developer joins the the backend team and adds a new possible kind of simulation that can be available for the frontend to choose

    The internal API should handle the implementation of the basic functions of the new simulation’s cell type. Since each cell is extendable, the internal API should not have to change that much. The new cell created will merely implement the functions that are part of the already existing API. The new class that implements the internal API will need to provide implementations of the functions to fit the needs of that specific simulation. 

    Methods used: 
    - public abstract int updateCell(Grid cellGrid, int row, int col, int width, int height) - implemented 
    - public abstract int getState() - implemented 
    - public abstract String getCellColor() - implemented 
    - public abstract void setCellColor() - implemented 
    - public Neighbor getNeighbors() - called
    - public boolean checkState(Grid grid, int row, int col, int currState) - called
    - public boolean checkNextState(Grid grid, int row, int col, int nextState) - called
