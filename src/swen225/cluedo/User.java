package swen225.cluedo;

import java.util.HashSet;
import java.util.Set;
import swen225.cluedo.cards.Card;

/**
 * User is the class for each player. 
 * 
 * @author Ellisjord
 *
 */
public class User {
	 
	Set<Card> hand;
	
	public User() {
		hand = new HashSet<Card>();
	}
	
	/**
	 * adds card to users hand / maybe do in constructor
	 */
	public void addCardToHand(Set<Card> cards) {}
}
