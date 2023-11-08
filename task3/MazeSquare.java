public class MazeSquare extends GameSquare {
	private GameBoard board;					// A reference to the GameBoard this square is part of.
	public boolean target;						// true if this square is the target of the search.

	private boolean visited;					// True if visited, false if not

	MazeSquare currentSq;						// Current MazeSquare that the method is using
	MazeSquare targetSq = null;					// target Square static variable, originally set to null

	/**
	 * Create a new GameSquare, which can be placed on a GameBoard.
	 *
	 * @param x the x co-ordinate of this square on the game board.
	 * @param y the y co-ordinate of this square on the game board.
	 * @param board the GameBoard that this square resides on.
	 */
	public MazeSquare(int x, int y, GameBoard board)
	{
		super(x, y);
		this.board = board;
	}

	/**
	 * A method that is invoked when a user clicks on this square.
	 * This defines the end point for the search.
	 *
	 */
	public void leftClicked()
	{
		reset(0);								// Reset all values
		this.setTarget();						// Sets target for current square
		this.setHighlight(true);	// Highlight the new source square

	}

	/**
	 * A method that is invoked when a user clicks on this square.
	 * This defines the start point for the search.
	 */

	// Right click starts computation to check for available paths
	public void rightClicked()
	{
		targetSq = findTarget();
		currentSq = this;

		// If findTarget() returns the target, set source and begin search
		if(targetSq != null && targetSq.target){
			System.out.println("Source has been set at: (" + currentSq.getXLocation() + "," + currentSq.getYLocation() + ").\n");

			// Create PathFinder Object and call search
			PathFinder find = new PathFinder(currentSq, targetSq, board);
			find.doSearch();

		} else {
			// If there's no target, remind user
			System.out.println("Please enter a target first.\n");
			
		}
	}

	/**
	 * A method that is invoked when a reset() method is called on GameBoard.
	 *
	 * @param n An unspecified value that matches that provided in the call to GameBoard reset()
	 */
	// Reset restarts all values to each square of the board
	public void reset(int n)
	{
		// For loop for resetting each squares' properties
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				currentSq = (MazeSquare) board.getSquareAt(i,j);		// Assigns square at (i,j) to current
				currentSq.visited = false;								// Set visited to false
				currentSq.target = false;								// Set target to false
				currentSq.setHighlight(false);				// Clear highlight
			}
		}
	}

	// Method to find a target by checking the entire board
	public MazeSquare findTarget(){
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				currentSq = (MazeSquare) board.getSquareAt(i,j);		// Assigns square at (i,j) to current
				
				if(currentSq.target){
					return currentSq;
				}
			}
		}

		// If there's no target, tell user to set target first and reset previous path search
		reset(0);
		return null;
	}

	// Setter visited squares
	public void setVisited(){
		visited = true;
	}

	// Setter to "unvisit" a square
	public void setUnvisited(){
		visited = false;
	}

	// Checks if square has been visited
	public boolean isVisited(){
		return visited;
	}

	// Setter for target
	public void setTarget(){
		// Sets clicked square as target
		this.target = true;

		// Assigns square as target position
		targetSq = this;
		System.out.println("Target has been set at: (" + targetSq.getXLocation() + "," + targetSq.getYLocation() + ").");
	}
}
