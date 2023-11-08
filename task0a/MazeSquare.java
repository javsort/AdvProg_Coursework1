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
		// Call print method to fulfill task0a
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

	// Task 0a
	public void print(){
		MazeSquare clickedSquare = this;
		System.out.println("Coordinates (x,y): (" + clickedSquare.getXLocation() + ", " + clickedSquare.getYLocation() + ").\n");

	}
}