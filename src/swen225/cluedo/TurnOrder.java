package swen225.cluedo;

import java.util.List;
import java.util.ArrayList;


/**
 * 
 * @author elmes
 */
public class TurnOrder{
	
	private List<User> list = new ArrayList<User>();
	private int pointer = 0;
	
	public TurnOrder() {}
	
	public void addUser(User user) {list.add(user);}
	
	/**
	 * Reset order to default order
	 */
	public void resetOrder() {
		pointer = 0;
	}
	
	/**
	 * The current turns associated user
	 * @return The user who's turn it is
	 */
	public User currentUser() {
		return list.get(pointer);
	}
	
	/**
	 * End the turn of the current user.
	 */
	public void endTurn() {
		incrementPointer();
	}
	
	private void incrementPointer() {
		if (pointer < list.size() - 1) pointer ++;
		else pointer = 0;
	}
}
