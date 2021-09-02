package main;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Maze {
	public static final int WIDTH = 400;							// setting the width and height of the maze
	public static final int HEIGHT = WIDTH; 						// best to keep these the same. variable is only created for readability.
	public static final int W = 20;									// setting the width of each cell of the grid
	
	public static int speed = 1;		
	public static boolean generated, solved;						// to check whether it has been generated or solved
	
	private int cols, rows;											// to store rows and columns of the grid

	public static void main(String[] args) {
		new Maze();													// calling the constructor of maze
	}

	public Maze() {													// constructor of maze
		cols = Math.floorDiv(WIDTH, W);								// finding the number columns and setting it as rows too
		rows = cols;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}
				createAndShowGUI();										// calling the function for creating and showing th e graphics
			}
		});
	}

	private void createAndShowGUI() {									// function for graphics of the maze
		JFrame frame = new JFrame("Maze Generation Recurcive Backtracking");	// creating a Java frame in which the maze will be present

		JPanel container = new JPanel();									// creating an instance of Jpanel as container
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));	
		frame.setContentPane(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// will enable the program to stop when the X button on top right
																			// of the screen is pressed
		MazeGridPanel grid = new MazeGridPanel(rows, cols);					// setting the backfround to black
		grid.setBackground(Color.BLACK);
		
		JPanel mazeBorder = new JPanel();									// setting the border of the maze in the panel according
		final int BORDER_SIZE = 20;											// to our wish
		mazeBorder.setBounds(0, 0, WIDTH + BORDER_SIZE, HEIGHT + BORDER_SIZE);
		mazeBorder.setBackground(Color.BLACK);								// setting the colour
		mazeBorder.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
		
		mazeBorder.add(grid);			
		
		container.add(mazeBorder);											// adding thr maze border to the java panel
		
		CardLayout cardLayout = new CardLayout();

		JButton runButton = new JButton("Run");								// setting the run and solve buttons
		JButton solveButton = new JButton("Solve");
				
		JPanel card1 = new JPanel();										// objects of the jpabel class used to add the run and solve buttons
		JPanel card2 = new JPanel();
		card1.setLayout(new GridBagLayout());
		card2.setLayout(new GridBagLayout());

		card1.add(runButton);												// adding the run and solve buttons to the panel as options
		card2.add(solveButton);

		JPanel cards = new JPanel(cardLayout);								// adding solve and run to a new panel named cards
		cards.setBorder(new EmptyBorder(0, 20, 0, 0));						// as solve and gen and setting it's borders and opacity
		cards.setOpaque(false);
		cards.add(card1, "gen");
		cards.add(card2, "solve");
		
		container.add(cards);												// adding the solve and run buttons to the main panel (container)
		
		runButton.addActionListener(event -> {								// getting the action at the run button and 
						 generated = false;									// making generated and soolved to false (reseting)
			 solved = false;	
			 grid.generate();												// generating
					     cardLayout.next(cards);							// moving to the next card
		});

		solveButton.addActionListener(event -> {							// getting th einout in the solve button 
			if (generated) {												// if it has been generated then solve it else throw error
				grid.solve();	
				cardLayout.last(cards);
			} else {
				JOptionPane.showMessageDialog(frame, "Please wait until the maze has been generated.");
			}
		});
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
