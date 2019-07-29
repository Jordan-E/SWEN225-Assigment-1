package swen225.cluedo;

import swen225.cluedo.Board.Room;

/**
 * 
 * @author elmes
 */
public class Cell {
	private final CellType cellType;
	private final Room room;
	private final String letterValue;
	
	private enum CellType {ROOM, HALLWAY, OUT_OF_BOUNDS}

	public Cell(String letterValue) {
		this.letterValue = letterValue;
		
		if(letterValue.equals("-")) {
			cellType = CellType.OUT_OF_BOUNDS;
			room = null;
		}
		else if(letterValue.equals(" ")) {
			cellType = CellType.HALLWAY;
			room = null;
		}
		else {//must be a letter 
			cellType = CellType.ROOM;
			
			if(letterValue.toLowerCase().equals("k")) room = Room.Kitchen;
			else if(letterValue.toLowerCase().equals("b")) room = Room.BallRoom;
			else if(letterValue.toLowerCase().equals("c")) room = Room.Conservatory;
			else if(letterValue.toLowerCase().equals("i")) room = Room.BilliardRoom;
			else if(letterValue.toLowerCase().equals("l")) room = Room.Library;
			else if(letterValue.toLowerCase().equals("s")) room = Room.Study;
			else if(letterValue.toLowerCase().equals("h")) room = Room.Hall;
			else if(letterValue.toLowerCase().equals("o")) room = Room.Lounge;
			else if(letterValue.toLowerCase().equals("d")) room = Room.DiningRoom;
			else throw new Error("Incorrect letter in board data. Incorrect letter: " + letterValue);			
		}
	}
	
	public boolean isRoom() {
		return room != null;
	}
	
	public CellType getCellType() {
		return cellType;
	}

	public Room getRoom() {
		return room;
	}
	
	@Override
	public String toString() {
		return letterValue;
	}
}

