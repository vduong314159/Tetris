package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Comparator;

import gfx.Rectangle;

import javax.swing.JPanel;

public class TetrisSquare extends Rectangle {
	private TetrisSquare[][] _array;
	private int _i, _j;
	private Board _board;
	
	public TetrisSquare(Board board) {
		super(board);
		this.setColor(Color.white); //the squares on the edges of the board are white
		this.setSize(Constants.SQ_SIZE - 2, Constants.SQ_SIZE - 2); //subtracting 2 gives the square a black border without me having to set a border
		this.setVisible(true);
		
		_board = board;
		_array = board.getArray();	//indices on array correspond to locations on the board	
	}
	
	
	public void paint(Graphics brush) {
		this.paint((Graphics2D)brush); 
	}
	
	public void setInArray(int i, int j) { //wrapper method
		if (_array[i][j] != null) {
			_board.setGameOver(true);
		}
		_array[i][j] = this;
		_i = i;
		_j = j;
		int x = Constants.SQ_SIZE * j;
		int y = Constants.SQ_SIZE * i;
		super.setLocation(x, y);
	}
	
	public void setI(int i) {
		_i = i;
		_array[_i][_j] = this;
		int y = Constants.SQ_SIZE * i;
		super.setY(y);
	}
	
	public void setJ(int j) {
		_j = j;
		_array[_i][_j] = this;
		int x = Constants.SQ_SIZE * j;
		super.setX(x);
	}
	
	public int getI() {
		return _i;
	}
	
	public int getJ() {
		return _j;
	}
	
	public void rotateCW(Point centerOfRotation) {
		int newJ = centerOfRotation.x - centerOfRotation.y + _i;//j;
		int newI = centerOfRotation.x + centerOfRotation.y - _j;//i;
		
		this.setInArray(newI, newJ);
	}
	
	public void rotate(Point centerOfRotation) { //seems redundant but it makes coding simpler for me
		this.rotateCW(centerOfRotation);
	}
	
	public void rotateCCW(Point centerOfRotation) {
		int newJ = centerOfRotation.x + centerOfRotation.y - _i;
		int newI = -centerOfRotation.x + centerOfRotation.y + _j;
		
			this.setInArray(newI, newJ);
	}
}	

