package swen225.cluedo.cards;

import swen225.cluedo.Board;
import swen225.cluedo.Board.Room;

/**
 * Card for each room in the game of cluedo
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
