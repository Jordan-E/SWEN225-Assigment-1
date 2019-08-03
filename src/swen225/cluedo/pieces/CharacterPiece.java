package swen225.cluedo.pieces;

/**
 * 
 * @author Ellisjord
 *
 */
public class CharacterPiece extends Piece{
	private int col;
	private int row;
	public CharacterPiece(String name) {
		super(name);
		if(name == "Miss Scarlett") {col = 24; row = 7;}
		else if (name == "Colonel Mustard") {col = 17; row = 0;}
		else if (name == "Mrs White") {col = 0; row = 9;}
		else if (name == "Mr Green") {col = 0; row = 14;}
		else if (name == "Mrs Peacock") {col = 6; row = 23;}
		else if (name == "Professor Plum") {col = 19; row = 23;}
		else {throw new Error("Character Piece doesnt exsist");}
	}
	
	
	
	@Override
	public int getX() {
		return col;
	}

	@Override
	public int getY() {
		return row;
	}

	@Override
	public int move(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

