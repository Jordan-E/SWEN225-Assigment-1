package swen225.cluedo.moves;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import swen225.cluedo.Board;
import swen225.cluedo.User;

/**
 * Move where the user decides which cells to move through
 * 
 * @author elmes
 */

public class CustomMove extends Move{
	
	private int numSteps;
	private List<Step> steps;
	
	private static List<Character> validDirections = Arrays.asList(new Character[] {'n','e','s','w'});
	
	public CustomMove(User user, int numSteps, String input) {
		super(user);
		this.numSteps = numSteps;
		steps = processSteps(input);
	}

	@Override
	public String invalidMessage() {
		// TODO Auto-generated method stub
		return null;
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

	
	@Override
	public boolean isValid(Board board) {
		for (int i = 0; i < steps.size(); i++) {
			if (!canMove(steps.get(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean canMove(Step steps) {
//		if(finalX < 0 || finalX > board.length || finalY < 0 || finalY > board[0].length) {return false;} //move would take user off the board
//		
//		if(direction == Direction.SOUTH) {
//			for (int i = yPosition; i < finalY; i++) {
//				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
//			}
//		}
//		else if(direction == Direction.NORTH){
//			for (int i = yPosition; i > finalY; i--) {
//				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
//			}
//		}
//		else if(direction == Direction.EAST){
//			for (int i = xPosition; i < finalX; i++) {
//				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
//			}
//		}
//		else if(direction == Direction.WEST){
//			for (int i = xPosition; i > finalX; i--) {
//				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
//			}
//		}
		
		return false;
	}

	/**
	 * calculates the finishing position of the piece after completing the move
	 */
//	private void newPosition() {
//		if(direction == Direction.SOUTH) {
//			finalX = xPosition + numMoves; 
//		}
//		else if(direction == Direction.NORTH){
//			finalX = xPosition - numMoves; 
//		}
//		else if(direction == Direction.EAST){
//			finalY = xPosition + numMoves; 
//		}
//		else if(direction == Direction.WEST){
//			finalY = xPosition - numMoves; 
//		}				
//	}
	


	@Override
	public User getUser() {		
		return user;
	}

	@Override
	public boolean apply() {
		// TODO Auto-generated method stub
		return false;
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
	
	public Direction getDir() {
		return dir;
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