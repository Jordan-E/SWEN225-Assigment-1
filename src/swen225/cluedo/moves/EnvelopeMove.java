package swen225.cluedo.moves;

import swen225.cluedo.Board;
import swen225.cluedo.Room;
import swen225.cluedo.User;

/**
 * Move for guessing what is in the envelope
 */

public class EnvelopeMove extends Move {
	
	private final String character;
	private final String weapon;
	private final Room room;

	public EnvelopeMove(User user, String character, String weapon, Room room) {
		super(user);
		this.character = character;
		this.weapon = weapon;
		this.room = room;
	}

	@Override
	public boolean isValid(Board board) {
		return true;
	}

	public String getCharacter() {
		return character;
	}

	public String getWeapon() {
		return weapon;
	}

	public Room getRoom() {
		return room;
	}

	@Override
	public boolean apply(Board board) {
		return true;
	}
}

