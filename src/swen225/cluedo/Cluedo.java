package swen225.cluedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		board = new Board(25,24);
		
		// initialize character pieces
		for (int i = 0; i < names.length; i++) {
			characterPieces.add(new swen225.cluedo.pieces.CharacterPiece(names[i]));
		}
		
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
		Deck deck = new Deck();
		envelope = new Envelope(deck.getEnvelopeContents());
		dealHands(deck);
		
		play(inputScanner);
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
			
			Board.Room room = board.inRoom(user);
			GuessMove guess = null;
			if (room != null) guess = guessSelection(inputScanner, user);
			if (guess != null) processGuess(guess);
			
			System.out.println("end of turn");
			turnOrder.endTurn(); //end turn
		}
	}
	
	/**
	 * IO for first move of turn
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
			move = envelopeMoveDialogue(inputScanner, user);
		} else {
			move = roomMoveDialogue(inputScanner, user, rooms.get(Integer.parseInt(input)));
		}
		
		return move;
	}
	
	private GuessMove guessSelection (Scanner inputScanner, User user) {
		assert board.inRoom(user) != null;
		
		CharacterPiece character;
		WeaponPiece weapon;
		Board.Room room = board.inRoom(user);
		
		System.out.println(user + " has an opportunity to guess in " + board.inRoom(user));
		character = chooseCharacter(inputScanner);
		weapon = chooseWeapon(inputScanner);
		
		return new GuessMove(user, character, weapon, room);
	}
	
	private void processGuess(GuessMove guess) {
		System.out.println("Murderer - " + guess.getCharacter().toString() + "\tWeapon - " + guess.getWeapon().toString() + "\tRoom - " + guess.getRoom());
		System.out.println("#### NOT IMPLEMENTED YET ####");
	}
	
	
	private CustomMove customMoveDialogue(Scanner inputScanner, User user, int numMoves) {
		//TODO
		System.out.println("#### customMoveDialogue NOT IMPLEMENTED ####");
		//return new CustomMove(user, numMoves);
		return null;
	}
	
	private EnvelopeMove envelopeMoveDialogue(Scanner inputScanner, User user) {
		//TODO
		System.out.println("#### envelopeMoveDialogue NOT IMPLEMENTED ####");
		return new EnvelopeMove(user);
	}
	
	private RoomMove roomMoveDialogue(Scanner inputScanner, User user, Board.Room room) {
		//TODO
		System.out.println("#### roomMoveDialogue NOT IMPLEMENTED ####");
		return new RoomMove(user, room);
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
