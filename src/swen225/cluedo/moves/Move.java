package swen225.cluedo.moves;

import swen225.cluedo.User;

public abstract class Move {
	
	private User user;
	
	public Move (User user) {
		this.user = user;
	}
	
	public abstract User getUser();
	
	public abstract String invalidMessage();

	public abstract boolean isValid();
}