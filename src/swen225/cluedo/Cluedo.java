package swen225.cluedo;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import swen225.cluedo.cards.Deck;

/**
 * Class for running the Cluedo game. 
 * 
 * @author elmes
 */
public class Cluedo {
	private Queue<User> turnQueue;
	Envelope envelope;
	private Board board;
	
	/**
	 * Constructor
	 * @param playerCount
	 */
	public Cluedo(int playerCount) {
		board = new Board(25,24);
		
		// initialise users and their position in queue
		turnQueue = new ArrayDeque<>();
		String[] names = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Mr Green", "Mrs Peacock", "Professor Plum"};
		for (int i = 0; i < playerCount; i++) {
			turnQueue.offer(new User(names[i])); //user may need further input in future
		}
		
		// distribute cards
		Deck deck = new Deck();
		envelope = new Envelope(deck.getEnvelopeContents());
		dealHands(deck);
		
		play();
	}
	
	/**
	 * Deal cards in deck to users
	 * 
	 * @param deck
	 */
	private void dealHands(Deck deck) {
		while (!deck.isEmpty()) {
			nextPlayer().addCard(deck.randomCard());
		}
		
		// reset turn order so miss scar scar starts
	}
	
	/**
	 * Play the game. This is were the high level logic should be
	 */
	private void play() {
		// get next player
		
		// roll dice
		
		// tell player where they can move
		
		// take input of where they want to move
		
		// execute move
	}
	
	/**
	 * This is for when somebody guesses what is in the envelope
	 */
	private void endGame() {}
	
	/**
	 * Roll a pair of imaginary dice
	 * @return number of moves
	 */
	private int roll() {
		return (int)(Math.random() * 5 + 1) + (int)(Math.random() * 5 + 1);
	}
	
	/**
	 * Gets the player who's turn is next.
	 */
	private User nextPlayer() {return null;}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("How many people are playing? (3 to 6)");
		Integer playerCount = 0;
		
		boolean validInput = false;
		while (!validInput) {
			String s = in.nextLine();
			try {
				playerCount = Integer.parseInt(s);
				if (playerCount <= 6 && playerCount>= 3) validInput = true;
				else throw new Error("Please enter a number between three and six.");
			} catch (ClassCastException e) {
				System.out.println("\"" + s + "\" is not a number please enter a number.");
			} catch (Error e) {
				System.out.println(e);
			}
		}
		
		new Cluedo(playerCount);
	}
}
