package util;
import java.awt.Color;							
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Maze;

public class Cell {
private int x, y, distance, id;
	
	private Cell parent;
	
	private boolean visited = false;						// to check if the cell is visited 
	private boolean path = false;							// to check if the cell is in the path
	private boolean deadEnd = false;						// to check if the cell is a deadend
	
	private boolean[] walls = {true, true, true, true};		// initially as all walls will be present all positions represents true 
	
	public boolean[] getWalls() {							// function to get the wall array 
		return walls;
	}

	public void setWalls(boolean[] walls) {					//	to change the wall array to input array 
		this.walls = walls;
	}

	public Cell(int x, int y) {								// argument constructor with x,y as inputs 	 
		this.x = x;											// for setting x,y of the partcular object
		this.y = y;
		this.distance = -1;
	}
	
	public int getX() {										// function to get x
		return x;
	}
	
	public int getY() {										// function to get y
		return y;
	}
	
	public int getId() {									// function to get id of the cell
		return id;
	}

	public void setId(int id) {								// function to set id of the cell
		this.id = id;
	}
	
	public boolean isVisited() {							// function to check whether the cell is visited (returns true when visited)
		return visited;
	}
	
	public void setVisited(boolean visited) {				// function to set or change whether it has been visited or not
		this.visited = visited;
	}
	
	public boolean isDeadEnd() {							// to check if thr cell is a dead end
		return deadEnd;
	}
	
	public void setDeadEnd(boolean deadEnd) {				// to change DeadEnd
		this.deadEnd = deadEnd;
	}

	public boolean isPath() {								// to get the path variable
		return path;
	}

	public void setPath(boolean path) {						// to change the path variable
		this.path = path;
	}
	
	public int getDistance() {								// to get the distance		
		return distance;
	}

	public void setDistance(int distance) {					// to change the distance
		this.distance = distance;
	}

	public Cell getParent() {								// to get the parent of the current cell
		return parent;
	}
	
	public void setParent(Cell parent) {					// to change or make a new parent of the cell
		this.parent = parent;
	}
	
	public void draw(Graphics g) {							// function to draw the cell according to it's condition (represented by colour)
		int x2 = x * Maze.W;
	    int y2 = y * Maze.W;
	    
	    if (visited) {										// if the cell is already visited once then set it's colour to magenta
	    	g.setColor(Color.MAGENTA);	
	    	g.fillRect(x2, y2, Maze.W, Maze.W);
	    }
	   
	    if (path) {											// if it is in the path then colour the cell with blue colour 
	    	g.setColor(Color.BLUE);
	    	g.fillRect(x2, y2, Maze.W, Maze.W);
	    } else if (deadEnd) {								// else if it is a deadend colour it with red colour
	    	g.setColor(Color.RED);
	    	g.fillRect(x2, y2, Maze.W, Maze.W);
	    }
	    
	    g.setColor(Color.WHITE);
	    if (walls[0]) {										// if the walls are true ( present then draw the walls )
	    	g.drawLine(x2, y2, x2+Maze.W, y2);
	    }
	    if (walls[1]) {
	    	g.drawLine(x2+Maze.W, y2, x2+Maze.W, y2+Maze.W);
	    }
	    if (walls[2]) {
	    	g.drawLine(x2+Maze.W, y2+Maze.W, x2, y2+Maze.W);
	    }
	    if (walls[3]) {
	    	g.drawLine(x2, y2+Maze.W, x2, y2);
	    } 
	}
	
	public void displayAsColor(Graphics g, Color color) {	// function used to draw a cell with the colour as input 
		int x2 = x * Maze.W;								// initialising x2,y2 (initial points of cell )
	    int y2 = y * Maze.W;
		g.setColor(color);									// determining the colour
    	g.fillRect(x2, y2, Maze.W, Maze.W);
	}
	
	public void removeWalls(Cell next) {					// function to remove walls
		int x = this.x - next.x;							// determining the diffence of x co-ordinates of cells of present and that of 
		 // top 0, right 1, bottom 2, left 3				// input ( next )
		
		if(x == 1) {										// if x=1 then the cell (next ) is to the left of the present cell
			walls[3] = false;								// so break the left wall of present and right of next cell 
			next.walls[1] = false;
		} else if (x == -1) {								// else if x=-1 then next is to the right of the present cell
			walls[1] = false;								// break the left of ciurrent and right wall of next cell
			next.walls[3] = false;
		}
		
		int y = this.y - next.y;							// determining the diffence of x co-ordinates of cells of present and that of 
															// input ( next )
		
		if(y == 1) {										// similar to that of the x co-ordinates this can be dealt
			walls[0] = false;
			next.walls[2] = false;
		} else if (y == -1) {
			walls[2] = false;
			next.walls[0] = false;
		}
	}

	private Cell randomNeighbour(List<Cell> neighbours) {						// chooses a random cell ( neighbour of the present cell )
		if (neighbours.size() > 0) {											// checking if it has neighbours
			return neighbours.get(new Random().nextInt(neighbours.size()));		// randomly selecting a neoghbour using Random function
		} else {
			return null;
		}
	}
	
	private Cell checkNeighbourInGridBounds(List<Cell> grid, Cell neighbour) {	// function to givr all possible neighbours of the cell
		if (grid.contains(neighbour)) {											// checking whether it has the neighbour
			return grid.get(grid.indexOf(neighbour));							// returning the index of neighbour
		} else {
			return null;														// if no neighbou return null
		}
	}
	

	public Cell getUnvisitedNeighbour(List<Cell> grid) {						// to get the unvisted neighbours of the current cell
		
		List<Cell> neighbours = getUnvisitedNeighboursList(grid);				// get the list od unvisisted neighbours of the current cell 
		
		if (neighbours.size() ==  1) {											// if it contains only one neighbour (unvisited) then return it
			return neighbours.get(0);
		}
		return randomNeighbour(neighbours);										// else if it contains more than 1 cell randomise and return any
	}
	
	public List<Cell> getUnvisitedNeighboursList(List<Cell> grid) {				// to get the list of unvisisted neighbours of the cell
		
		List<Cell> neighbours = new ArrayList<Cell>(4);							// as it can have a maximum of 4 neighbours an array list of length 4 
																				// has been created

		Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));		// in these 4 lines we will be checking whether top,right,bottom,left
		Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));		// cells are neighbour of the present cell 
		Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));		// if not present it will return null else it will return it's index
		Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));
		
		if (top != null) if(!top.visited) neighbours.add(top);					// in these 4 lines we will be checking whether the top, right, bottom
		if (right != null) if(!right.visited) neighbours.add(right);			// left cells are present as well as not visited and then will be added
		if (bottom != null)if(!bottom.visited) neighbours.add(bottom);			// to the array list of neighbours
		if (left != null) if(!left.visited)neighbours.add(left);
		
		return neighbours;														// return the list
	}
//	
//	// no walls between
	public List<Cell> getValidMoveNeighbours(List<Cell> grid) {					// to get the list of neighbours to which it is connnected to (no wall 
		List<Cell> neighbours = new ArrayList<Cell>(4);							// present between them )

		Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));		// in these four lines we will be checking whether it has four 
		Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));		// neighbours on each side and if present get it's position
		Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
		Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));
		
		if (top != null) {														// here when the neighbours are present at each side we will check
			if (!walls[0]) neighbours.add(top);									// whether the wall between thme is present or not
		}																		// if it is not present then they are connected and will be added to
																				// the array list that will be returned
		if (right != null) {
			if (!walls[1]) neighbours.add(right);
		}
		
		if (bottom != null) {
			if (!walls[2]) neighbours.add(bottom);
		}
		
		if (left != null) {
		if (!walls[3]) neighbours.add(left);
		}
		
		return neighbours;
	}


	@Override																	// overriding the function from imported package
	public boolean equals(Object obj) {											
		if (this == obj)														// returns true when present object =  to the argument
			return true;
		if (obj == null)														// if obj is null we return fslse
			return false;
		if (getClass() != obj.getClass())										// if the getclass() functions output are different for both 		
			return false;														// then return false same for x,y of both objects
		Cell other = (Cell) obj;					
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
