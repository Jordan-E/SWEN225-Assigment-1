package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import swen225.cluedo.cards.Card;
import swen225.cluedo.cards.Deck;

class DeckTests {

	@Test
	void test0() {	//check isEmpty works
		Deck deck = new Deck();
		assert(deck.isEmpty());
	}
	
	@Test
	void test1() {	//check randomCard removes card 
		Deck deck = new Deck();
		for (int i = 0; i < 21; i++) {
			deck.randomCard();
		}
		assert(deck.isEmpty());
	}
	
	@Test
	void test2() {	//check isEmpty works
		Deck deck = new Deck();
		Set<Card> envelope = new HashSet<>();  
		envelope.addAll(deck.getEnvelopeContents());
	}
	
}
