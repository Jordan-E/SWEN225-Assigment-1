package swen225.cluedo;

/**
 * 
 * @author Ellisjord
 */
public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;
	
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
	};
	
	public Board(int row, int col) {
		board = new Cell[row][col];
		if (row != 25 || col != 24) System.out.println("Board dimensions not supported");	//need to add error otherwise game will continue
		this.rows = row;
		this.cols = col;
		
		printBoard();
	}
	
	/**
	 * move player
	 */
	public void move(int x, int y) {}
	
	/**
	 * print out the board as text into the output.
	 */
	public void printBoard() {
		//System.out.println("|------------------------|");
		for(int i = 0; i<rows; i++) {
			System.out.print("|");
		    for(int j = 0; j<cols; j++) {
		        System.out.print(boardData[i][j]);
		    }
		    System.out.print("|");
		    System.out.println();
		}
		System.out.print("|");
		for(int i=0; i < rows-1; i++) {System.out.print("-");}
		System.out.print("|");
	}

	/**
	 * checks where a cell is clear for a player to move into
	 * 
	 * @return whether a player can move into this cell
	 */
	public boolean canMove() {return false;}
	
	private void loadMap() {
		for(int i = 0; i<rows; i++) {
		    for(int j = 0; j<cols; j++) {
		        
		    }
		}
	}
}
