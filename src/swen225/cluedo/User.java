package swen225.cluedo;

import java.util.HashSet;
import java.util.Set;
import swen225.cluedo.cards.Card;

/**
 * User is the class for each player. 
 * 
 * @author elmes
 *
 */
public class User {
	 
	Set<Card> hand;
	String name;

	
	public User(String name) {
		this.name = name;
		hand = new HashSet<Card>();
		
	}
	
	/**
	 * adds card to users hand / maybe do in constructor
	 */
	public void addCard(Card card) {hand.add(card);}
}
