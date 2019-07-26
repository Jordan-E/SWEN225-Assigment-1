package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import swen225.cluedo.Envelope;
import swen225.cluedo.cards.CharacterCard;
import swen225.cluedo.cards.Deck;
import swen225.cluedo.cards.RoomCard;
import swen225.cluedo.cards.WeaponCard;

class EnvelopeTests {

	@Test
	void test0() { //checks getCharacter returns a return card
		Deck deck = new Deck();
		Envelope envelope = new Envelope(deck.getEnvelopeContents());
		assert(envelope.getCharacter()!=null);
	}

	@Test
	void test1() { //checks getRoom returns a return card
		Deck deck = new Deck();
		Envelope envelope = new Envelope(deck.getEnvelopeContents());
		assert(envelope.getRoom()!=null);
	}
	
	@Test
	void test2() { //checks getWeapon returns a return card
		Deck deck = new Deck();
		Envelope envelope = new Envelope(deck.getEnvelopeContents());
		assert(envelope.getWeapon()!=null);
	}
}
