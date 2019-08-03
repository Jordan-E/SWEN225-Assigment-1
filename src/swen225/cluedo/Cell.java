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
	private boolean isDoorway = false;
	private boolean isOccupied = false;
	
	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

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
			
			if(letterValue.equalsIgnoreCase("k")) room = Room.Kitchen;
			else if(letterValue.equalsIgnoreCase("b")) room = Room.BallRoom;
			else if(letterValue.equalsIgnoreCase("c")) room = Room.Conservatory;
			else if(letterValue.equalsIgnoreCase("i")) room = Room.BilliardRoom;
			else if(letterValue.equalsIgnoreCase("l")) room = Room.Library;
			else if(letterValue.equalsIgnoreCase("s")) room = Room.Study;
			else if(letterValue.equalsIgnoreCase("h")) room = Room.Hall;
			else if(letterValue.equalsIgnoreCase("o")) room = Room.Lounge;
			else if(letterValue.equalsIgnoreCase("d")) room = Room.DiningRoom;
			else throw new Error("Incorrect letter in board data. Incorrect letter: " + letterValue);	
			
			if (Character.isUpperCase(letterValue.charAt(0))) isDoorway = true;
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

