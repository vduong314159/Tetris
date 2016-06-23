package Tetris;

import static Tetris.Constants.*;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

/*
 * This is my TPiece class. My SPiece is the tetromino in the shape of an T.
 */

public class IPiece extends TetrisPiece{
	private TetrisSquare[][] _board;
	private boolean _isHorizontal;
	
	public IPiece(Board board) {
		super(board);
		_board = board.getArray();
		
		this.setColor(Color.cyan);
		
		for (int sq = 0; sq < 4; sq++) {
			int i = 1;
			int j = sq + 4;
			this.get(sq).setInArray(i, j);
		}
		_isHorizontal = true;
	}
	
	public void rotate() {
		int i2 = this.get(2).getI();
		int j2 = this.get(2).getJ();
		Point centerOfRotation = new Point(j2, i2);
		
		if (_isHorizontal == true) {
			this.rotateCW(centerOfRotation);	
		}
		else if (_isHorizontal == false) {
			this.rotateCCW(centerOfRotation);
		}		
	}  
} 





