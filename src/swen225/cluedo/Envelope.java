package swen225.cluedo;

import java.util.Set;

import swen225.cluedo.cards.*;
import swen225.cluedo.moves.EnvelopeMove;

/**
 * 
 * @author elmes
 *
 */
public class Envelope {
	
	private CharacterCard character;
	private WeaponCard weapon;
	private RoomCard room;
	
	public Envelope(CharacterCard character, WeaponCard weapon, RoomCard room) {
		this.character = character;
		this.weapon = weapon;
		this.room = room;
	}
	
	public Envelope(Set<Card> cards) {
		for (Card c : cards) {
			if (c instanceof CharacterCard) character = (CharacterCard) c;
			else if (c instanceof WeaponCard) weapon = (WeaponCard) c;
			else if (c instanceof RoomCard) room = (RoomCard) c;
		}
	}

	public CharacterCard getCharacter() {return character;}

	public WeaponCard getWeapon() {return weapon;}

	public RoomCard getRoom() {return room;}

	public boolean processGuess(EnvelopeMove move) {		
		if (!character.represents(move.getCharacter())) return false;
		if (!weapon.represents(move.getWeapon())) return false;
		if (!room.represents(move.getRoom())) return false;
		
		return true;
	}
}
