package swen225.cluedo;

import java.util.ArrayList;
import java.util.List;

import swen225.cluedo.moves.Move;
import swen225.cluedo.pieces.CharacterPiece;

/**
 * 
 * @author Ellisjord
 */
public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;
	private List<CharacterPiece> characterPieces;
	
	public enum Room {Kitchen, BallRoom, Conservatory, BilliardRoom, Library, Study, Hall, Lounge, DiningRoom}
	
	
	private String[][] boardData = {
		{"-","-","-","-","-","-","-","-","-"," ","-","-","-","-"," ","-","-","-","-","-","-","-","-","-"},//1
		{"k","k","k","k","k","k","-"," "," "," ","b","b","b","b"," "," "," ","-","c","c","c","c","c","c"},//2
		{"k","k","k","k","k","k"," "," ","b","b","b","b","b","b","b","b"," "," ","c","c","c","c","c","c"},//3
		{"k","k","k","k","k","k"," "," ","b","b","b","b","b","b","b","b"," "," ","c","c","c","c","c","c"},//4
		{"k","k","k","k","k","k"," "," ","b","b","b","b","b","b","b","b"," "," ","C","c","c","c","c","c"},//5
		{"k","k","k","k","k","k"," "," ","B","b","b","b","b","b","b","B"," "," "," ","c","c","c","c","-"},//6
		{"-","k","k","k","K","k"," "," ","b","b","b","b","b","b","b","b"," "," "," "," "," "," "," "," "},//7
		{" "," "," "," "," "," "," "," ","b","B","b","b","b","b","B","b"," "," "," "," "," "," "," ","-"},//8
		{"-"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","i","i","i","i","i","i"},//9
		{"d","d","d","d","d"," "," "," "," "," "," "," "," "," "," "," "," "," ","I","i","i","i","i","i"},//10
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," ","i","i","i","i","i","i"},//11
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," ","i","i","i","i","i","i"},//12
		{"d","d","d","d","d","d","d","D"," "," ","-","-","-","-","-"," "," "," ","i","i","i","i","I","i"},//13
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," "," "," "," "," "," ","-"},//14
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," ","l","l","l","l","l","-"},//15
		{"d","d","d","d","d","d","D","d"," "," ","-","-","-","-","-"," "," ","l","l","l","l","l","l","l"},//16
		{"-"," "," "," "," "," "," "," "," "," ","-","-","-","-","-"," "," ","L","l","l","l","l","l","l"},//17
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","l","l","l","l","l","l","l"},//18
		{"-"," "," "," "," "," "," "," "," ","h","h","H","H","h","h"," "," "," ","l","l","l","l","l","-"},//19
		{"o","o","o","o","o","o","O"," "," ","h","h","h","h","h","h"," "," "," "," "," "," "," "," "," "},//20
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","H"," "," "," "," "," "," "," "," ","-"},//21
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","h"," "," ","S","s","s","s","s","s","s"},//22
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","h"," "," ","s","s","s","s","s","s","s"},//23
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","h"," "," ","s","s","s","s","s","s","s"},//24
		{"o","o","o","o","o","o","-"," ","-","h","h","h","h","h","h","-"," ","-","s","s","s","s","s","s"}//25
	};  //1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  
	
	
	public Board(int row, int col, List<CharacterPiece> characterPieces) {
		board = new Cell[row][col]; //[25][24]
		if (row != 25 || col != 24) System.out.println("Board dimensions not supported");	//need to add error otherwise game will continue
		this.rows = row;
		this.cols = col;
		this.characterPieces = characterPieces;
		loadBoard();
	}
	
	
	/**
	 * takes the boardData array and makes board with the correct Cells using the boardData data
	 */
	private void loadBoard() {
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[i].length; j++) {
				board[i][j] = new Cell(boardData[i][j]);
			}
		}	
		for (int i = 0; i < characterPieces.size(); i++) {
			board[characterPieces.get(i).getX()][characterPieces.get(i).getY()].setOccupied(true);
		}
	}
	
	
	/**
	 * move player
	 */
	public void move(int x, int y) {}
	
	/**
	 * print out the board as text into the output.
	 */
	public void printBoard() {
		System.out.println(toString());
	}
	
	public String toString() {
		String s = "";
		
		//s+=("|------------------------|\n");
		for(int i = 0; i<rows; i++) {
			s += "|";
		    for(int j = 0; j<cols; j++) {
		    	if (!board[i][j].isOccupied()) {
		    		s += board[i][j];
				}
		    	else {s += "*";}
		        
		    }
		    s += "|\n";
		}
		s += "|";
		for(int i=0; i < rows-1; i++) {s += "-";}
		s += "|";
		return s;
	}

	/**
	 * Calculates which rooms a user's piece can move into
	 * 
	 * @return list of rooms in range of user's piece
	 */
	public List<Room> possibleRooms(User user, int numMoves) {
		return new ArrayList<>();
	}
	
	/**
	 * checks where a cell is clear for a player to move into
	 * 
	 * @return whether a player can move into this cell
	 */
	public boolean canMove(Cell cell) {
		if (cell.isOccupied() /*|| cell.getCellType() == CellType.OUT_OF_BOUNDS)*/) {
			return false;
		}
		return true;
	}
		
	
	/**
	 * When given a move. 
	 * Checks the move is valid.
	 * Moves the relevant pieces.
	 * 
	 * @param move
	 * @return whether the move was successful (valid)
	 */
	public boolean execute(Move move) {
		if (move.isValid(this)) {
			move.apply();
			return true;
		}
		else {return false;}
		
	}
	
	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	/**
	 * Test whether a user is in a room
	 * 
	 * @param user
	 * @return null if not in room. Room if in room
	 */
	public Room inRoom(User user) {
		int x = user.getCharacterPiece().getX();
		int y = user.getCharacterPiece().getY();
		if (board[x][y].isRoom()) {
			Room room = board[x][y].getRoom();
			return room;
		}
		return null;
	}
	

}
