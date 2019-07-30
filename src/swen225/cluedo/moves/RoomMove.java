package swen225.cluedo.moves;

import swen225.cluedo.Board;
import swen225.cluedo.User;

/**
 * Move to "teleport" into a room that is within range
 */

public class RoomMove extends Move{

	private Board.Room room;
	
	public RoomMove(User user, Board.Room room) {
		super(user);
		this.room = room;
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

	@Override
	public boolean apply() {
		// TODO Auto-generated method stub
		return false;
	}

}
