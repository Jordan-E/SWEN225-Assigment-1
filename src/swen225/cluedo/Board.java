package swen225.cluedo;

import java.util.ArrayList;
import java.util.List;

import swen225.cluedo.moves.Move;

/**
 * 
 * @author Ellisjord
 */
public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;
	
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
	};  //1   2   3   4   5   6   7   8  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25
	
	
	public Board(int row, int col) {
		board = new Cell[row][col];
		if (row != 25 || col != 24) System.out.println("Board dimensions not supported");	//need to add error otherwise game will continue
		this.rows = row;
		this.cols = col;
		loadBoard();
		printBoard();
	}
	
	
	/**
	 * takes the boardData array and makes board with the correct Cells using the boardData data
	 */
	private void loadBoard() {
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[i].length; j++) {
				if(boardData[i][j] == "-") {
					board[i][j] = new Cell("OUT_OF_BOUNDS", "-");
				}
				else if(boardData[i][j] == " ") {
					board[i][j] = new Cell("HALL", " ");
				}
				else {//must be a letter 
					
					Cell newCell;	

					
					if(boardData[i][j] == "k") {
						newCell = new Cell("OUT_OF_BOUNDS", "k");
						newCell.setRoom(Room.Kitchen);
					}
					else if(boardData[i][j] == "K") {
						newCell = new Cell("ROOM", "K");
						newCell.setRoom(Room.Kitchen);
					}
					else if(boardData[i][j] == "b") {
						newCell = new Cell("OUT_OF_BOUNDS", "b");
						newCell.setRoom(Room.BallRoom);
					}
					else if(boardData[i][j] == "B") {
						newCell = new Cell("ROOM", "B");
						newCell.setRoom(Room.BallRoom);
					}
					else if(boardData[i][j] == "c") {
						newCell = new Cell("OUT_OF_BOUNDS", "c");
						newCell.setRoom(Room.Conservatory);
					}
					else if(boardData[i][j] == "C") {
						newCell = new Cell("ROOM", "C");
						newCell.setRoom(Room.Conservatory);
					}
					else if(boardData[i][j] == "i") {
						newCell = new Cell("OUT_OF_BOUNDS", "i");
						newCell.setRoom(Room.BilliardRoom);
					}
					else if(boardData[i][j] == "I") {
						newCell = new Cell("ROOM", "I");
						newCell.setRoom(Room.BilliardRoom);
					}
					else if(boardData[i][j] == "l") {
						newCell = new Cell("OUT_OF_BOUNDS", "l");
						newCell.setRoom(Room.Library);
					}
					else if(boardData[i][j] == "L") {
						newCell = new Cell("ROOM", "L");
						newCell.setRoom(Room.Library);
					}
					else if(boardData[i][j] == "s") {
						newCell = new Cell("OUT_OF_BOUNDS", "s");
						newCell.setRoom(Room.Study);
					}
					else if(boardData[i][j] == "S") {
						newCell = new Cell("ROOM", "S");
						newCell.setRoom(Room.Study);
					}
					else if(boardData[i][j] == "h") {
						newCell = new Cell("OUT_OF_BOUNDS", "h");
						newCell.setRoom(Room.Hall);
					}
					else if(boardData[i][j] == "H") {
						newCell = new Cell("ROOM", "H");
						newCell.setRoom(Room.Hall);
					}
					else if(boardData[i][j] == "o") {
						newCell = new Cell("OUT_OF_BOUNDS", "o");
						newCell.setRoom(Room.Lounge);
					}
					else if(boardData[i][j] == "O") {
						newCell = new Cell("ROOM", "O");
						newCell.setRoom(Room.Lounge);
					}
					else if(boardData[i][j] == "d") {
						newCell = new Cell("OUT_OF_BOUNDS", "d");
						newCell.setRoom(Room.DiningRoom);
					}
					else if (boardData[i][j] == "D") {
						newCell = new Cell("ROOM", "D");
						newCell.setRoom(Room.DiningRoom);
					}	
					else {
						throw new Error("Inncorect letter in board data. Inncorect letter: " + boardData[i][j]);
					}
					
					board[i][j] = newCell;
					
				}
			}
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
		//TODO please change this to a .toString() method
		//System.out.println("|------------------------|");
		for(int i = 0; i<rows; i++) {
			System.out.print("|");
		    for(int j = 0; j<cols; j++) {
		        System.out.print(board[i][j]);
		    }
		    System.out.print("|");
		    System.out.println();
		}
		System.out.print("|");
		for(int i=0; i < rows-1; i++) {System.out.print("-");}
		System.out.print("|");
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
	public boolean canMove() {return false;}
	
	
	/**
	 * Talk to me about this
	 * 
	 * @param move
	 * @return whether the move was successful (valid)
	 */
	public boolean execute(Move move) {
		// TODO Auto-generated method stub
		return true;
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
		if (board[x][y].getCellType() == CellType.ROOM) {
			Room room = board[x][y].getRoom();
			return room;
		}
		return null;
	}
	
	private void loadMap() {
		for(int i = 0; i<rows; i++) {
		    for(int j = 0; j<cols; j++) {
		        
		    }
		}
	}
}
