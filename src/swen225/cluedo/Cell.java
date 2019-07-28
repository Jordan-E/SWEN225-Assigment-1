package swen225.cluedo;

import swen225.cluedo.Board.Room;

/**
 * 
 * @author elmes
 */
public class Cell {
	boolean isRoom;
	private CellType cellType;
	private Room room;
	private String letterValue;
	
	@Override
	public String toString() {
		return letterValue;
	}


	public Cell(String cellType, String letterValue) {
		this.cellType = CellType.valueOf(cellType);
		this.letterValue = letterValue;
	}


	public void setRoom(Room room) {
		this.room = room;
	}
	
	public CellType getCellType() {
		return cellType;
	}


	public Room getRoom() {
		return room;
	}

	
	
}

