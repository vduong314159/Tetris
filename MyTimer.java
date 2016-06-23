package Tetris;

import java.awt.event.*;

/**
 * 
 * This is my timer and moves the tetris piece in play down and tells the computer when to check and implement methods.
 *
 */

public class MyTimer extends javax.swing.Timer {
	private Board _board;
	
	public MyTimer(Board board){
		super(1000, null);
		this.addActionListener(new TetrisListener());
		_board = board;
	}
	
	private class TetrisListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			_board.play();
		}	
	}
}
