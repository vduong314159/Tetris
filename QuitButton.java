package Tetris;
/*
 * This is my QuitButton class and I tell my quit button what to do hen it is clicked. It is a subclass of JButton and most of 
 * what it does is in the Java support code. 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitButton extends javax.swing.JButton {
	
	public QuitButton() {
		super("Quit");
		this.addActionListener(new QuitListener());
	}
	private class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0); // waits zero seconds before exiting
		}
	}
}
