package Tetris;

import static Tetris.Constants.*;

import java.awt.Color;

import javax.swing.JPanel;

/*
 * This is my TPiece class. My SPiece is the tetromino in the shape of an T.
 */

public class OPiece extends TetrisPiece{
	private TetrisSquare[][] _array;
	
	public OPiece(Board board) {
		super(board);
		
		this.setColor(Color.yellow);
		
		_array = board.getArray();
		
		this.get(0).setInArray(2, 5); //I manually set each tetris square in the array because I felt that thinking of ways to set FOUR squares in a pattern with booleans and loops would take more energy.
		this.get(1).setInArray(2, 6);
		this.get(2).setInArray(1, 5);
		this.get(3).setInArray(1, 6);		
	}
	
	public void rotate() {} //It would be more practical for this class to do nothing because a rotated OPiece looks just like an unrotated OPiece.
}
