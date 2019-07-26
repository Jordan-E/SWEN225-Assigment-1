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
	
	public WeaponCard getWeapon() {
		//TODO
		return null;
	}
	
	public CharacterCard getCharacter() {
		//TODO
		return null;
	}
	
	public RoomCard getRoom() {
		//TODO
		return null;
	}
}
