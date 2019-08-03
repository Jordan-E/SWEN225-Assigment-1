package swen225.cluedo.moves;

import swen225.cluedo.Board;
import swen225.cluedo.User;

/**
 * Move for guessing what is in the envelope
 */

public class EnvelopeMove extends Move {
	
	private final String character;
	private final String weapon;
	private final Board.Room room;

	public EnvelopeMove(User user, String character, String weapon, Board.Room room) {
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
		// TODO Auto-generated method stub
		return false;
	}

	public String getCharacter() {
		return character;
	}

	public String getWeapon() {
		return weapon;
	}

	public Board.Room getRoom() {
		return room;
	}

	@Override
	public boolean apply() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
