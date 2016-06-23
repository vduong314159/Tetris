package Tetris;

/*
 * This is my Tetris Piece class. It is an abstract class. Here, I describe methods that all my tetris pieces use.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static Tetris.Constants.*;
import gfx.Rectangle;

import javax.swing.JPanel;

public abstract class TetrisPiece {
	private ArrayList<TetrisSquare> _squares;
	private TetrisSquare[][] _array;
	private Board _board;
	
	public TetrisPiece(Board board) {
		_squares = new ArrayList<TetrisSquare>();
		_array = board.getArray();
		_board = board;
		
		for (int i=0; i<4; i++) {
			_squares.add(new TetrisSquare(board));
		}		
	}
	
	public void setColor(Color color) {
		for (int i=0; i<4; i++) {
			_squares.get(i).setColor(color);
		}
	}
	
	public boolean canMoveLeft() {
		int check = 0;
		int n = 0;
		
		for (TetrisSquare sq: _squares) {
			int i = sq.getI();
			int j = sq.getJ();
			int newJ = j - 1;
			TetrisSquare sqOnLeft = _array[i][newJ];
			n = n + 1;
			
			if (sqOnLeft == null || _squares.contains(sqOnLeft)) {
				check = check + 1;
			}
		}
		if (check == 4) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void moveLeft() {
		int check = 0;
		int n = 0;
		
		for (TetrisSquare sq: _squares) {
			int i = sq.getI();
			int j = sq.getJ();
			int newJ = j - 1;
			TetrisSquare sqOnLeft = _array[i][newJ];
			n = n + 1;
			
			if (sqOnLeft == null || _squares.contains(sqOnLeft)) {
				check = check + 1;
			}
		}
		if (check == 4) {
			for (TetrisSquare sq: _squares){
				int i = sq.getI();
				int j = sq.getJ();
				int newJ = j - 1;
				
				_array[i][j] = null;
			}
			for (TetrisSquare sq: _squares){
				int j = sq.getJ();
				int newJ = j - 1;
				
				sq.setJ(newJ);
			}
		}
		else {
		}
	}
	
	public void moveRight() {
		int check = 0;
		int n = 0;
		
		for (TetrisSquare sq: _squares) {
			int i = sq.getI();
			int j = sq.getJ();
			int newJ = j + 1;
			TetrisSquare sqOnRight = _array[i][newJ];
			n = n + 1;
			
			if (sqOnRight == null || _squares.contains(sqOnRight)) {
				check = check + 1;
			}
		}
		if (check == 4) {
			for (TetrisSquare sq: _squares){
				int i = sq.getI();
				int j = sq.getJ();
				int newJ = j + 1;
				
				_array[i][j] = null;
			}
			for (TetrisSquare sq: _squares){
				int i = sq.getI();
				int j = sq.getJ();
				int newJ = j + 1;
				
				sq.setJ(newJ);
			}
		}
		else {
		}
	}
		
	public TetrisSquare get(int i) {		
		return _squares.get(i);
	}
	
	public void paint(Graphics brush) {
		for (int i=0; i<4; i++) {
			_squares.get(i).paint(brush);
		}
	}
	
	public void gravity() {
		int check = 0;
		int n = 0;
		
		for (TetrisSquare sq: _squares) {
			int i = sq.getI();
			int j = sq.getJ();
			int newI = i + 1;
			TetrisSquare sqBelow = _array[newI][j];

			if (newI < 20) {
				if (sqBelow == null || _squares.contains(sqBelow)) {
					check = check + 1;
				}
			}		
		}
		if (check == 4) {
			for (TetrisSquare sq: _squares){
				int i = sq.getI();
				int j = sq.getJ();
				
				_array[i][j] = null;
			}
			for (TetrisSquare sq: _squares){
				int i = sq.getI();
				int j = sq.getJ();
				int newI = i + 1;
				
				sq.setI(newI);
			}
		}
		else if (check != 4) { //tetris piece is at its lowest point and new piece needs to be generated
			_board.newPiece();
		}
	}
	
	public void moveDown() {
		this.gravity();
	}
	
	public boolean canFall() {
		int check = 0;
		int n = 0;
		
		for (TetrisSquare sq: _squares) {
			int i = sq.getI();
			int j = sq.getJ();
			int newI = i + 1;
			TetrisSquare sqBelow = _array[newI][j];

			if (newI < 20) {
				if (sqBelow == null || _squares.contains(sqBelow)) {
					check = check + 1;
				}
			}			
		}
		
		if (check == 4) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public void drop() {
		while (this.canFall()) {
			this.gravity();
		}
	}
	
	public void rotateCW(Point centerOfRotation) {
		int check = 0;
		int n = 0;
		//System.out.println("Implement canMoveLeft()");
		for (TetrisSquare sq: _squares) {
			int i = sq.getI();
			int j = sq.getJ();
			int newJ = centerOfRotation.x - centerOfRotation.y + i;
			int newI = centerOfRotation.x + centerOfRotation.y - j;
			
			TetrisSquare sqRotd = _array[newI][newJ];
			
			if (newI < 20 && newI > 0 && newJ > 0 && newJ < 11) {
				if (sqRotd == null || _squares.contains(sqRotd)) {
					check = check + 1;
				}
			}			
		}
		if (check == 4) {
			for (TetrisSquare sq: _squares){
				int i = sq.getI();
				int j = sq.getJ();
				
				_array[i][j] = null;
			}
			for (TetrisSquare sq: _squares){
				int i = sq.getI();
				int j = sq.getJ();
				int newJ = centerOfRotation.x - centerOfRotation.y + i;//j;
				int newI = centerOfRotation.x + centerOfRotation.y - j;//i;
				
				sq.setInArray(newI, newJ);
			}
		}
		else {
		}
	}
	
	public void rotateCCW(Point centerOfRotation) {
		int check = 0;
		int n = 0;
		for (TetrisSquare sq: _squares) {
			int i = sq.getI();
			int j = sq.getJ();
			int newJ = centerOfRotation.x + centerOfRotation.y - i;
			int newI = -centerOfRotation.x + centerOfRotation.y + j;
			
			TetrisSquare sqRotd = _array[newI][newJ];
			
			if (newI < 20 && newI > 0 && newJ > 0 && newJ < 11) {
				if (sqRotd == null || _squares.contains(sqRotd)) {
					check++;
				}
			}
		}
		if (check == 4) {
			for (int sq = 0; sq < 4; sq++) {
				int i = this.get(sq).getI();
				int j = this.get(sq).getJ();
				_array[i][j] = null;
			}
			
			for (int sq = 0; sq < 4; sq++) {
					this.get(sq).rotateCCW(centerOfRotation);
			}
		}
	}
	
	public void rotate() {
		int i2 = this.get(2).getI();
		int j2 = this.get(2).getJ();
		
		Point centerOfRotation = new Point(j2, i2);
		this.rotateCCW(centerOfRotation);
	}
}
