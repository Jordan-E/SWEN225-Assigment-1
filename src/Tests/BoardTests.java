package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import swen225.cluedo.Board;
import swen225.cluedo.Cell;
import swen225.cluedo.Room;
import swen225.cluedo.pieces.CharacterPiece;
import swen225.cluedo.pieces.WeaponPiece;

public class BoardTests {

	private static final String[] names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green", "Mrs Peacock", "Professor Plum"};
	private static final String[] weapons = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	private static final String[] rooms = {"Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
	
	
	/**
	 * Ensure the board layout is correct
	 */
	@Test public void checkBoardLayout() {
		Board b = new Board(25, 24, names, weapons, rooms);
		
		String[][] expected = {
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
		
		Cell[][] board = b.getBoard();
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 24; j++) {
				assertEquals(expected[i][j], board[i][j].toString());
			}
		}
	}
	
	/**
	 * Check all character pieces start at the right location
	 */
	@Test 
	public void checkInitialPlayerLocation() {
		Board b = new Board(25, 24, names, weapons, rooms);
		
		for (CharacterPiece p : b.getCharacterPieces()) {
			int id = p.getIdentifyingNum();
			if(id == 1) {
				assertEquals(7, p.getX());
				assertEquals(24, p.getY());
			} else if (id == 2) {
				assertEquals(0, p.getX());
				assertEquals(17, p.getY());
			} else if (id == 3) {
				assertEquals(9, p.getX());
				assertEquals(0, p.getY());
			} else if (id == 4) {
				assertEquals(14, p.getX());
				assertEquals(0, p.getY());
			} else if (id == 5) {
				assertEquals(23, p.getX());
				assertEquals(6, p.getY());
			} else if (id == 6) {
				assertEquals(23, p.getX());
				assertEquals(19, p.getY());
			}
		}
	}
	
	
	// ------ Test Moves ----- //
	
	
}
