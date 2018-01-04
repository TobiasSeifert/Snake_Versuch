package logik;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.SnakeGui;

public class Demo {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				JFrame frame = new SnakeGui();
				frame.setVisible(true);
			}
			
		});
		
	}
	
}
