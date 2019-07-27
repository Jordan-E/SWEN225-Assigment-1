package swen225.cluedo.moves;

import swen225.cluedo.User;

/**
 * Move where the user decides which cells to move through
 */

public class CustomMove extends Move{
	
	private int numMoves;

	public CustomMove(User user, int numMoves) {
		super(user);
		this.numMoves = numMoves;
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
