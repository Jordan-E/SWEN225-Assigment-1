package swen225.cluedo.moves;

import java.awt.PageAttributes.OriginType;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import swen225.cluedo.Board;
import swen225.cluedo.Board.Room;
import swen225.cluedo.Cell;
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
	private List<Cell> visitedCells;
	private int startRow;
	private int startCol;
	

	
	
	public CustomMove(User user, int numSteps, String input) {
		super(user);
		this.numSteps = numSteps;
		steps = processSteps(input);
		characterPiece = user.getCharacterPiece();
		visitedCells = new ArrayList<Cell>();
		startCol = characterPiece.getX();
 		startRow = characterPiece.getY();
 		
		
	}
	
	private List<Step> processSteps(String input) {
		if (input == null) return null;
		
		
		input.toLowerCase();
		List<Step> steps = new ArrayList<>();
		Scanner sc = new Scanner(input).useDelimiter(",");
		
		while (sc.hasNext()) {
			try {
				Step step = new Step(sc.next().trim()); //throws InvalidParameterException if not correct format
				steps.add(step);
			} catch (InvalidParameterException e) {
				sc.close();
				throw new InvalidParameterException();
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
		for (int i = 0; i < steps.size(); i++) {
			if (!canMove(steps.get(i), board)) {
				return false;
			}
		}
		//checks that all steps are used unless piece is in a room
		int col = characterPiece.getX();
		int row = characterPiece.getY();
		if(!(stepCount == numSteps) && (!board.getBoard()[row][col].isRoom())) {System.out.println("All steps not used");return false;} //makes sure 
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
			return false;
		}
		
		for (int i = 0; i < count; i++) {
			if(board.getCellDirection(direction, row, col)==null) {return false;} //player has gone off the board
			//checks that the next cell is a valid cell for a player to walk on
			if(!(board.getCellDirection(direction, row, col).isHallway() || board.getCellDirection(direction, row, col).isRoom())) {System.out.println("1");return false;}
			if(board.getCellDirection(direction, row, col).isOccupied() && !board.getCell(row, col).isRoom()) {System.out.println("2");return false;} //check to stop going through other players
			
			//checks that player piece doesn't go through room wall. (From hallway to room thats not doorway)
			if(board.getCell(row, col).isHallway() && board.getCellDirection(direction, row, col).isRoom() && !board.getCellDirection(direction, row, col).isDoorway()) {return false;} 
			//checks that player isn't leaving through a wall
			if(board.getCell(row, col).isRoom() && !board.getCell(row, col).isDoorway() && board.getCellDirection(direction, row, col).isHallway()) return false;
			
			if(board.getCellDirection(direction, row, col).isRoom()) {stepCount--;} //moving in a room doesn't take up steps
			
			for(Cell cell: visitedCells) { //TODO fix me. Should stop playerPiece going on the same square twice
				if(board.getCell(row, col)==cell) return false;
			}
			visitedCells.add(board.getCell(row, col));
			
			//keeps track if the piece is in the doorway
			if(board.getCellDirection(direction, row, col).isDoorway()) {characterPiece.setInDoorway(true);}
			else {characterPiece.setInDoorway(false);}
			
			
		
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
			List<Piece> roomContains = board.getRoomContents().get(room);
			roomContains.remove(characterPiece);
		}
		
		if(board.getCell(row, col).isRoom()) { //adds player to a room. 
			Room room = board.getCell(row, col).getRoom();
			List<Piece> roomContains = board.getRoomContents().get(room);
			roomContains.add(characterPiece);
		}
		
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
			throw new InvalidParameterException();
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
		else throw new InvalidParameterException();
		
		return dir;
		
	}
}