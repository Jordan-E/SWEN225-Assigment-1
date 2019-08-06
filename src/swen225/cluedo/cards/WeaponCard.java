package swen225.cluedo.cards;

/**
 * class for weapon card
 * 
 * @author elmes
 *
 */
public class WeaponCard implements Card{

	String name;
	
	public WeaponCard(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Determine if this card represents the provided weapon
	 * @param weapon
	 * @return
	 */
	public boolean represents(String weapon) {
		return name.equals(weapon);
	}
}
