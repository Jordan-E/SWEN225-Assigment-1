package swen225.cluedo.cards;

/**
 * 
 * Interface holding all the different found cards in cluedo
 * 
 * @author Ellisjord
 *
 */
public interface Card {
	/**
	 * @return returns if the card is a weapon, Character or Room.
	 */
	public String cardType();
	
	/**
	 * @return returns what the card description is.
	 */
	public String returnItem();
	
}
