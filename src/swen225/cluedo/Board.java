package swen225.cluedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import swen225.cluedo.moves.Move;
import swen225.cluedo.pieces.CharacterPiece;
import swen225.cluedo.pieces.Piece;
import swen225.cluedo.pieces.WeaponPiece;

/**
 * 
 * @author Ellisjord
 */
public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;
	private List<CharacterPiece> characterPieces;
	private List<WeaponPiece> weaponPieces;
	private Map<Room, List<Piece>> roomContents = new HashMap<>();
	
	public enum Room {Kitchen, BallRoom, Conservatory, BilliardRoom, Library, Study, Hall, Lounge, DiningRoom}
	
	
	private String[][] boardData = {
		{"-","-","-","-","-","-","-","-","-"," ","-","-","-","-"," ","-","-","-","-","-","-","-","-","-"},//0
		{"k","k","k","k","k","k","-"," "," "," ","b","b","b","b"," "," "," ","-","c","c","c","c","c","c"},//1
		{"k","k","k","k","k","k"," "," ","b","b","b","b","b","b","b","b"," "," ","c","c","c","c","c","c"},//2
		{"k","k","k","k","k","k"," "," ","b","b","b","b","b","b","b","b"," "," ","c","c","c","c","c","c"},//3
		{"k","k","k","k","k","k"," "," ","b","b","b","b","b","b","b","b"," "," ","C","c","c","c","c","c"},//4
		{"k","k","k","k","k","k"," "," ","B","b","b","b","b","b","b","B"," "," "," ","c","c","c","c","-"},//5
		{"-","k","k","k","K","k"," "," ","b","b","b","b","b","b","b","b"," "," "," "," "," "," "," "," "},//6
		{" "," "," "," "," "," "," "," ","b","B","b","b","b","b","B","b"," "," "," "," "," "," "," ","-"},//7
		{"-"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","i","i","i","i","i","i"},//8
		{"d","d","d","d","d"," "," "," "," "," "," "," "," "," "," "," "," "," ","I","i","i","i","i","i"},//9
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," ","i","i","i","i","i","i"},//10
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," ","i","i","i","i","i","i"},//11
		{"d","d","d","d","d","d","d","D"," "," ","-","-","-","-","-"," "," "," ","i","i","i","i","I","i"},//12
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," "," "," "," "," "," ","-"},//13
		{"d","d","d","d","d","d","d","d"," "," ","-","-","-","-","-"," "," "," ","l","l","l","l","l","-"},//14
		{"d","d","d","d","d","d","D","d"," "," ","-","-","-","-","-"," "," ","l","l","l","l","l","l","l"},//15
		{"-"," "," "," "," "," "," "," "," "," ","-","-","-","-","-"," "," ","L","l","l","l","l","l","l"},//16
		{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","l","l","l","l","l","l","l"},//17
		{"-"," "," "," "," "," "," "," "," ","h","h","H","H","h","h"," "," "," ","l","l","l","l","l","-"},//18
		{"o","o","o","o","o","o","O"," "," ","h","h","h","h","h","h"," "," "," "," "," "," "," "," "," "},//19
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","H"," "," "," "," "," "," "," "," ","-"},//20
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","h"," "," ","S","s","s","s","s","s","s"},//21
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","h"," "," ","s","s","s","s","s","s","s"},//22
		{"o","o","o","o","o","o","o"," "," ","h","h","h","h","h","h"," "," ","s","s","s","s","s","s","s"},//23
		{"o","o","o","o","o","o","-"," ","-","h","h","h","h","h","h","-"," ","-","s","s","s","s","s","s"}//24
	};  //0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23    
	
	
	public Board(int row, int col, List<CharacterPiece> characterPieces, List<WeaponPiece> weaponPieces) {
		board = new Cell[row][col]; //[25][24]
		if (row != 25 || col != 24) System.out.println("Board dimensions not supported");	//need to add error otherwise game will continue
		this.rows = row;
		this.cols = col;
		this.characterPieces = characterPieces;
		this.weaponPieces = weaponPieces;
		loadBoard();
		for (Room room : Room.values()) { 
			roomContents.put(room, new ArrayList<Piece>());		   
		}		
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
			board[characterPieces.get(i).getY()][characterPieces.get(i).getX()].setOccupied(true);
		}
	}
	
	/**
	 * 
	 * @param direction
	 * @param row
	 * @param col
	 * @return The cell one in the direction of the direction entered, returns null if off the board
	 */
	public Cell getCellDirection(String direction, int row, int col) { 
		if (direction == "N") {
			if(row-1 < 0) {return null;} //checks for going off the board
			return board[row-1][col];			
		}else if(direction == "S") {
			if(row+1 > rows) {return null;}
			return board[row+1][col];
		}else if(direction == "W") {
			if(col-1 < 0) {return null;}
			return board[row][col-1];
		}else if(direction == "E") {
			if(col+1 > cols) {return null;}
			return board[row][col+1];
		}else {throw new Error("getCell incorrect input");}
	}

	/**
	 * get room conetents map
	 * @return
	 */
	public Map<Room, List<Piece>> getRoomContents() {
		return roomContents;
	}



	/**
	 * set piece in roomconetents map
	 * @param roomContents
	 */
	public void setRoomContents(Map<Room, List<Piece>> roomContents) {
		this.roomContents = roomContents;
	}




	/**
	 * Get a cell in board
	 * @param row
	 * @param col
	 * @return Cell
	 */
	public Cell getCell(int row, int col) {
		if(row < 0 || row > cols || col < 0 || col > cols) {System.out.println("Row: " + row + " Col: " + col); return null; } 
		return board[row][col];
	}
	
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
		    	else {
		    		for (CharacterPiece chara: characterPieces) { //get the number to display for the characterPiece
						int col = chara.getX();
						int row = chara.getY();
						if(row == i && col == j) {s += chara.getIdentifyingNum(); break;}
					}
		    	}
		        
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
	 * When given a move. 
	 * Checks the move is valid.
	 * Moves the relevant pieces.
	 * 
	 * @param move
	 * @return whether the move was successful (valid)
	 */
	public boolean execute(Move move) {
		if (move.isValid(this)) {
			move.apply(this);
			return true;
		}
		System.out.println("Invalid move (Board execute)");
		return false;
		
	}
	
	/**
	 * get game board
	 * @return the game board containing cells
	 */
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
		int col = user.getCharacterPiece().getX();
		int row = user.getCharacterPiece().getY();
		if (board[row][col].isRoom()) {
			Room room = board[row][col].getRoom();
			return room;
		}
		return null;
	}
	

}