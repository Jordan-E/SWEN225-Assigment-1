package swen225.cluedo.cards;

import swen225.cluedo.Room;

/**
 * Class for Room card
 * 
 * @author elmes
 *
 */
public class RoomCard implements Card{
	
	String room;
	
	public RoomCard(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return room.toString();
	}
	
	/**
	 * Determine if this card represents the provided room
	 * @param room
	 * @return
	 */
	public boolean represents(String room) {
		return this.room.equals(room);
	}
}
