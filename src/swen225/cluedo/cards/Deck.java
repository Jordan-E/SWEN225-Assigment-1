package swen225.cluedo.cards;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 
 * Holds all the cards in the game of cluedo
 * 
 * @author Ellisjord
 *
 */
public class Deck {
	
	ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
		deck.add(new CharacterCard("Miss Scarlett"));
		deck.add(new CharacterCard("Colonel Mustard"));
		deck.add(new CharacterCard("Mrs. White"));
		deck.add(new CharacterCard("Mr. Green"));
		deck.add(new CharacterCard("Mrs. Peacock"));
		deck.add(new CharacterCard("Professor Plum"));
		
		deck.add(new WeaponCard("Candlestick"));
		deck.add(new WeaponCard("Dagger"));
		deck.add(new WeaponCard("Lead Pipe"));
		deck.add(new WeaponCard("Revolver"));
		deck.add(new WeaponCard("Rope"));
		deck.add(new WeaponCard("Spanner"));
		
		deck.add(new RoomCard("Kitchen"));
		deck.add(new RoomCard("Ball Room"));
		deck.add(new RoomCard("Conservatory"));
		deck.add(new RoomCard("Billard Room"));
		deck.add(new RoomCard("Libary"));
		deck.add(new RoomCard("Study"));
		deck.add(new RoomCard("Hall"));
		deck.add(new RoomCard("Dining Room"));
		deck.add(new RoomCard("Lounge"));
	}
	
	/**
	 * Select a random card and removes it from deck
	 * @return random card from deck
	 */
	public Card randomCard() {
		int pos = (int) (Math.random() * deck.size());
		return deck.remove(pos);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {return deck.size() == 0;}

	/**
	 * Select at random one weapon, one character and one room card and remove these cards
	 * @return player, weapon, and room cards in a Set
	 */
	public Set<Card> getEnvelopeContents() {
		Set<Card> envelope = new HashSet<>();
		envelope.add(getCardOfType(RoomCard.class));
		envelope.add(getCardOfType(CharacterCard.class));
		envelope.add(getCardOfType(WeaponCard.class));
		return envelope;
	}
	
	private Card getCardOfType(Class<? extends Card> cardType) {
		Random rand = new Random();
		int randNum;
		while(true) {
			randNum = rand.nextInt(deck.size());
			if(deck.get(randNum).getClass() == cardType) {break;}
		}
		return deck.remove(randNum);
	}
}
