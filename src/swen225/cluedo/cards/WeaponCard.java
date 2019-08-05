package swen225.cluedo.cards;

/**
 * Card for each weapon in Cluedo
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
	public boolean represents(Object weapon) {
		if (weapon instanceof String) return name.equals((String) weapon);
		return false;
	}
}
