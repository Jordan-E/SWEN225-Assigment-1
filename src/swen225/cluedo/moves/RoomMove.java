package swen225.cluedo.moves;

import swen225.cluedo.Board;
import swen225.cluedo.User;
import swen225.cluedo.pieces.CharacterPiece;

/**
 * Move to "teleport" into a room that is within range
 */

public class RoomMove extends Move{

	private Board.Room room;
	private CharacterPiece character;
	
	public RoomMove(User user, Board.Room room) {
		super(user);
		this.character = user.getCharacterPiece(); 
		this.room = room;
	}
	
	@Override
	public String invalidMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid(Board board) {
		int col = character.getX();
		int row = character.getY();
		if(!board.getCell(row, col).isRoom()) { //must be in a room to make a guessMove
			System.out.println("Must be in a room to make a guess");
			return false;
		} 
		
		return true;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean apply(Board board) {
		// TODO Auto-generated method stub
		return false;
	}

}
