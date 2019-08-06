package swen225.cluedo.pieces;

/**
 * 
 * @author Ellisjord
 *
 */
public class WeaponPiece extends Piece{

	private int row;
	private int col;

	public WeaponPiece (String name) {
		super(name);
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int move(int x, int y) {
		this.row = y;
		this.col = x;
		return 0;
	}

}
