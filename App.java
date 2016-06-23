package Tetris;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * It's time for Tetris! This is the main class to get things started. The main method of this application
 * calls the App constructor. You will need to fill in the constructor to instantiate your Tetris game.
 * 
 * Class comments here...
 * 
 * @author vduong
 *Did you discuss your design with another student? no
 *If so, list their login here:
 *
 */

public class App extends JFrame {
	
	public App() {
		super ("Tetris");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.add(new MainPanel());
		
		this.setPreferredSize(new Dimension(400, 800));
		this.setVisible(true);
		this.pack();
		
	}

	/* Here's the mainline */
	public static void main(String[] args) {
		new App();

	}

}
