package Tetris;

import static Tetris.Constants.*;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;
/*
 * This is my ZPiece class. My SPiece is the tetromino in the shape of an Z.
 */

public class ZPiece extends TetrisPiece{
	private TetrisSquare[][] _array;
	private int _orientation;
	
	public ZPiece(Board board) {
		super(board);
		_array = board.getArray();
		
		this.setColor(Color.red);
		
		this.get(0).setInArray(2, 6);
		this.get(1).setInArray(2, 7);
		this.get(2).setInArray(1, 5);
		this.get(3).setInArray(1, 6);
			
		_orientation = 1;
	}
		
	public void rotate() {
		int i0 = this.get(0).getI();
		int j0 = this.get(0).getJ();
		
		Point centerOfRotation = new Point(j0, i0);
		
		if (_orientation == 1) {
			this.rotateCW(centerOfRotation);
			_orientation = 2;
		}
		else if (_orientation == 2) { //this piece would have the same orientation as if it rotated clockwise but this way, it seems to rotate more towards the center of the piece
			this.rotateCCW(centerOfRotation);
			_orientation = 1;
		}
	}
}

