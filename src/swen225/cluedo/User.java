package swen225.cluedo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import swen225.cluedo.Board.Room;
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
	private Set<Card> seen;
	private String name;
	private CharacterPiece character;
	
	public User(CharacterPiece character) {
		this.character = character;
		name = character.getName();
		hand = new HashSet<>();
		seen = new HashSet<>();
	}
	
	/**
	 * Checks inputs against cards to see if any of the user's cards
	 * represent one of the inputs
	 * 
	 * @param character
	 * @param weapon
	 * @param room
	 * @return List of cards that the user has out of inputs
	 */
	public List<Card> hasCards(String character, String weapon, Room room) {
		List<Card> cards = new ArrayList<>();
		
		for (Card c : hand) {
			if (c.represents(character) || c.represents(weapon) 
					|| c.represents(room)) cards.add(c);
		}
		
		return cards;
	}
	
	/**
	 * adds card to user's hand
	 * @param card
	 */
	public void addCardToHand(Card card) {hand.add(card); seen.add(card);}
	
	/**
	 * Adds card to user's seen cards
	 * @param card
	 */
	public void addCardToSeen(Card card) {seen.add(card);}
	
	
	// --- Getters --- //
	
	public String getName() {return name;}
	
	public CharacterPiece getCharacterPiece() {
		return character;
	}
	
	public String getHand() {
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (Card c : hand) {
			if (!first) sb.append(", ");
			else first = false;
			sb.append(c.toString());
		}
		
		return sb.toString();
	}
	
	public String getSeen() {
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (Card c : seen) {
			if (!first) sb.append(", ");
			else first = false;
			sb.append(c.toString());
		}
		
		return sb.toString();
	}
}
