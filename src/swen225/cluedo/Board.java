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
	private List<CharacterPiece> characterPieces = new ArrayList<>();
	private List<WeaponPiece> weaponPieces = new ArrayList<>();
	private List<Room> rooms = new ArrayList<>();
	
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
		{"d","d","d","d","d","d","d","D"," "," ","-","-","-","-","-"," "," "," ","i","i","i","i","I","i"},//12 Col
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
												//Row
						
	public Board(int row, int col, String[] characters, String[] weapons, String[] roomNames) {
		board = new Cell[row][col]; //[25][24]
		if (row != 25 || col != 24) System.out.println("Board dimensions not supported");	//need to add error otherwise game will continue
		
		// initialize character pieces
		for (int i = 0; i < characters.length; i++) {
			characterPieces.add(new swen225.cluedo.pieces.CharacterPiece(characters[i]));
		}
		
		// initialize weapon pieces
		for (int i = 0; i < weapons.length; i++) {
			weaponPieces.add(new WeaponPiece(weapons[i]));
		}
		
		// initialize rooms
		for (int i = 0; i < roomNames.length; i++) {
			rooms.add(new Room(roomNames[i]));
		}
		
		this.rows = row;
		this.cols = col;
		loadBoard();
	}
	
	
	
	/**
	 * takes the boardData array and makes board with the correct Cells using the boardData data
	 */
	private void loadBoard() {
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[i].length; j++) {
				board[i][j] = new Cell(boardData[i][j], rooms);
			}
		}	
		for (int i = 0; i < characterPieces.size(); i++) {
			board[characterPieces.get(i).getY()][characterPieces.get(i).getX()].setOccupied(true);
		}
	}
	


	/**
	 * Get a cell in board
	 * @param row
	 * @param col
	 * @return Cell
	 */
	public Cell getCell(int row, int col) {
		if(row < 0 || row > rows || col < 0 || col > cols) {System.out.println("Row: " + row + " Col: " + col); return null; } 
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
		
		
//		s +="\n";
//		for(int i = 0; i<rows; i++) {
//			s += "|";
//		    for(int j = 0; j<cols; j++) {
//		    	
//		    	boolean found = false;
//		    	for (CharacterPiece chara: characterPieces) { //get the number to display for the characterPiece
//					int col = chara.getX();
//					int row = chara.getY();
//					if(row == i && col == j) {s += chara.getIdentifyingNum(); found=true; break;}
//				}
//		    	if(found == false) {s += board[i][j];}
//		        found = false;
//		    }
//		    s += "|\n";
//		}
//		s += "|";
//		for(int i=0; i < rows-1; i++) {s += "-";}
//		s += "|";
		
		
		
		return s;
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
			move.apply(this); //setting valid here bad
			return true;
		}

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
		return getRoom(user.getCharacterPiece());
	}

	/**
	 * Get room piece is in
	 * @param piece
	 * @return
	 */
	public Room getRoom(Piece piece) {
		int col = piece.getX();
		int row = piece.getY();
		if (board[row][col].isRoom()) {
			Room room = board[row][col].getRoom();
			return room;
		}
		return null;
	}


	public List<CharacterPiece> getCharacterPieces() {
		return characterPieces;
	}



	public List<WeaponPiece> getWeaponPieces() {
		return weaponPieces;
	}



	public List<Room> getRooms() {
		return rooms;
	}
	
	
}