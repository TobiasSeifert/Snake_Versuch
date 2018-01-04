package logik;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import objekte.Apfel;

@SuppressWarnings("serial")
public class Spiel extends JPanel implements ActionListener {
	private int test = 0;
	private int test2 = 20;
	private int test3 = 1;
	private int länge_Schlange;

	private KeyListener pfeiltasten = new ButListener();

	private BufferedImage head_0, head_1, head_2, head_3;
	private BufferedImage apple;
	
	//Am Anfang negativ, damit nicht gezeichnet
	private Apfel apfel = new Apfel(-1,-1);
	
	// arrays für Schlange
	private int[] schlange_x_Koordinate = new int[500];
	private int[] schlange_y_Koordinate = new int[500];

	private boolean alive;

	// 0 = up, 1 = down, 2 = right, 3 = left
	private static int direction;

	private static int score;

	private Timer t = new Timer(180, this);

	private int zeitAnfang;
	private int seconds;
	private int minutes;

	public Spiel() {
		setFocusable(true);
		requestFocusInWindow();

		addKeyListener(pfeiltasten);
		addImages();
	}

	public void snake_move() {
		directionChecken();
		apfelChecken();
		gameOverChecken();
		levelChecken();
	}

	public void neuesSpiel() {
		länge_Schlange = 2;
		direction = 2;
		score = 0;

		erschaffeSchlange();

		erschaffeApfel();
	}

	public void starten() {
		zeitAnfang = (int) (System.currentTimeMillis() / 1000);
		minutes = 0;
		alive = true;
		t.start();
	}

	public void apfelChecken() {
		if (schlange_x_Koordinate[0] == apfel.getX_Koordinate() && schlange_y_Koordinate[0] == apfel.getY_Koordinate()) {
			esseApfel();
			erschaffeApfel();
		}

	}

	public void erschaffeApfel() {
		Random random = new Random();
		apfel.setX_Koordinate(random.nextInt(19) * 20);
		apfel.setY_Koordinate(random.nextInt(19) * 20);
		for (int i = 0; i <= länge_Schlange; i++) {
			while (apfel.getX_Koordinate() == schlange_x_Koordinate[i] && apfel.getY_Koordinate() == schlange_y_Koordinate[i]) {
				apfel.setX_Koordinate(random.nextInt(19) * 20);
				apfel.setY_Koordinate(random.nextInt(19) * 20);
			}
		}
		repaint();
	}

	public void erschaffeSchlange() {
		schlange_x_Koordinate[0] = 160;
		schlange_y_Koordinate[0] = 140;

		schlange_x_Koordinate[1] = 140;
		schlange_y_Koordinate[1] = 140;

		schlange_x_Koordinate[2] = 120;
		schlange_y_Koordinate[2] = 140;
	}

	public void gameOverChecken() {
		JLabel label = new JLabel("Dein Score beträgt " + score + "!");
		label.setFont(new Font("Times New Roman", Font.ITALIC + Font.BOLD, 16));
		if (schlange_x_Koordinate[0] < 0 || schlange_x_Koordinate[0] > 380 || schlange_y_Koordinate[0] < 0
				|| schlange_y_Koordinate[0] > 380) {
			alive = false;
			JOptionPane.showMessageDialog(Spiel.this, label, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			neuesSpiel();

		}

		for (int i = 1; i <= länge_Schlange; i++) {
			if ((schlange_x_Koordinate[0] == schlange_x_Koordinate[i])
					&& (schlange_y_Koordinate[0] == schlange_y_Koordinate[i])) {
				alive = false;
				JOptionPane.showMessageDialog(Spiel.this, label, "Game Over", JOptionPane.INFORMATION_MESSAGE);
				neuesSpiel();
			}
		}
	}
	
	public void levelChecken() {
		if (test++ % test2 == 0) {
			länge_Schlange++;
			schlange_x_Koordinate[länge_Schlange] = schlange_x_Koordinate[länge_Schlange - 1];
			schlange_y_Koordinate[länge_Schlange] = schlange_y_Koordinate[länge_Schlange - 1];
			if (test3++ % 10 == 0) {
				test2--;
			}
		}
	}
	
	public void esseApfel() {
		länge_Schlange++;
		schlange_x_Koordinate[länge_Schlange] = schlange_x_Koordinate[länge_Schlange - 1];
		schlange_y_Koordinate[länge_Schlange] = schlange_y_Koordinate[länge_Schlange - 1];
		score += 100;
	}

	public void directionChecken() {
		if (direction == 0) {
			for (int i = länge_Schlange; i >= 1; i--) {
				schlange_y_Koordinate[i] = schlange_y_Koordinate[i - 1];
				schlange_x_Koordinate[i] = schlange_x_Koordinate[i - 1];
			}
			schlange_y_Koordinate[0] = schlange_y_Koordinate[0] - 20;
		}
		if (direction == 1) {
			for (int i = länge_Schlange; i >= 1; i--) {
				schlange_y_Koordinate[i] = schlange_y_Koordinate[i - 1];
				schlange_x_Koordinate[i] = schlange_x_Koordinate[i - 1];
			}
			schlange_y_Koordinate[0] = schlange_y_Koordinate[0] + 20;
		}
		if (direction == 2) {
			for (int i = länge_Schlange; i >= 1; i--) {
				schlange_y_Koordinate[i] = schlange_y_Koordinate[i - 1];
				schlange_x_Koordinate[i] = schlange_x_Koordinate[i - 1];
			}
			schlange_x_Koordinate[0] = schlange_x_Koordinate[0] + 20;
		}
		if (direction == 3) {
			for (int i = länge_Schlange; i >= 1; i--) {
				schlange_y_Koordinate[i] = schlange_y_Koordinate[i - 1];
				schlange_x_Koordinate[i] = schlange_x_Koordinate[i - 1];
			}
			schlange_x_Koordinate[0] = schlange_x_Koordinate[0] - 20;
		}
	}

	public void addImages() {
		try {
			apple = ImageIO.read(Spiel.class.getClassLoader().getResourceAsStream("img/apple.png"));
			head_0 = ImageIO.read(Spiel.class.getClassLoader().getResourceAsStream("img/kopf_0.png"));
			head_1 = ImageIO.read(Spiel.class.getClassLoader().getResourceAsStream("img/kopf_1.png"));
			head_2 = ImageIO.read(Spiel.class.getClassLoader().getResourceAsStream("img/kopf_2.png"));
			head_3 = ImageIO.read(Spiel.class.getClassLoader().getResourceAsStream("img/kopf_3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (alive) {
			snake_move();
			seconds = (int) ((System.currentTimeMillis() / 1000) - zeitAnfang);
			if (seconds > 60) {
				zeitAnfang += 60;
				minutes++;
			}
		}
		addKeyListener(pfeiltasten);
		repaint();
	}

	public class ButListener implements KeyListener {
		// TODO überarbeiten, da bei zu schnellem drücken nicht erkannt
		@Override
		public void keyPressed(KeyEvent e) {
			removeKeyListener(pfeiltasten);
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				neuesSpiel();
				starten();
			} else if ((e.getKeyCode() != KeyEvent.VK_UP) && (e.getKeyCode() != KeyEvent.VK_DOWN)
					&& (e.getKeyCode() != KeyEvent.VK_RIGHT) && (e.getKeyCode() != KeyEvent.VK_LEFT)
					&& (e.getKeyCode() != KeyEvent.VK_ENTER)) {
				System.out.println("Falsche Eingabe!");
				addKeyListener(pfeiltasten);
			} else if ((e.getKeyCode() == KeyEvent.VK_UP) && (direction != 1)) {
				direction = 0;
			} else if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (direction != 0)) {
				direction = 1;
			} else if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (direction != 2)) {
				direction = 3;
			} else if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (direction != 3)) {
				direction = 2;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}

	// zeichnen der Schlange
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 400);

		if (!alive) {
			Font font = new Font("Times New Roman", Font.BOLD, 20);

			g.setColor(Color.WHITE);
			g.setFont(font);

			String startMsg = "Press \n 'Enter' \n to start!";
			g.drawString(startMsg, 100, 175);

		}
		if (alive) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			switch (direction) {
			case 0:
				g.drawImage(head_0, schlange_x_Koordinate[0], schlange_y_Koordinate[0], this);
				break;
			case 1:
				g.drawImage(head_1, schlange_x_Koordinate[0], schlange_y_Koordinate[0], this);
				break;
			case 2:
				g.drawImage(head_2, schlange_x_Koordinate[0], schlange_y_Koordinate[0], this);
				break;
			case 3:
				g.drawImage(head_3, schlange_x_Koordinate[0], schlange_y_Koordinate[0], this);
			}

			for (int i = 1; i <= länge_Schlange; i++) {
				g.setColor(Color.BLUE);
				g.fillOval(schlange_x_Koordinate[i], schlange_y_Koordinate[i], 20, 20);
			}

			g.drawImage(apple, apfel.getX_Koordinate(), apfel.getY_Koordinate(), this);

			Font font = new Font("Times New Roman", Font.BOLD, 14);

			g.setColor(Color.white);
			g.setFont(font);

			String S_Score = "Score: " + score;
			g.drawString(S_Score, 300, 15);

			String S_Länge = "Länge: " + (länge_Schlange + 1);
			g.drawString(S_Länge, 170, 15);

			if (minutes < 10) {
				if (seconds < 10) {
					String S_Clock = "0" + minutes + ":0" + seconds;
					g.drawString(S_Clock, 50, 15);
				} else {
					String S_Clock = "0" + minutes + ":" + seconds;
					g.drawString(S_Clock, 50, 15);
				}
			} else {
				if (seconds < 10) {
					String S_Clock = minutes + ":0" + seconds;
					g.drawString(S_Clock, 50, 15);
				} else {
					String S_Clock = minutes + ":" + seconds;
					g.drawString(S_Clock, 50, 15);
				}

			}
		}

	}

}
