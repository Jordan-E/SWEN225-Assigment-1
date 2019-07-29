package swen225.cluedo.cards;

/**
 * Card for each weapon in cluedo
 * 
 * @author Ellisjord
 *
 */
public class WeaponCard implements Card{

	String description;
	public WeaponCard(String description) {
		this.description = description;
	}

	public String cardType() {
		return "weapon";
	}


	public String returnItem() {
		return description;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
	/**
	 * Determine if this card represents the provided weapon
	 * @param weapon
	 * @return
	 */
	public boolean represents(String weapon) {
		//TODO
		return true; //placeholder
	}
}
