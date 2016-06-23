package Tetris;

import gfx.Rectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * This is my Board class. My board is a JPanel where I draw my squares. It contains an array that takes in tetris squares. This is where I handle
 * most of the logic. Each element of the array corresponds to a location on the board. When a tetris piece enters my board or leaves my board, it also enters or leaves my array. 
 * Not doing it this way occasionally led to the squares locations being a little off on the board. 
 * 
 */


public class Board extends JPanel{
	private TetrisSquare[][] _array;
	private TetrisSquare _tSquare;
	private Rectangle _sCheck;
	private TetrominoFactory _tFactory;
	private ProxyPiece _proxyPiece;
	private MyTimer _timer;
	private int _numOfCols, _numOfRows;
	private boolean _pause;
	private boolean _isGameOver;
	
	public Board() {
		super();
		
		_pause = false;
		_isGameOver = false;
		
		this.setFocusable(true); // so that the board listens to myKeyListener
		this.addKeyListener(new myKeyListener()); //myKeyListener is a private class in this class
		
		Dimension size = new Dimension(200, 575);
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.setVisible(true);
		this.setBackground(java.awt.Color.black);
				
		_array = new TetrisSquare[21][12]; 
				
		_tFactory = new TetrominoFactory();
		TetrisPiece _tPiece = _tFactory.getTetromino(this); //Tetromino Factory returns a random tetris piece
		_proxyPiece = new ProxyPiece(); //proxy piece that is set to whatever piece is in play
		_proxyPiece.setPiece(_tPiece);
			
		
		//The following two loops add tetris squares into the edges of the array and board so that my tetris pieces do not move out of my board.
		// I make it so that no two squares can overlap. 
		for (int j = 1; j < 11; j++) {	
			int[] _endRows = {0, 20};
			for (int endRows : _endRows) {
				TetrisSquare sq = new TetrisSquare(this);
				sq.setInArray(endRows, j);
			}
		}
		
		for (int i = 0; i < 20; i++) {
			int[] _endCols = {0, 11};
			for (int endCols : _endCols ) {
				TetrisSquare sq = new TetrisSquare(this);
				sq.setInArray(i, endCols);
			}
		}
		_timer = new MyTimer(this); //MyTimer calls on play() which is the method that moves the tetris piece in play down and calls on
		_timer.start(); // my clearLine() method when necessary.
	}
	
	public void setGameOver(boolean isGameOver) { //I have this setter method because my TetrisPiece checks if it overlaps with something.
		if (isGameOver == true) { // If it overlaps with a piece during construction, the game is over so then it uses this method to set Game Over.
			_isGameOver = true; 
		}
		else if (isGameOver == false) {
			_isGameOver = false;
		}
	}
	
	public void paintComponent(Graphics brush) {
		super.paintComponent(brush); //partial override. the superclass method calls paint on Swing elements
		
		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 12; j++) {
				if (_array[i][j] != null) {
					_array[i][j].paint(brush); //paint Tetris squares in array
				}
			}
		}
		if (_pause == true) {
			brush.drawString("Paused", 50, 50); //this method needs a brush and this is where brush is.
		}
		if (_isGameOver == true) {
			brush.drawString("Game Over", 50, 50);
		}
	}
	
	public void play() {
		if (_pause == false && _isGameOver == false) {
			_proxyPiece.gravity();
			this.repaint();
		}
		//and if the game is paused or over, nothing happens when MyTimer calls this method.
	}
		
	public TetrisSquare[][] getArray() {
		return _array;
	}
	
	public void newPiece() { //called when a new piece is in play
		if (_isGameOver == false) {
			int checkCL = 0; //check if there are any lines that I need to clear before creaing a new piece.
			for (int i = 1; i < 20; i++) {
				for (int j = 1; j < 11; j++) {
					if (_array[i][j] != null) {
						checkCL++;
					}
				}
				if (checkCL == 10) {
					this.clearLine(i);
				}
				checkCL = 0;
			}
			TetrisPiece tetromino = _tFactory.getTetromino(this);
			_proxyPiece.setPiece(tetromino); //sets _proxyPiece to be new piece
		}
	}
	
	public void clearLine(int i) {
		for (int col = 1; col < 11; col++) {
			_array[i][col] = null; //nulls full row
		}
		i--;
		while (i > 0) { //drops other rows. I need to work my way up the board.
			for (int col = 1; col < 11; col++ ) {
				if (_array[i][col] != null) { 
					_array[i][col].setInArray(i+1, col);
					_array[i][col] = null; //I need to work my way up the board because for each row, I need to null its bottom row before dropping that particular row.
				}
				else if (_array[i][col] == null) {
					_array[i+1][col] = null;
				}
			}
			i--;
		}
	}
	
	private class myKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyPressed = e.getKeyCode();
			if (keyPressed == KeyEvent.VK_P) {
				if (_pause == true) {
					_pause = false;
				}
				else if (_pause == false) {
					_pause = true;
				}
			}
			if (_pause == false && _isGameOver == false) { //board only responds to key presses when the game is not paused nor over
				if (keyPressed == KeyEvent.VK_LEFT) {
					_proxyPiece.moveLeft();
				}
				if (keyPressed == KeyEvent.VK_RIGHT) {
					_proxyPiece.moveRight();
				}
				if (keyPressed == KeyEvent.VK_UP) {
					_proxyPiece.rotate();
				}
				if (keyPressed == KeyEvent.VK_DOWN) {
					_proxyPiece.moveDown();
				}
				if (keyPressed == KeyEvent.VK_SPACE) {
					_proxyPiece.drop();
				}
				Board.this.repaint();
			}				
		}

		@Override
		public void keyReleased(KeyEvent arg0) {	}

		@Override
		public void keyTyped(KeyEvent arg0) {	}
	}
}
