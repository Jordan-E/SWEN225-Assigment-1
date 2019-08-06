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
		
		boolean startInRoom;
		for (int i = 0; i < steps.size(); i++) {
			if (!canMove(steps.get(i), board)) {
				return false;
			}
		}
		//checks that all steps are used unless piece is in a room
		int col = characterPiece.getX();
		int row = characterPiece.getY();
		if(!(stepCount == numSteps) && (!board.getBoard()[row][col].isRoom())) { //checks all moves have been moved unless in a room
			setInvalidMessage("Error: All steps not used");
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
		Cell originCell = board.getCell(row, col);
		
		
		if (stepCount > numSteps) { //not enough steps left to make the move.
			setInvalidMessage("Error: Not all moves used");
			return false;
		}
		
		for (int i = 0; i < count; i++) {
			if(board.getCellDirection(direction, row, col)==null) { //player has gone off the board
				setInvalidMessage("Error: Cannot move off the board");
				return false;
			}
			
			//checks that the next cell is a valid cell for a player to walk on
			if(!(board.getCellDirection(direction, row, col).isHallway() || board.getCellDirection(direction, row, col).isRoom())) {
				setInvalidMessage("Error: Tried to walk on invalid cell");
				return false;
			}
			if(board.getCellDirection(direction, row, col).isOccupied() && !board.getCell(row, col).isRoom()) { //check to stop going through other players
				setInvalidMessage("Error: Player in the way");
				return false;
			} 
			
			//checks that player piece doesn't go through room wall. (From hallway to room thats not doorway)
			if(board.getCell(row, col).isHallway() && board.getCellDirection(direction, row, col).isRoom() && !board.getCellDirection(direction, row, col).isDoorway()) {
				setInvalidMessage("Error: Player cant move through wall");
				return false;
			} 
			//checks that player isn't leaving through a wall
			if(board.getCell(row, col).isRoom() && !board.getCell(row, col).isDoorway() && board.getCellDirection(direction, row, col).isHallway()) {
				setInvalidMessage("Error: Leave the room through the doorway");
				return false;
			}
			
			if(board.getCellDirection(direction, row, col).isRoom()) {stepCount--;} //moving in a room doesn't take up steps
			
			BoardPosition currentPos = new BoardPosition(row, col);
			for(BoardPosition cell: visitedCells) { //Stop playerPiece going on the same square twice
				if(cell.equals(currentPos)) { 
					setInvalidMessage("Error: Cannot revisit the same square in the same move");
					return false;
					
				}
			}
			visitedCells.add(currentPos);
			
			//keeps track if the piece is in the doorway
			if(board.getCellDirection(direction, row, col).isDoorway()) {characterPiece.setInDoorway(true);}
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
		originCell.setOccupied(false); //set starting cell to unoccupied
		board.getCell(row, col).setOccupied(true); //set final cell to occupied
		return true;
	}

	@Override
	public boolean apply(Board board) {
		int col = characterPiece.getX();
		int row = characterPiece.getY();
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