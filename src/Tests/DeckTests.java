package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import swen225.cluedo.cards.Card;
import swen225.cluedo.cards.Deck;

class DeckTests {

	/** check isEmpty works */
	@Test void test0() {	
		Deck deck = new Deck();
		assert(deck.isEmpty());
	}
	
	/** check randomCard removes card */
	@Test void test1() {	
		Deck deck = new Deck();
		for (int i = 0; i < 21; i++) {
			deck.randomCard();
		}
		assert(deck.isEmpty());
	}
	
	/** tests that get envelope returns 3 cards that include a weapon, room and person */
	@Test void test2() {	
		Deck deck = new Deck();
		List<Card> envelope = new ArrayList<>(deck.getEnvelopeContents());
		assertFalse(envelope.get(0).cardType() == envelope.get(1).cardType());
		assertFalse(envelope.get(0).cardType() == envelope.get(2).cardType());
		assertFalse(envelope.get(1).cardType() == envelope.get(2).cardType());
	}
	
}