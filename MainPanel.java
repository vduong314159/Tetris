package Tetris;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/*
 * This is my MainPanel class. It contains my board and quit button panel.
 */

public class MainPanel extends JPanel {
	
	public MainPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(new Board());
		this.add(new QuitButtonPanel());
		
		this.setVisible(true);
	}
}
