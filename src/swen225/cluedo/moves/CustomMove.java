package swen225.cluedo.moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import swen225.cluedo.Board;
import swen225.cluedo.Cell;
import swen225.cluedo.Room;
import swen225.cluedo.User;
import swen225.cluedo.pieces.CharacterPiece;
import swen225.cluedo.pieces.Piece;

/**
 * Move where the user decides which cells to move through
 * 
 * @author Ellisjord
 */

public class CustomMove extends Move{
	
	private int numSteps;
	private List<Step> steps;
	private CharacterPiece characterPiece;
	private int stepCount = 0;
	private List<BoardPosition> visitedCells;
	private int startRow;
	private int startCol;
	
	public CustomMove(User user, int numSteps, String input) {
		super(user);
		this.numSteps = numSteps;
		steps = processSteps(input);
		characterPiece = user.getCharacterPiece();
		visitedCells = new ArrayList<BoardPosition>();
		startCol = characterPiece.getX();
 		startRow = characterPiece.getY();	
	}
	
	private List<Step> processSteps(String input) {
		if (input == null) return null;
		
		
		input.toLowerCase();
		List<Step> steps = new ArrayList<>();
		Scanner sc = new Scanner(input);
		sc.useDelimiter(",");
		
		while (sc.hasNext()) {
			try {
				Step step = new Step(sc.next().trim()); //throws IllegalArgumentException if not correct format
				steps.add(step);
			} catch (IllegalArgumentException e) {
				sc.close();
				throw new IllegalArgumentException();
			}
		}
		
		sc.close();
		return steps;
	}

	/**
	 * Loops through all the moves and checks if the move is valid
	 */
	@Override
	public boolean isValid(Board board) {
		Room startRoom = board.getCell(characterPiece.getY(), characterPiece.getX()).getRoom();	
		int startCol = characterPiece.getX();
		int startRow = characterPiece.getY();
		
		
		for (int i = 0; i < steps.size(); i++) {
			if (!canMove(steps.get(i), board)) {
				characterPiece.move(startCol, startRow); //puts character back in original position

				return false;
			}
		}
		//checks that all steps are used unless piece is in a room
		int col = characterPiece.getX();
		int row = characterPiece.getY();
		if(!(stepCount == numSteps) && (!board.getBoard()[row][col].isRoom())) { //checks all moves have been moved unless in a room
			setInvalidMessage("Error: All steps not used (Moves inside a room do not count) Steps moved: " + stepCount + "/" + numSteps);
			
			return false;
		} 
		Room endRoom = board.getCell(characterPiece.getY(), characterPiece.getX()).getRoom();
		if(startRoom == endRoom && startRoom != null) {
			setInvalidMessage("Error: Must leave room");
			
			return false;
		}
		return true;
	
	}
	
	
	
	
	/**
	 * Steps through all the cells the playerPeice would have to move through and checks if they're valid
	 * @param steps
	 * @param board
	 * @return if the move is valid
	 */
	private boolean canMove(Step steps, Board board) {
		String direction = steps.getDir();
		int count = steps.getCount();
		stepCount += count;
		int col = characterPiece.getX();
		int row = characterPiece.getY();
		int lastRow = row;
		int lastCol = col;
		
		//checks if user is entering the corner doors in the right direction
		if(getCellDirection(direction, row, col, board).isDoorway()) { 
			System.out.println(getCellDirection(direction, row, col, board).toString());
			System.out.println(lastCol + ", "+ lastRow);
			System.out.println(!(lastCol  == 17 && lastRow == 20));
			if((getCellDirection(direction, row, col, board).toString() == "O")) {
				if(!(lastCol  == 6 && lastRow == 18)) {
					setInvalidMessage("Can't enter door from this direction");
					return false;
				}
			}else if(board.getCell(row, col).toString() == "S") {
				
				if(!(lastCol  == 17 && lastRow == 20)) { //not working and i have no idea why
					setInvalidMessage("Can't enter door from this direction"); 
					return false;
				}
				
			}
			else if(board.getCell(row, col).toString() == "C") { //needs to be checked
				if(!(lastCol  == 18 && lastRow == 5)) {
					setInvalidMessage("Can't enter door from this direction"); 
					return false;
				}
			}
		}
		
		if (stepCount > numSteps) { //not enough steps left to make the move.
			int movesOver = stepCount - numSteps;
			setInvalidMessage("Error: Not all moves used. " + movesOver +  " step(s) to many");
			return false;
		}
		
		for (int i = 0; i < count; i++) {
			if(getCellDirection(direction, row, col, board)==null) { //player has gone off the board
				setInvalidMessage("Error: Cannot move off the board");
				return false;
			}
			
			//checks that the next cell is a valid cell for a player to walk on
			if(!(getCellDirection(direction, row, col, board).isHallway() || getCellDirection(direction, row, col, board).isRoom())) {
				setInvalidMessage("Error: Tried to walk on invalid cell: " + getCellDirection(direction, row, col, board) + " At Row: "+  row + " Col: " + col);
				return false;
			}
			if(getCellDirection(direction, row, col, board).isOccupied() && !board.getCell(row, col).isRoom()) { //check to stop going through other players
				setInvalidMessage("Error: Player in the way" + " Row: " + row + " Col: " + col);
				return false;
			} 
			
			//checks that player piece doesn't go through room wall. (From hallway to room thats not doorway)
			if(board.getCell(row, col).isHallway() && getCellDirection(direction, row, col, board).isRoom() && !getCellDirection(direction, row, col, board).isDoorway()) {
				setInvalidMessage("Error: Player cant move through wall");
				return false;
			} 
			//checks that player isn't leaving through a wall
			if(board.getCell(row, col).isRoom() && !board.getCell(row, col).isDoorway() && getCellDirection(direction, row, col, board).isHallway()) {
				setInvalidMessage("Error: Leave the room through the doorway");
				return false;
			}
			
			
			
			if(getCellDirection(direction, row, col, board).isRoom()) {stepCount--;} //moving in a room doesn't take up steps
			
			BoardPosition currentPos = new BoardPosition(row, col);
			for(BoardPosition cell: visitedCells) { //Stop playerPiece going on the same square twice
				if(cell.equals(currentPos)) { 
					setInvalidMessage("Error: Cannot revisit the same square in the same move");
					return false;
										
				}
			}
			visitedCells.add(currentPos);
			
			//keeps track if the piece is in the doorway
			if(getCellDirection(direction, row, col, board).isDoorway()) {characterPiece.setInDoorway(true);}
			else {
				characterPiece.setInDoorway(false);
			}
			
			
		
			if(direction == "N") {row -= 1;}
			else if(direction == "S") {row += 1;}
			else if(direction == "E") {col += 1;}
			else if(direction == "W") {col -= 1;}
			else {throw new Error("Invalid direction custom move apply");}

		}
		

		characterPiece.move(col, row);

		return true;
	}
	
	/**
	 * gets cell one in direction selected
	 * @param direction
	 * @param row
	 * @param col
	 * @param board
	 * @return
	 */
	public Cell getCellDirection(String direction, int row, int col, Board board) {
		if (direction == "N") {
			if(row-1 < 0) {return null;} //checks for going off the board
			return board.getCell(row-1, col);			
		}else if(direction == "S") {
			if(row+1 > 25) {return null;}
			return board.getCell(row+1, col);
		}else if(direction == "W") {
			if(col-1 < 0) {return null;}
			return board.getCell(row, col-1);
		}else if(direction == "E") {
			if(col+1 > 24) {return null;}
			return board.getCell(row, col+1);
		}else {throw new Error("getCell incorrect input");}
	}

	@Override
	public boolean apply(Board board) {
		int col = characterPiece.getX();//set starting cell to unoccupied
		int row = characterPiece.getY();//set final cell to occupied
		board.getCell(startRow, startCol).setOccupied(false);
		board.getCell(row, col).setOccupied(true);
		characterPiece.move(col, row);
		
		if(board.getCell(startRow, startCol).isRoom()) { //removes player to a room. 
			Room room = board.getCell(startRow, startCol).getRoom();
			room.removePiece(characterPiece);
		}
		
		if(board.getCell(row, col).isRoom()) { //adds player to a room. 
			Room room = board.getCell(row, col).getRoom();
			room.addPiece(characterPiece);
		}
		
		return true;
	}

}

class BoardPosition {


	private int row;
	private int col;
	public BoardPosition(int row, int col){
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardPosition other = (BoardPosition) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}

class Step {
	
	private final Direction dir;
	private final int count;
	
	private enum Direction {N, E, S, W}
	
	public Step(String input) {
		try {
			dir = processDirection(input.substring(0,1));
			count = Integer.parseInt(input.substring(1));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}	
	
	public String getDir() {
		return dir.name();
	}

	public int getCount() {
		return count;
	}

	private Direction processDirection(String input) {
		Direction dir;
		
		if (input.equalsIgnoreCase("n")) dir = Direction.N;
		else if (input.equalsIgnoreCase("e")) dir = Direction.E;
		else if (input.equalsIgnoreCase("s")) dir = Direction.S;
		else if (input.equalsIgnoreCase("w")) dir = Direction.W;
		else throw new IllegalArgumentException();
		
		return dir;
		
	}
}