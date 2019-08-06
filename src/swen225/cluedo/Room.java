package swen225.cluedo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import swen225.cluedo.pieces.Piece;

/**
 * 
 * @author elmes
 *
 */
public class Room {
	private Set<Piece> pieces;
	private String name;
	
	public Room(String name) {
		this.name = name;
		pieces = new HashSet<>();
	}
	
	public void addPiece(Piece piece) {
		pieces.add(piece);
	}

	public boolean removePiece(Piece piece) {
		return pieces.remove(piece);
	}
	
	public boolean contains(Piece piece) {
		return pieces.contains(piece);
	}
	
	public Set<Piece> getPieces() {
		return pieces;
	}
	
	public String getName() {return name;}
}
