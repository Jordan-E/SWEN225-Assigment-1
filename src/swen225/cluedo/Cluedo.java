package swen225.cluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import swen225.cluedo.cards.Card;
import swen225.cluedo.cards.Deck;
import swen225.cluedo.moves.CustomMove;
import swen225.cluedo.moves.EnvelopeMove;
import swen225.cluedo.moves.GuessMove;
import swen225.cluedo.moves.Move;
import swen225.cluedo.pieces.CharacterPiece;
import swen225.cluedo.pieces.Piece;
import swen225.cluedo.pieces.WeaponPiece;

/**
 * Class for input/output of Cluedo and high level logic 
 * 
 * @author elmes
 */
public class Cluedo {
	private TurnOrder turnOrder = new TurnOrder();
	private Envelope envelope;
	private Board board;
	
	// default values
	private static final String[] names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green", "Mrs Peacock", "Professor Plum"};
	private static final String[] weapons = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	private static final String[] rooms = {"Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room"};
	
	/**
	 * Constructor
	 * Initializes board, users and turn order
	 * @param playerCount
	 */
	public Cluedo() {
		Scanner inputScanner = new Scanner(System.in);
		Integer playerCount = getPlayerCount(inputScanner);
				
		board = new Board(25,24, names, weapons, rooms);
		
		// initialize users and their position in queue
		List<CharacterPiece> characterPieces = board.getCharacterPieces();
		for (int i = 0; i < playerCount; i++) {
			User user = new User(characterPieces.get(i));
			turnOrder.addUser(user);
		}
		
		// distribute cards
		Deck deck = new Deck(names, weapons, rooms);
		envelope = new Envelope(deck.getEnvelopeContents());
		dealHands(deck);
		
		play(inputScanner);
	}
	
	/**
	 * Play the game. This is were the high level logic is stored.
	 * 
	 * Asks player what move they would like to make
	 * Passes move to board to execute
	 * If they move into a room they can make a guess in that room
	 * Repeats in order until no players are left or someone wins
	 */
	private void play(Scanner inputScanner) {
		while (turnOrder.playersLeft()) {
			User user = turnOrder.currentUser(); // User who's turn it is
			int moves = rollDice(); // roll dice
			
			System.out.println("\n" + board.toString());
			System.out.println("\nIt is " + user.getName() + "'s (" + user.getCharacterPiece().getIdentifyingNum()+ ") turn.");
			System.out.println("You roll a "+moves);
			
			boolean validMove = false;
			while (!validMove) {
				Move move = moveSelection(inputScanner, user, moves);
				if(board.execute(move)) validMove = true;
				else System.out.println(move.getInvalidMessage());
			}
			
			// if still users turn (didn't guess envelope) check to see if they can guess
			if (user.equals(turnOrder.currentUser())) {
				Room room = board.inRoom(user);
				GuessMove guess = null;
				if (room != null && yesNoQuestion(inputScanner, "Would you like to make guess?")) {
					guess = guessSelection(inputScanner, user);
				}
				if (guess != null) {
					board.execute(guess); // move the pieces into the guessing room
					processGuess(inputScanner, guess);
				}
				
				turnOrder.endTurn(); //end turn
			}
			
			System.out.println("--------------- End of turn ---------------");
		}
		
		System.out.println("\n==================== END OF GAME ====================");
	}

	/**
	 * IO for moves of turn
	 * User can either move on the board or guess the contents of the envelope
	 * 
	 * @param user
	 * @param numMoves
	 * @return the move the user wants to perform
	 */
	private Move moveSelection(Scanner inputScanner, User user, int numMoves) {
		Move move = null;
		
		viewCardsDialogue(inputScanner, user);
		
		// tell user what moves they can move
		System.out.println("You may do the following moves: ");
		
		System.out.println("\t(c) Custom movement");
		System.out.println("\t(g) Guess the contents of the envelope");
		
		// get user input
		List<String> validInput = new ArrayList<>();
		validInput.add("c");
		validInput.add("g");
		String input = getInput(inputScanner, validInput);
		
		// construct move
		if (input.equals("c")) {
			move = customMoveDialogue(inputScanner, user, numMoves);
		} else if (input.equals("g")) {
			EnvelopeMove eMove = envelopeMoveDialogue(inputScanner, user);
			move = eMove;
			processEnvelopeGuess(user, eMove);
		}
		
		return move;
	}
	
	// ----- HELPERS -----//
	
	/**
	 * Asks the user how many people are playing
	 * Checks the input is valid
	 * 
	 * @param input Scanner for getting user input
	 * @return number of people playing
	 */
	private Integer getPlayerCount(Scanner input) {
		System.out.println("How many people are playing? (3 to 6)");
		Integer playerCount = 0;
		
		boolean validInput = false;
		while (!validInput) {
			String s = input.nextLine();
			try {
				playerCount = Integer.parseInt(s);
				if (playerCount <= 6 && playerCount>= 3) validInput = true;
				else throw new Error("Please enter a number between three and six.");
			} catch (NumberFormatException e) {
				System.out.println("\"" + s + "\" is not a number. Please enter a number between three and six.");
			} catch (Error e) {
				System.out.println(e.getMessage());
			}
		}
		
		return playerCount;
	}
	
	/**
	 * Deal cards in deck to users
	 * 
	 * @param deck
	 */
	private void dealHands(Deck deck) {
		while (!deck.isEmpty()) {
			turnOrder.currentUser().addCardToHand(deck.randomCard());
			turnOrder.endTurn();
		}
		
		// reset turn order so Miss Scarlett starts
		turnOrder.resetOrder();
	}
	
	
	/**
	 * Asks the user if they would like to see the cards they have seen
	 * If they do displays them
	 * 
	 * @param inputScanner
	 * @param user
	 */
	private void viewCardsDialogue(Scanner inputScanner, User user) {
		if (yesNoQuestion(inputScanner, "Would you like to see the list of cards you have seen?")) {
			System.out.println(user.getSeen());
		}
	}	
	
	
	/**
	 * Prints the guess
	 * If they guess correctly updates turnOrder to reflect no turns left in game
	 * If they guess incorrectly updates turnOrder so that they can only disprove guesses
	 * 
	 * @param user
	 * @param eMove
	 */
	private void processEnvelopeGuess(User user, EnvelopeMove eMove) {
		System.out.println(user.getName() + " guessed that " + eMove.getCharacter() + " is the murderer, " + 
				eMove.getWeapon() + " is the murder weapon, and " + eMove.getRoom().getName() + " is the location.");
		
		if (envelope.processGuess(eMove)) {
			// end game. They guessed correctly
			System.out.println(eMove.getUser().getName() + " guessed the contents of the envelope correctly.");
			System.out.println("The murderer was " + eMove.getCharacter() + " with a " + eMove.getWeapon() + " in the " + eMove.getRoom().getName() + ".");
			turnOrder.removeAll();
		} else {
			// Incorrect guess. Remove this player from the game (can still be called on for disproving guesses)
			System.out.println(eMove.getUser().getName() + " guessed the contents of the envelope incorrectly.");
			turnOrder.removeUser(user);
		}
	}
	
	/**
	 * Generates a GuessMove by asking the user what they want to guess
	 * (not for envelope guesses)
	 * 
	 * @param inputScanner
	 * @param user
	 * @return
	 */
	private GuessMove guessSelection (Scanner inputScanner, User user) {
		assert board.inRoom(user) != null;
		
		System.out.println(user.getName() + " has an opportunity to guess in " + board.inRoom(user));
		CharacterPiece character = chooseCharacter(inputScanner);
		WeaponPiece weapon = chooseWeapon(inputScanner);
		Room room = board.inRoom(user);
		
		return new GuessMove(user, character, weapon, room);
	}
	
	/**
	 * Goes round the users in order checking if they have a card to disprove the guess
	 * 
	 * If a user has only one card that can disprove the guess it shows the guesser that card.
	 * If a user has multiple cards that can disprove a guess it allows them to choose which card
	 * they show and then shows that card to the guesser.
	 * 
	 * 
	 * @param inputScanner
	 * @param guess
	 */
	private void processGuess(Scanner inputScanner, GuessMove guess) {
		System.out.println("Murderer - " + guess.getCharacter().toString() + "\tWeapon - " 
				+ guess.getWeapon().toString() + "\tRoom - " + guess.getRoom().getName());
		
		List<User> responseOrder = turnOrder.responseOrder(guess.getUser());
		boolean disputed = false;
		for (User user : responseOrder) {
			if (disputed) continue;
			
			List<Card> cards = user.hasCards(guess.getCharacter().getName(), guess.getWeapon().getName(), guess.getRoom().getName());
			if (cards.size() > 0) {
				disputed = true;
				
				Card show;
				if (cards.size() > 1) {
					show = cardChoiceDialogue(inputScanner, user, cards);
				} else show = cards.get(0);
				
				System.out.println(user.getName() + " has the " + show.toString() + " card.");
				guess.getUser().addCardToSeen(show);
			}
		}
		if (!disputed) System.out.println("No one else has any of the cards you guessed.");
	}
	
	/**
	 * This allows a user to show which card they reveal to a guesser if they have
	 * multiple cards that would disprove the guess
	 * 
	 * @param inputScanner
	 * @param user
	 * @param cards
	 * @return
	 */
	private Card cardChoiceDialogue(Scanner inputScanner, User user, List<Card> cards) {
		int count = 1;
		System.out.println(user.getName() + ", which card would you like to show?");
		for (Card card : cards) {
			System.out.println("\t(" + count++ + ") " + card);
		}
		
		// get user input
		List<String> validInput = new ArrayList<>();
		for (Integer i = 1; i <= cards.size(); i++) {validInput.add(i.toString());}
		Integer input = Integer.parseInt(getInput(inputScanner, validInput));
		
		return (cards.get(input - 1));
	}

	
	/**
	 * Input/output for creating a custom move
	 * Checks that the syntax is correct but not if the move is valid
	 * 
	 * @param inputScanner
	 * @param user
	 * @param numMoves
	 * @return
	 */
	private CustomMove customMoveDialogue(Scanner inputScanner, User user, int numMoves) {
		String input;
		
		while (true) {
			System.out.println("Please enter your movements in the form direction (N,E,S,W) followed by number of steps (1,2,3,..)");
			System.out.println("For movements in multiple directions seperate each move with a comma e.g. S3,E4");
			
			input = inputScanner.nextLine();
			try {
				return new CustomMove(user, numMoves, input);
			} catch (IllegalArgumentException e) {
				//repeat
			}
		}
	}
	
	/**
	 * Input/output for the envelope guess
	 * 
	 * @param inputScanner
	 * @param user
	 * @return the move that is created as a result of the user input
	 */
	private EnvelopeMove envelopeMoveDialogue(Scanner inputScanner, User user) {
		System.out.println(user.getName() + " is guessing the contents of the envelope.");
		String character = chooseCharacter(inputScanner).getName();
		String weapon = chooseWeapon(inputScanner).getName();
		Room room = chooseRoom(inputScanner);
		
		return new EnvelopeMove(user, character, weapon, room);
	}
	
	/**
	 * Input/output for picking a piece from a list of pieces
	 * 
	 * @param inputScanner
	 * @param message
	 * @param pieces
	 * @return selected piece
	 */
	private Piece choosePiece(Scanner inputScanner, String message, List<? extends Piece> pieces) {
		System.out.println(message);
		int count = 1;
		for (Piece piece : pieces) {
			System.out.println("\t(" + count++ + ") " + piece.getName());
		}
		
		// get user input
		List<String> validInput = new ArrayList<>();
		for (Integer i = 1; i <= pieces.size(); i++) {
			validInput.add(i.toString());
		}
		String input = getInput(inputScanner, validInput);
		
		return pieces.get(Integer.parseInt(input) - 1);
	}
	
	/**
	 * Input/Output for picking a CharacterPiece from all the character pieces
	 * 
	 * @param inputScanner
	 * @return selected CharacterPiece
	 */
	private CharacterPiece chooseCharacter(Scanner inputScanner) {
		return (CharacterPiece) choosePiece(inputScanner, "Who do you think the murderer was?", board.getCharacterPieces());
	}
	
	
	/**
	 * Input/Output for picking a WeaponPiece from all the weapon pieces
	 * 
	 * @param inputScanner
	 * @return selected WeaponPiece
	 */
	private WeaponPiece chooseWeapon(Scanner inputScanner) {
		return (WeaponPiece) choosePiece(inputScanner, "What do you think the murder weapon was?", board.getWeaponPieces());
	}
	
	
	/**
	 * Input/output for picking a room from the enum of rooms in board
	 * 
	 * @param inputScanner
	 * @return selected room
	 */
	private Room chooseRoom(Scanner inputScanner) {
		System.out.println("Which room do you think the murder took place in?");
		
		List<Room> rooms = board.getRooms();
		int count = 1;
		for (Room room : rooms) {
			System.out.println("\t(" + count++ + ") " + room.getName());
		}
		
		List<String> validInput = new ArrayList<>();
		for (Integer i = 1; i <= rooms.size(); i++) {
			validInput.add(i.toString());
		}
		
		String input = getInput(inputScanner, validInput);
		return rooms.get(Integer.parseInt(input)-1);
	}
	
	/**
	 * Roll a pair of imaginary dice
	 * @return number of moves
	 */
	private int rollDice() {
		return (int)(Math.random() * 5 + 1) + (int)(Math.random() * 5 + 1);
	}
	
	/**
	 * Checks if input matches any value in valid
	 * 
	 * @param valid List of valid input must be lower case
	 * @return valid input
	 */
	private String getInput(Scanner inputScanner, List<String> valid) {
		boolean validInput = false;
		String input = "";
		
		while (!validInput) {
			input = inputScanner.nextLine().toLowerCase();
			if (valid.contains(input)) {
				validInput = true;
			}else {
				System.out.print("Please enter any of the following: ");
				int i = 0;
				while (i < valid.size() - 1) {
					System.out.print("\"" + valid.get(i++) + "\"" + ", ");
				}
				System.out.println("\"" + valid.get(i) + "\"");
			}
		}	
		
		return input;
	}
	
	/**
	 * Asks the user the given yes/no question and returns answer
	 * 
	 * @param inputScanner
	 * @param question
	 * @return true for yes, false for no
	 */
	private boolean yesNoQuestion(Scanner inputScanner, String question) {
		System.out.println(question);
		
		List<String> validInput = new ArrayList<>();
		validInput.add("y"); validInput.add("n");
		String input = getInput(inputScanner, validInput);
		
		return input.equalsIgnoreCase("y");
	}
	
	
	
	
	/**
	 * New Cluedo instance
	 * @param args
	 */
	public static void main(String[] args) {	
		new Cluedo();
	}
}
