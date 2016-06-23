package Tetris;

import static Tetris.Constants.*;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

/*
 * This is my TPiece class. My SPiece is the tetromino in the shape of an T.
 */

public class JPiece extends TetrisPiece{
	private int _orientation;
	private TetrisSquare[][] _array;
	
	public JPiece(Board board) {
		super(board);
		_array = board.getArray();
		
		this.setColor(Color.PINK);
		
		this.get(0).setInArray(2, 6); //I manually set each tetris square in the array because I felt that thinking of ways to set FOUR squares in a pattern with booleans and loops would take more energy.
		this.get(1).setInArray(1, 4); 
		this.get(2).setInArray(1, 5);
		this.get(3).setInArray(1, 6);
		
		_orientation = 0;
	}
	
	public void rotate() {
		int i2 = this.get(2).getI();
		int j2 = this.get(2).getJ();
		
		Point centerOfRotation = new Point(j2, i2);
		
		int _check = 0;
		for (int sq = 0; sq < 4; sq ++) {
			if (sq != 2) {
				int i = this.get(sq).getI();
				int j = this.get(sq).getJ();
				
				int newJ = centerOfRotation.x - centerOfRotation.y + i;//j;
				int newI = centerOfRotation.x + centerOfRotation.y - j;//i;
				
				if (_array[newI][newJ] == null) {
					_check = _check + 1;
				}
			}
		}
		if (_check == 3) {
			super.rotateCW(centerOfRotation);
		}		
	} 
} 
