package swen225.cluedo.cards;

import swen225.cluedo.Board.Room;

/**
 * Class for Room card
 * 
 * @author elmes
 *
 */
public class RoomCard implements Card{
	
	Room room;
	
	public RoomCard(Room room) {
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
	public boolean represents(Object room) {
		if (room instanceof Room) return this.room.equals((Room) room);
		return false;
	}
}
