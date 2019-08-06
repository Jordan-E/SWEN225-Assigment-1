package swen225.cluedo.cards;

/**
 * 
 * Class for character card in the game
 * 
 * @author elmes
 *
 */
public class CharacterCard implements Card{
	
	String name; 
	
	public CharacterCard(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Determine if this card represents the provided character
	 * @param character
	 * @return
	 */
	public boolean represents(String name) {
		return this.name.equals(name);
	}

}
