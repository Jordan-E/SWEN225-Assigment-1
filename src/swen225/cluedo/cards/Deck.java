package swen225.cluedo.cards;

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
	
	public Deck(String[] names, String[] weapons, String[] rooms) {
		deck = new ArrayList<Card>();
		
		for (String name : names) {deck.add(new CharacterCard(name));}
		for (String weapon : weapons) {deck.add(new WeaponCard(weapon));}
		for (String room : rooms) {deck.add(new RoomCard(room));}
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
