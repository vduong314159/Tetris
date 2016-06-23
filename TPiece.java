package Tetris;

import static Tetris.Constants.*;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

/*
 * This is my TPiece class. My SPiece is the tetromino in the shape of an T.
 */

public class TPiece extends TetrisPiece{
	private TetrisSquare[][] _array;
	private int _orientation;
	
	public TPiece(Board board) {
		super(board);
		_array = board.getArray();
		
		this.setColor(Color.magenta);
		
		this.get(0).setInArray(2, 6);
		this.get(1).setInArray(1, 5);
		this.get(2).setInArray(1, 6);
		this.get(3).setInArray(1, 7);
		
		_orientation = 1;			
	}	
}
