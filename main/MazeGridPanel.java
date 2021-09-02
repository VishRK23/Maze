package main;
import generator.*;
import solver.BFSSolve;
//import solver.*;
import util.Cell;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;	
public class MazeGridPanel extends JPanel {

	private final ArrayList<Cell> grid = new ArrayList<Cell>();                // creating th grid (array list)
public MazeGridPanel(int rows, int cols) {                                     // adding each cell with (x,y) co-ordinates 
		for (int x = 0; x < rows; x++) {                                       // to the array list grid by transversing by row and then by column
			for (int y = 0; y < cols; y++) {                                   
				grid.add(new Cell(x, y));
			}
		}
	}

	@Override                                                                  //  overriding the function 
	public Dimension getPreferredSize() {
		// +1 pixel on width and height so bottom and right borders can be drawn.
		return new Dimension(Maze.WIDTH + 1, Maze.HEIGHT + 1);                 // 
	}

	public void generate() {

		new DFSGen(grid, this);                                                // calling the DFSGEN function  

	}

	public void solve() {

		new BFSSolve(grid, this);                                              // calling the BFS solve function 

		}
	@Override
	protected void paintComponent(Graphics g) {                               // the starting point of graphics part
		super.paintComponent(g);                                              // calling the paintComponent function in the super class 
		for (Cell c : grid) {                                                 // parsing through the grid cell by cell and draing it
			c.draw(g);
		}
		grid.get(0).displayAsColor(g, Color.GREEN);                           // start cell
		grid.get(grid.size() - 1).displayAsColor(g, Color.YELLOW);            // end or goal cell
	}

}
