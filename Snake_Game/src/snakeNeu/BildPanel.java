package snakeNeu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class BildPanel extends JPanel{
	
	private BufferedImage img;
	
	public BildPanel(BufferedImage img) {
		this.img = img;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, this);
	}

}
