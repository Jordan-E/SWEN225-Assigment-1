package swen225.cluedo;

/**
 * 
 * @author Ellisjord
 */
public class Board {
	private Cell[][] board;
	
	public Board(int row, int col) {
		board = new Cell[row][col];
		if (row != 25 || col != 24) System.out.println("Board dimensions not supported");	
	}
	
	/**
	 * move player
	 */
	public void move(int x, int y) {}
	
	/**
	 * print out the board as text into the output.
	 */
	public void printBoard() {}

	/**
	 * checks where a cell is clear for a player to move into
	 * 
	 * @return whether a player can move into this cell
	 */
	public boolean canMove() {return false;}
}
