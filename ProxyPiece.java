package Tetris;
/*
 * This is my proxy piece class. The proxy piece is set to be the tetris piece in play. It makes references easier to deal with in the game.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;

public class ProxyPiece{
	private TetrisPiece _tPiece;

	public ProxyPiece() {
		
	}

	public void setPiece(TetrisPiece tPiece) {
		_tPiece = tPiece;
	}
	
	public void moveLeft() {
		_tPiece.moveLeft();
	}
	
	public void moveRight() {
		_tPiece.moveRight();
		
	}
	
	 public void gravity() {
		_tPiece.gravity();
	} 
	 
	public void moveDown() {
		_tPiece.moveDown();
	}
	
	public void drop() {
		_tPiece.drop();
	}
	
	public void paint(Graphics brush) {
		_tPiece.paint((Graphics2D)brush);
	}
	
	public void rotate() {
		_tPiece.rotate();
	}

	
}
