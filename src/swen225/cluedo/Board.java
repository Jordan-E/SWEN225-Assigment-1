package swen225.cluedo;

import java.util.Queue;


/**
 * 
 * Board is a class that holds a 24x25 2D array of the board. 
 * 
 * @author Ellisjord
 *
 */
public class Board {
	private Cell[][] board;

	
	public Board() {
		board = new Cell[24][25];
		
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
