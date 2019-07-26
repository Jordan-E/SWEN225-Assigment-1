package swen225.cluedo;

import java.util.Set;

import swen225.cluedo.cards.*;

/**
 * 
 * @author elmes
 *
 */
public class Envelope {
	
	Set<Card> cards;
	
	public Envelope(Set<Card> cards) {
		this.cards = cards;
	}

	public Set<Card> getCards() {
		return cards;
	}
	
	public Card getWeapon() {
		for (Card card : cards) {
			if(card instanceof WeaponCard) return card;
		}
		return null;
	}
	
	public Card getCharacter() {
		for (Card card : cards) {
			if(card instanceof CharacterCard) return card;
		}
		return null;
	}
	
	public Card getRoom() {
		for (Card card : cards) {
			if(card instanceof RoomCard) return card;
		}
		return null;
	}
}
