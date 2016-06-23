package Tetris;
/*
 * This is my SPiece class. My SPiece is the tetromino in the shape of an S.
 */

import static Tetris.Constants.*;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

public class SPiece extends TetrisPiece{
	private TetrisSquare[][] _array;
	private int _orientation;
	
	public SPiece(Board board) {
		super(board);
		_array = board.getArray();
		
		this.setColor(Color.green);
		
		this.get(0).setInArray(2, 5);
		this.get(1).setInArray(2, 6);
		this.get(2).setInArray(1, 6);
		this.get(3).setInArray(1, 7);
	
		_orientation = 1;
	}
	
	public void rotate() {
		int i2 = this.get(2).getI();
		int j2 = this.get(2).getJ();
		
		Point centerOfRotation = new Point(j2, i2);
		if (_orientation == 1) {
			this.rotateCW(centerOfRotation);
			_orientation = 2;
		}
		else { //this piece would have the same orientation as if it rotated clockwise but this way, it seems to rotate more towards the center of the piece
			this.rotateCCW(centerOfRotation);
			_orientation = 1;
		}
	}
}