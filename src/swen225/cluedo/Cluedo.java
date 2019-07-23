package swen225.cluedo;

import java.util.Queue;
import java.util.Set;

import swen225.cluedo.cards.Card;
import swen225.cluedo.cards.Deck;

/**
 * 
 * Class for running the Cluedo game. 
 * 
 * @author Ellisjord
 *
 */
public class Cluedo {
	private Queue<User> turnQueue;
	private User activePlayer;
	private Deck deck;
	private Board board;
	
	public Cluedo() {
		board = new Board();
	}
	
	/**
	 * class to get a weapon, room and Character card to give to every user
	 */
	public Set<Card> getPlayerHand() {return null;}
	
	/**
	 * Gets the player who's turn is next and set them to active player.
	 */
	public void changeTurn() {}

}
