package swen225.cluedo;

import java.util.List;
import java.util.ArrayList;


/**
 * Use this class for storing turn order
 * 
 * @author elmes
 */
public class TurnOrder{
	
	private List<User> guessing = new ArrayList<User>();
	private List<User> order = new ArrayList<User>();
	private int pointer = 0;
	
	public TurnOrder() {}
	
	public void addUser(User user) {
		guessing.add(user);
		order.add(user);
	}
	
	/**
	 * Won't allow a user to guess in the future
	 * @return whether the user was removed successfully
	 */
	public boolean removeUser(User user) {
		if (currentUser().equals(user)) endTurn();
		if (!guessing.contains(user)) return false;
		guessing.remove(user);
		return true;
	}
	
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
		return guessing.get(pointer);
	}
	
	/**
	 * End the turn of the current user.
	 */
	public void endTurn() {
		incrementPointer();
	}
	
	/**
	 * Use this method for getting the order in which players should be asked 
	 * if they have a card that disproves given user's guess
	 * 
	 * @param guesser
	 * @return
	 */
	public List<User> responseOrder(User guesser) {
		List<User> list = new ArrayList<>();
		
		int start = order.indexOf(guesser);
		if (start == -1) return null;
		for (int i = start + 1; i < order.size(); i++) {list.add(order.get(i));}
		for (int i = 0; i < start; i++) {list.add(order.get(i));}
		
		return list;
	}
	
	/**
	 * Check there are still players that are allowed to play (game isn't over)
	 * @return
	 */
	public boolean playersLeft() {
		return guessing.size() > 0;
	}
	
	private void incrementPointer() {
		if (pointer < guessing.size() - 1) pointer++;
		else pointer = 0;
	}
}
