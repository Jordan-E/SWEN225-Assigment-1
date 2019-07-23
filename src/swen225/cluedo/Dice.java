package swen225.cluedo;

import java.util.Random;

/**
 * Dice simulates two die being thrown
 * 
 * @author Ellisjord
 *
 */
public class Dice {
	
	/**
	 * Throws two dice using math.random. 
	 * 
	 * @return An Integer between 2-12
	 */
	public int roll() { //could just make as a method in cluedo class
		return (int) ((Math.random() * 5 + 1) + (Math.random() * 5 + 1));
	}
}
