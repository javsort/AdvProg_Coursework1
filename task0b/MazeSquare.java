public class MazeSquare extends GameSquare
{
	private GameBoard board;			// A reference to the GameBoard this square is part of.
	private boolean target;				// true if this square is the target of the search.

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
		// Call method to fulfill task 0b
		print();
		this.target = true;
	}

	/**
	 * A method that is invoked when a user clicks on this square.
	 * This defines the start point for the search.
	 */
	public void rightClicked()
	{
	}

	/**
	 * A method that is invoked when a reset() method is called on GameBoard.
	 *
	 * @param n An unspecified value that matches that provided in the call to GameBoard reset()
	 */
	public void reset(int n)
	{

	}

	// Task 0b
	public void print(){
		MazeSquare current = this;

		System.out.println("Coordinates (x,y): (" + current.getXLocation() + ", " + current.getYLocation() + ").");
		
		// Check if there's a square on the right or not
		if(current.getXLocation() < 9){
			System.out.println("Right Coordinates (x,y): (" + (current.getXLocation() + 1) + ", " + current.getYLocation() + ").\n");
		} else {
			System.out.println("There's no right neighbor.\n");
		}
	}
}