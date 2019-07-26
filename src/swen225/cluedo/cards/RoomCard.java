package swen225.cluedo.cards;

/**
 * Card for each room in the game of cluedo
 * 
 * @author Ellisjord
 *
 */
public class RoomCard implements Card{
	String description;
	public RoomCard(String description) {
		this.description = description;
	}

	public String cardType() {
		return "room";
	}


	public String returnItem() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}
	

}
