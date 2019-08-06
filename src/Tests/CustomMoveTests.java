package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import swen225.cluedo.Board;
import swen225.cluedo.User;
import swen225.cluedo.moves.CustomMove;

class CustomMoveTests {

	private static final String[] names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green", "Mrs Peacock", "Professor Plum"};
	private static final String[] weapons = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	private static final String[] rooms = {"Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
	
	@Test
	void moveStright() { //move stright
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 5; //this is what they rolled on dice
		String userInput = "N5";
		CustomMove customMove;
		
		try {
			customMove = new CustomMove(user, numSteps,"up ten"); // incorrect syntax should throw IllegalArgumentException
		} catch (IllegalArgumentException e) {}
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertTrue(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	@Test
	void testWall() { //move stright
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 7; //this is what they rolled on dice
		String userInput = "N6, W1";
		CustomMove customMove;
		
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertTrue(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	@Test
	void twoDirectionMove() { //move stright
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 2; //this is what they rolled on dice
		String userInput = "N1, W1";
		CustomMove customMove;
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertFalse(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}

	@Test
	void tooManysteps() { //checks that the user cant  move more spaces than the dice allows
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 2; //this is what they rolled on dice
		String userInput = "N5";
		CustomMove customMove;
		
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertFalse(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	@Test
	void tooFewsteps() { 
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 2; //this is what they rolled on dice
		String userInput = "N1";
		CustomMove customMove;
		
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertFalse(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	@Test
	void sameCellStep() { //makes sure user cant step on the same cell twice in one move
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 4; //this is what they rolled on dice
		String userInput = "N2, S2";
		CustomMove customMove;
		

		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertFalse(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	
	@Test
	void enterDoorWrongDirectionLounge() { 

		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 6; //this is what they rolled on dice
		String userInput = "N5, W1";
		CustomMove customMove;
	
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertFalse(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	@Test
	void enterRoomFromDoorLOunge() { 
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 8; //this is what they rolled on dice
		String userInput = "N6, W1, S1";
		CustomMove customMove;
	
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertTrue(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	@Test
	void enterRoomFromDoorStudy() { 
		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 21; //this is what they rolled on dice
		String userInput = "N7, e9, S1, e1, s3";
		CustomMove customMove;
	
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertTrue(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	
	@Test
	void enterDoorWrongDirection() { 

		Board board = new Board(25, 24, names, weapons, rooms);
		User user = new User(board.getCharacterPieces().get(0)); // this gives us miss scarlett just -1 from piece id
		int numSteps = 21; //this is what they rolled on dice
		String userInput = "N7, e9, S4, e1";
		CustomMove customMove;
	
		
		customMove = new CustomMove(user, numSteps, userInput); // this is correct syntax so should be accepted		
		
		
		assertFalse(board.execute(customMove)); //this is a valid move so should return true;
		//board.printBoard();
	}
	

	

}
