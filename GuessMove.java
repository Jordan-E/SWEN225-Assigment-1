package swen225.cluedo.moves;

import java.util.List;
import java.util.Map.Entry;

import swen225.cluedo.Board;
import swen225.cluedo.Board.Room;
import swen225.cluedo.User;
import swen225.cluedo.pieces.*;

/**
 * Move for placing pieces in room to make a guess
 */

public class GuessMove extends Move{ //teleport

	private final CharacterPiece character;
	private final WeaponPiece weapon;
	private final Room room;
	
	public GuessMove(User user, CharacterPiece character, WeaponPiece weapon, Room room) {
		super(user);
		this.character = character;
		this.weapon = weapon;
		this.room = room;
	}

	@Override
	public boolean isValid(Board board) {
		return true;
	}

	public CharacterPiece getCharacter() {
		return character;
	}

	public WeaponPiece getWeapon() {
		return weapon;
	}
	
	public Room getRoom() {
		return room;
	}

	@Override
	public boolean apply(Board board) {
		List<Piece> roomContains = board.getRoomContents().get(room);
		//remove weapon from old room
		for (Entry<Room, List<Piece>> piece : board.getRoomContents().entrySet()) {
			if(piece.getValue().contains(weapon)) {
				piece.getValue().remove(weapon);
			}
		}	
		//add weapon to class
		roomContains.add(weapon);
		
		//take off isOccupied for original cell
		int col = character.getX();
		int row = character.getY();
		board.getCell(row, col).setOccupied(false);
		
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				if(board.getCell(i, j).getRoom() == room && !board.getCell(i, j).isOccupied()) {
					board.getCell(i, j).setOccupied(true);
					character.move(j, i);
					return true;
				}
			}
		}
		return true;
	}
}
