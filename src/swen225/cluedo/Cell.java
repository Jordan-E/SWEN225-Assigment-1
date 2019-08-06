package swen225.cluedo;

import java.util.List;

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
	
	private enum CellType {ROOM, HALLWAY, OUT_OF_BOUNDS}

	/**
	 * Constructor takes letterValue and uses it to determine details about room
	 * @param letterValue
	 */
	public Cell(String letterValue, List<Room> rooms) {
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
			this.room = getRoom(letterValue, rooms);			
			if (Character.isUpperCase(letterValue.charAt(0))) isDoorway = true;
		}
	}
	
	
	// ---- Getters and Setters ---- //
	
	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	public boolean isDoorway() {
		return isDoorway;
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

	public boolean isHallway() {
		return cellType == CellType.HALLWAY;
	}
	
	
	
	// ---- HELPERS ---- //
	
	private Room getRoom(String letterValue, List<Room> rooms) {
		if(letterValue.equalsIgnoreCase("k")) return roomMatching("ki", rooms);
		else if(letterValue.equalsIgnoreCase("b")) return roomMatching("ba", rooms);
		else if(letterValue.equalsIgnoreCase("c")) return roomMatching("co", rooms);
		else if(letterValue.equalsIgnoreCase("i")) return roomMatching("bi", rooms);
		else if(letterValue.equalsIgnoreCase("l")) return roomMatching("li", rooms);
		else if(letterValue.equalsIgnoreCase("s")) return roomMatching("st", rooms);
		else if(letterValue.equalsIgnoreCase("h")) return roomMatching("ha", rooms);
		else if(letterValue.equalsIgnoreCase("o")) return roomMatching("lo", rooms);
		else if(letterValue.equalsIgnoreCase("d")) return roomMatching("di", rooms);
		
		throw new IllegalArgumentException("Incorrect letter in board data. Incorrect letter: " + letterValue);	
	}
	
	private Room roomMatching(String id, List<Room> rooms) {
		for (Room r : rooms) {
			if(r.getName().substring(0,2).equalsIgnoreCase(id)) return r;
		}
		throw new IllegalArgumentException("Incorrect letter in board data. Incorrect letter: " + letterValue);	
	}
}

