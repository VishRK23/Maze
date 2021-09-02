package solver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.LinkedList;
import java.util.List;
//import java.util.Queue;

import javax.swing.Timer;

import main.*;
import util.*; 
public class BFSSolve {												// class to solve using breadth first algorithm
	private final Queue queue = new Queue(400);						// making a queue data structur of sixe 400 
	private Cell current;											// making an instance of Cell (to save current cell ) and an array list 
	private final List<Cell> grid;									

	public BFSSolve(List<Cell> grid, MazeGridPanel panel) {			// constructor of the BFSSolve class
		this.grid = grid;											// initialising grid, current using the given argument
		current = grid.get(0);
		current.setDistance(0);
		queue.queueEnqueue(current);								// adding the current cell which we are dealing with to the queue
		final Timer timer = new Timer(Maze.speed, null);			// timer to run 
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!current.equals(grid.get(grid.size() - 1))) {	// from the last cell of the grid till the current cell is encountered
					flood();										// call flood function to increase distance by 1
				} else {											
					drawPath();
					Maze.solved = true;								// maze has been solved
					timer.stop();									// stop the timer
				}
				panel.repaint();									// repain the panel and delay the timer then start it again for next steps
				timer.setDelay(Maze.speed);
			}
		});
		timer.start();
	}
	
	private void flood() {													// function flood
		current.setDeadEnd(true);											// set the current cell as a deadend
		current = queue.queueDequeue();										// talking out the last element from queue and storing it in current
		List<Cell> adjacentCells = current.getValidMoveNeighbours(grid);	// getting valid neighbours of the cell taken out
		for (Cell c : adjacentCells) {										// parsing through the neighbours
			if (c.getDistance() == -1) {									// if distance is -1 then make it 0 and update it in the cell
				c.setDistance(current.getDistance() + 1);					// and enqueue it
				c.setParent(current);
				queue.queueEnqueue(c);
			}
		}
	}
	
	private void drawPath() {												// function to draw the path set the path	
		while (current != grid.get(0)) {									// while the first cell is not encountered (0,0)
			current.setPath(true);											// set that the cell is in the path of the solution and go to it's
			current = current.getParent();									// parent cell 
		}
	}
}
