package swen225.cluedo.cards;

/**
 * 
 * Class for each different player in the game
 * 
 * @author Ellisjord
 *
 */
public class CharacterCard implements Card{
	String description; 
	
	public CharacterCard(String description) {
		this.description = description;
	}
	
	public String cardType() {
		return "character";
	}

	public String returnItem() {
		return description;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
