package swen225.cluedo.pieces;

/**
 * 
 * @author Ellisjord
 *
 */
public class CharacterPiece extends Piece{
	private int col;
	private int row;
	private final int identifyingNum;
	public CharacterPiece(String name) {
		super(name);
		if(name == "Miss Scarlett") {col = 7; row = 24; identifyingNum = 1;}
		else if (name == "Colonel Mustard") {col = 0; row = 17;identifyingNum = 2;}
		else if (name == "Mrs White") {col = 9; row = 0;identifyingNum = 3;}
		else if (name == "Mr Green") {col = 14; row = 0;identifyingNum = 4;}
		else if (name == "Mrs Peacock") {col = 23; row = 6;identifyingNum = 5;}
		else if (name == "Professor Plum") {col = 23; row = 19;identifyingNum = 6;}
		else {throw new Error("Character Piece doesnt exsist");}
	}
	
	
	
	public int getIdentifyingNum() {
		return identifyingNum;
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
		this.row = y;
		this.col = x;
		return 0;
	}
	
}

