package swen225.cluedo.pieces;

/**
 * 
 * @author Ellisjord
 *
 */
public interface Piece {
	int getX();
	
	int getY();
	
	int move(int x, int y);
}

