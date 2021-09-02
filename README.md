# Maze
Maze Generation in JAVA using Recursive backtracing

Depth First Search (Recursive Back Tracking)
DFSGen.JAVA (Package generator)
1.	Make the initial current cell, mark it visited.
2.	While there are unvisited cells,
a.	If the cells have any neighbor cell which have not visited,
i.	Choose a random unvisited neighbor, and push it into the stack.
ii.	Do required GUI modification on that cell.
iii.	Mark it as visited.
b.	Else, if the stack is not empty,
i.	Pop a cell from the stack.
ii.	Mark it as current cell.


Breadth First Search 
BFSSolve.JAVA (Package solver)
1.	Take an initial cell, form the queue (by dequeue the queue). 
2.	Get all its valid neighboring cells
3.	For each neighboring cell, 
a.	if the distance is declared from the starting cell, 
i.	Declare the distance.
ii.	Declare its parent cell.
iii.	Enqueue the cell.
4.	Draw the path.
5.	Terminate the process if the last cell is encountered and marked as visited.


Cell.JAVA (Package util)
Contains basic parameters/function for wall generation/removal.
Function for random selection of neighbours of a Cell

GUI     (Package main)
Maze.JAVA
MazeGridPanel.JAVA
--------------------------------------------------------------------------------------------
