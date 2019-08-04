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
	private String name;
	private CharacterPiece character;
	
	
	public User(CharacterPiece character) {
		this.character = character;
		name = character.getName();
		hand = new HashSet<Card>();
	}
	
	public List<Card> hasCards(String character, String weapon, Room room) {
		List<Card> cards = new ArrayList<>();
		
		for (Card c : hand) {
			if (c.represents(character)) cards.add(c);
		}
		
		return cards;
	}
	
	/**
	 * adds card to users hand
	 */
	public void addCard(Card card) {hand.add(card);}
	
	public String getName() {return name;}
	
	public CharacterPiece getCharacterPiece() {
		return character;
	}
}
