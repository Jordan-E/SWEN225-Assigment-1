package swen225.cluedo.pieces;

/**
 * 
 * @author Ellisjord
 *
 */
public abstract class Piece {
	
	private String name;
	
	public Piece(String name) {
		this.name = name;
	}
	
	public abstract int getX();
	
	public abstract int getY();
	
	public abstract int move(int x, int y);
	
	public String getName() {return name;}
	
	public String toString() {return name;}
}

