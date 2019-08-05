package swen225.cluedo.moves;

import swen225.cluedo.Board;
import swen225.cluedo.User;
import swen225.cluedo.pieces.*;

/**
 * Move for placing pieces in room to make a guess
 */

public class GuessMove extends Move{

	private final CharacterPiece character;
	private final WeaponPiece weapon;
	private final Board.Room room;
	
	public GuessMove(User user, CharacterPiece character, WeaponPiece weapon, Board.Room room) {
		super(user);
		this.character = character;
		this.weapon = weapon;
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

	public CharacterPiece getCharacter() {
		return character;
	}

	public WeaponPiece getWeapon() {
		return weapon;
	}
	
	public Board.Room getRoom() {
		return room;
	}

	@Override
	public boolean apply(Board board) {
		// TODO Auto-generated method stub
		return false;
	}
}

