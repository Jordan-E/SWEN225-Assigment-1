package swen225.cluedo.moves;

import swen225.cluedo.Board;
import swen225.cluedo.Cell;
import swen225.cluedo.CellType;
import swen225.cluedo.User;

/**
 * Move where the user decides which cells to move through
 */

public class CustomMove extends Move{
	
	private int numMoves;
	private Direction direction;
	private int xPosition;
	private int yPosition;
	private int finalX;
	private int finalY;
	private Cell[][] board;

	public CustomMove(User user, int numSteps, Direction direction, Board board) {
		super(user);
		this.numMoves = numSteps;
		this.direction = direction;
		xPosition = user.getCharacterPiece().getX();
		yPosition = user.getCharacterPiece().getY();
		this.board = board.getBoard();
		newPosition();
	}

	@Override
	public String invalidMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		if(finalX < 0 || finalX > board.length || finalY < 0 || finalY > board[0].length) {return false;} //move would take user off the board
		
		if(direction == Direction.SOUTH) {
			for (int i = yPosition; i < finalY; i++) {
				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
			}
		}
		else if(direction == Direction.NORTH){
			for (int i = yPosition; i > finalY; i--) {
				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
			}
		}
		else if(direction == Direction.EAST){
			for (int i = xPosition; i < finalX; i++) {
				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
			}
		}
		else if(direction == Direction.WEST){
			for (int i = xPosition; i > finalX; i--) {
				if(board[yPosition][xPosition].getCellType() == CellType.OUT_OF_BOUNDS) {return false;}
			}
		}	
		
		return true;
	}
	
	/**
	 * calculates the finishing position of the piece after completing the move
	 */
	private void newPosition() {
		if(direction == Direction.SOUTH) {
			finalX = xPosition + numMoves; 
		}
		else if(direction == Direction.NORTH){
			finalX = xPosition - numMoves; 
		}
		else if(direction == Direction.EAST){
			finalY = xPosition + numMoves; 
		}
		else if(direction == Direction.WEST){
			finalY = xPosition - numMoves; 
		}				
	}
	


	@Override
	public User getUser() {		
		return user;
	}

}
