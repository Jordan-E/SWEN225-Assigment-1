package swen225.cluedo.moves;

import swen225.cluedo.User;
import swen225.cluedo.pieces.*;

/**
 * Move for placing pieces in room to make a guess
 */

public class GuessMove extends Move{

	private final CharacterPiece character;
	private final WeaponPiece weapon;
	
	public GuessMove(User user, CharacterPiece character, WeaponPiece weapon) {
		super(user);
		this.character = character;
		this.weapon = weapon;
	}

	@Override
	public String invalidMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
