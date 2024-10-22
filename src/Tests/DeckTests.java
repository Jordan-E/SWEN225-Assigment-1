package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import swen225.cluedo.cards.Card;
import swen225.cluedo.cards.Deck;

class DeckTests {

	private static final String[] names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green", "Mrs Peacock", "Professor Plum"};
	private static final String[] weapons = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	private static final String[] rooms = {"Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
	
	/** check randomCard removes card */
	@Test void test1() {	
		Deck deck = new Deck(names, weapons, rooms);
		for (int i = 0; i < 21; i++) {
			deck.randomCard();
		}
		assert(deck.isEmpty());
	}
	
	/** tests that get envelope returns 3 cards that include a weapon, room and person */
	@Test void test2() {	
		Deck deck = new Deck(names, weapons, rooms);
		List<Card> envelope = new ArrayList<>(deck.getEnvelopeContents());
		assertFalse(envelope.get(0).getClass() == envelope.get(1).getClass());
		assertFalse(envelope.get(0).getClass() == envelope.get(2).getClass());
		assertFalse(envelope.get(1).getClass() == envelope.get(2).getClass());
	}
	
}
