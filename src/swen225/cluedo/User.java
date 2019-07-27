package swen225.cluedo;

import java.util.HashSet;
import java.util.Set;
import swen225.cluedo.cards.Card;
import swen225.cluedo.pieces.CharacterPiece;

/**
 * User is the class for each player. 
 * 
 * @author elmes
 *
 */
public class User {
	 
	private Set<Card> hand;
	private String name;
	private CharacterPiece character;
	
	
	public User(CharacterPiece character) {
		this.character = character;
		name = character.getName();
		hand = new HashSet<Card>();
	}
	
	/**
	 * adds card to users hand / maybe do in constructor
	 */
	public void addCard(Card card) {hand.add(card);}
	
	public String getName() {return name;}
}
