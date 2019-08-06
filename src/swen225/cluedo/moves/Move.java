package swen225.cluedo.moves;

import swen225.cluedo.Board;
import swen225.cluedo.User;

public abstract class Move {
	
	private User user;
	private String invalidMessage = "";
	
	public Move (User user) {
		this.user = user;
	}
	
	public User getUser() {return user;}
	
	public String getInvalidMessage() {return invalidMessage;}
	
	public void setInvalidMessage(String s) {invalidMessage = s;}

	public abstract boolean isValid(Board board);
	
	/**
	 * 
	 * @return whether move was successfully applied
	 */
	public abstract boolean apply(Board board);

}
