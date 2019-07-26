package swen225.cluedo.cards;

import java.awt.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * 
 * Holds all the cards in the game of cluedo
 * 
 * @author Ellisjord
 *
 */
public class Deck {
	
	ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
	}
	
	/**
	 * Select a random card and removes it from deck
	 * @return random card from deck
	 */
	public Card randomCard() {
		int pos = (int) (Math.random() * deck.size());
		return deck.remove(pos);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {return deck.size() == 0;}

	/**
	 * Select at random one weapon, one character and one room card
	 * @return player, weapon, and room cards in a Set
	 */
	public Set<Card> getEnvelopeContents() {
		// TODO Auto-generated method stub
		return null;
	}
}
