package swen225.cluedo;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import swen225.cluedo.cards.Card;
import swen225.cluedo.cards.Deck;
import swen225.cluedo.moves.CustomMove;
import swen225.cluedo.moves.EnvelopeMove;
import swen225.cluedo.moves.GuessMove;
import swen225.cluedo.moves.Move;
import swen225.cluedo.moves.RoomMove;
import swen225.cluedo.pieces.CharacterPiece;
import swen225.cluedo.pieces.Piece;
import swen225.cluedo.pieces.WeaponPiece;

/**
 * Class for running the Cluedo game. 
 * 
 * @author elmes
 */
public class Cluedo {
	private TurnOrder turnOrder = new TurnOrder();
	Envelope envelope;
	private Board board;
	
	private static final String[] names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green", "Mrs Peacock", "Professor Plum"};
	private static final String[] weapons = {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
	
	private List<CharacterPiece> characterPieces = new ArrayList<>();
	private List<WeaponPiece> weaponPieces = new ArrayList<>();
	
	/**
	 * Constructor
	 * @param playerCount
	 */
	public Cluedo() {
		Scanner inputScanner = new Scanner(System.in);
		Integer playerCount = getPlayerCount(inputScanner);
		
		
		// initialize character pieces
		for (int i = 0; i < names.length; i++) {
			characterPieces.add(new swen225.cluedo.pieces.CharacterPiece(names[i]));
		}
		
		board = new Board(25,24, characterPieces);
		
		// initialize weapon pieces
		for (int i = 0; i < weapons.length; i++) {
			weaponPieces.add(new WeaponPiece(weapons[i]));
		}
		
		// initialize users and their position in queue
		for (int i = 0; i < playerCount; i++) {
			User user = new User(characterPieces.get(i));
			turnOrder.addUser(user);
		}
		
		// distribute cards
		Deck deck = new Deck(names, weapons);
		envelope = new Envelope(deck.getEnvelopeContents());
		dealHands(deck);
		
		play(inputScanner);
	}
	
	public List<CharacterPiece> getCharacterPieces() {
		return characterPieces;
	}

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
			turnOrder.currentUser().addCard(deck.randomCard());
			turnOrder.endTurn();
		}
		
		// reset turn order so Miss Scarlett starts
		turnOrder.resetOrder();
	}
	
	/**
	 * Play the game. This is were the high level logic should be
	 */
	private void play(Scanner inputScanner) {
		while (turnOrder.playersLeft()) {
			User user = turnOrder.currentUser(); // User who's turn it is
			int moves = rollDice(); // roll dice
			
			System.out.println("\n" + board.toString());
			System.out.println("\nIt is " + user.getName() + "'s turn.");
			System.out.println("You roll a "+moves);
			
			boolean validMove = false;
			while (!validMove) {
				Move move = moveSelection(inputScanner, user, moves);
				if(board.execute(move)) validMove = true;
				else System.out.println(move.invalidMessage());
			}
			
			// if still users turn (didn't guess envelope) check to see if they can guess
			if (user.equals(turnOrder.currentUser())) {
				Board.Room room = board.inRoom(user);
				GuessMove guess = null;
				if (room != null) guess = guessSelection(inputScanner, user);
				if (guess != null) {
					board.execute(guess); // move the pieces into the guessing room
					processGuess(inputScanner, guess);
				}
				
				turnOrder.endTurn(); //end turn
			}
			
			System.out.println("End of turn");
		}
		
		System.out.println("---- END OF GAME ----");
	}
	
	/**
	 * IO for moves of turn
	 * Tells user where/how they can move
	 * 
	 * @param user
	 * @param numMoves
	 * @return the move the user wants to perform
	 */
	private Move moveSelection(Scanner inputScanner, User user, int numMoves) {
		Move move = null;
		
		List<Board.Room> rooms = board.possibleRooms(user, numMoves);
		int count = 1;
		
		// tell user where they can move
		System.out.println("You may move to: ");
		for (Board.Room room : rooms) {
			System.out.println("\t(" + count++ + ") " + room);
		}
		System.out.println("\t(c) Custom movement");
		System.out.println("\t(g) Guess the contents of the envelope");
		
		// get user input
		List<String> validInput = new ArrayList<>();
		for (Integer i = 1; i <= rooms.size(); i++) {validInput.add(i.toString());}
		validInput.add("c");
		validInput.add("g");
		String input = getInput(inputScanner, validInput);
		
		// construct move
		if (input.equals("c")) {
			move = customMoveDialogue(inputScanner, user, numMoves);
		} else if (input.equals("g")) {
			EnvelopeMove eMove = envelopeMoveDialogue(inputScanner, user);
			move = eMove;
			
			System.out.println(user.getName() + " guessed that " + eMove.getCharacter() + " is the murderer, " + 
					eMove.getWeapon() + " is the murder weapon, and " + eMove.getRoom() + " is the location.");
			if (envelope.processGuess(eMove)) {
				// end game. They guessed correctly
				System.out.println(move.getUser().getName() + " guessed the contents of the envelope correctly.");
				System.out.println("The murderer was " + eMove.getCharacter() + " with a " + eMove.getWeapon() + " in the " + eMove.getRoom() + ".");
				turnOrder.removeAll();
			} else {
				// Incorrect guess. Remove this player from the game (can still be called on for disproving guesses)
				System.out.println(move.getUser().getName() + " guessed the contents of the envelope incorrectly.");
				turnOrder.removeUser(user);
			}
		} else {
			// chose a room to move to
			move = new RoomMove(user, rooms.get(Integer.parseInt(input)));
		}
		
		return move;
	}
	
	private GuessMove guessSelection (Scanner inputScanner, User user) {
		assert board.inRoom(user) != null;
		
		System.out.println(user + " has an opportunity to guess in " + board.inRoom(user));
		CharacterPiece character = chooseCharacter(inputScanner);
		WeaponPiece weapon = chooseWeapon(inputScanner);
		Board.Room room = board.inRoom(user);
		
		return new GuessMove(user, character, weapon, room);
	}
	
	private void processGuess(Scanner inputScanner, GuessMove guess) {
		System.out.println("Murderer - " + guess.getCharacter().toString() + "\tWeapon - " 
				+ guess.getWeapon().toString() + "\tRoom - " + guess.getRoom());
		
		List<User> responseOrder = turnOrder.responseOrder(guess.getUser());
		boolean disputed = false;
		for (User user : responseOrder) {
			if (disputed) continue;
			
			List<Card> cards = user.hasCards(guess.getCharacter().getName(), guess.getWeapon().getName(), guess.getRoom());
			if (cards.size() > 0) {
				disputed = true;
				
				Card show;
				if (cards.size() > 1) {
					show = cardChoiceDialogue(inputScanner, user, cards);
				} else show = cards.get(0);
				
				System.out.println(user.getName() + " has the " + show.toString() + "card.");
			}
		}
		if (!disputed) System.out.println("No one else has any of the cards you guessed.");
	}
	
	private Card cardChoiceDialogue(Scanner inputScanner, User user, List<Card> cards) {
		int count = 1;
		System.out.println("Which card would you like to show " + user.getName() + ".");
		for (Card card : cards) {
			System.out.println("\t(" + count++ + ") " + card);
		}
		
		// get user input
		List<String> validInput = new ArrayList<>();
		for (Integer i = 1; i <= cards.size(); i++) {validInput.add(i.toString());}
		Integer input = Integer.parseInt(getInput(inputScanner, validInput));
		
		return (cards.get(input - 1));
	}

	private CustomMove customMoveDialogue(Scanner inputScanner, User user, int numMoves) {
		String input;
		
		while (true) {
			System.out.println("Please enter your movements in the form direction (N,E,S,W) followed by number of steps (1,2,3,..)");
			System.out.println("For movements in multiple directions seperate each move with a comma e.g. S3,E4");
			
			input = inputScanner.nextLine();
			try {
				return new CustomMove(user, numMoves, input);
			} catch (InvalidParameterException e) {
				//repeat
			}
		}
	}
	
	private EnvelopeMove envelopeMoveDialogue(Scanner inputScanner, User user) {
		System.out.println(user.getName() + " is guessing the contents of the envelope.");
		String character = chooseCharacter(inputScanner).getName();
		String weapon = chooseWeapon(inputScanner).getName();
		Board.Room room = chooseRoom(inputScanner);
		
		return new EnvelopeMove(user, character, weapon, room);
	}
	
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
	
	private CharacterPiece chooseCharacter(Scanner inputScanner) {
		return (CharacterPiece) choosePiece(inputScanner, "Who do you think the murderer was?", characterPieces);
	}
	
	private WeaponPiece chooseWeapon(Scanner inputScanner) {
		return (WeaponPiece) choosePiece(inputScanner, "What do you think the murder weapon was?", weaponPieces);
	}
	
	private Board.Room chooseRoom(Scanner inputScanner) {
		System.out.println("Which room do you think the murder took place in?");
		
		Board.Room[] rooms = Board.Room.values();
		int count = 1;
		for (Board.Room room : rooms) {
			System.out.println("\t(" + count++ + ") " + room);
		}
		
		List<String> validInput = new ArrayList<>();
		for (Integer i = 1; i <= rooms.length; i++) {
			validInput.add(i.toString());
		}
		
		String input = getInput(inputScanner, validInput);
		return rooms[Integer.parseInt(input)-1];
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
	
	
	public static void main(String[] args) {	
		new Cluedo();
	}
}
