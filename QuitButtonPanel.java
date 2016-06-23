package Tetris;
/*
 * This panel is at the bottom of my frame and houses my quit button. 
 */
import javax.swing.JButton;
import javax.swing.JPanel;

public class QuitButtonPanel extends javax.swing.JPanel {
	
	public QuitButtonPanel() {
		super();
				
		JButton quit = new QuitButton();
		//place it in its container and display
		this.add(quit);
	}
}
