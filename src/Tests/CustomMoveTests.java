package Tests;

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
	void test() {
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
	}

}
