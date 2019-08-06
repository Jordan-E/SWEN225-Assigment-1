package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import swen225.cluedo.Envelope;
import swen225.cluedo.cards.CharacterCard;
import swen225.cluedo.cards.Deck;
import swen225.cluedo.cards.RoomCard;
import swen225.cluedo.cards.WeaponCard;

class EnvelopeTests {

	private static final String[] names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green", "Mrs Peacock", "Professor Plum"};
	private static final String[] weapons = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	
	/** checks getCharacter returns a return card */
	@Test void test0() { 
		Deck deck = new Deck(names, weapons);
		Envelope envelope = new Envelope(deck.getEnvelopeContents());
		assert(envelope.getCharacter()!=null);
	}

	/** checks getRoom returns a return card */
	@Test void test1() { 
		Deck deck = new Deck(names, weapons);
		Envelope envelope = new Envelope(deck.getEnvelopeContents());
		assert(envelope.getRoom()!=null);
	}
	
	/** checks getWeapon returns a return card */
	@Test void test2() {
		Deck deck = new Deck(names, weapons);
		Envelope envelope = new Envelope(deck.getEnvelopeContents());
		assert(envelope.getWeapon()!=null);
	}
}
