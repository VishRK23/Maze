package generator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import main.*;
import util.*;
public class DFSGen {															// genrator class

	private Stack stack = new Stack();											// making a new tack data structure
	private final List<Cell> grid; 												// making grid and current cell that will be used later
	private Cell current;

	public DFSGen(List<Cell> grid, MazeGridPanel panel) {						// constructor of the class DFSGEN
		this.grid = grid;														// setting up the variables according to the argument
		current = grid.get(0);
		final Timer timer = new Timer(Maze.speed, null);						// timer
		timer.addActionListener(new ActionListener() {							
			@Override
			public void actionPerformed(ActionEvent e) {						// function to parse through the whole string and 
				if (!grid.parallelStream().allMatch(c -> c.isVisited())) {		// call the carve function for the current cell
					carve();
				} else {														// if all has been visited then make the generated variable 
					current = null;												// true (as maze is genrated)
					Maze.generated = true;
					timer.stop();												// thn stop the timer
				}
				panel.repaint();												// then repaint the panel and delay and then start the timer
				timer.setDelay(Maze.speed);
			}
		});
		timer.start();
	}

	private void carve() {
		current.setVisited(true);												// as the cell is visited set it to true	
		Cell next = current.getUnvisitedNeighbour(grid);						// go to the next unvisited neighbour 
		if (next != null) {														// if their is a neighbour add it to the stack 
			stack.push(current);												// and remove the walls as a connection has been made
			current.removeWalls(next);
			current = next;														// go to the next cell 
		} else if (!stack.isEmpty()) {											// if the stack is not empty pop the top element in stack to 
			try {																// the current cell 
				current = stack.pop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}