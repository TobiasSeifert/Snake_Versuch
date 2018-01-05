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
import objekte.Schlange;

@SuppressWarnings("serial")
public class Spiel extends JPanel implements ActionListener {
	private int level_Counter = 0;
	private int level_Teiler = 15;
	private int level_Teiler_Counter = 1;

	private KeyListener pfeiltasten = new ButListener();

	private BufferedImage head_0, head_1, head_2, head_3;
	private BufferedImage apple;
	
	//Am Anfang negativ, damit nicht gezeichnet
	private Apfel apfel = new Apfel(-1,-1);
	
	private Schlange schlange;

	private boolean alive;

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
		schlange.bestimmeSchlange();
		apfelChecken();
		gameOverChecken();
		levelChecken();
	}

	public void neuesSpiel() {
		schlange = new Schlange(2,2); // 2 = Körper 2 lang, 2 = Richtung nach links
		score = 0;
		erschaffeApfel();
	}

	public void starten() {
		zeitAnfang = (int) (System.currentTimeMillis() / 1000);
		minutes = 0;
		alive = true;
		t.start();
	}

	public void apfelChecken() {
		if (schlange.getX_Koordinate(0) == apfel.getX_Koordinate() && schlange.getY_Koordinate(0) == apfel.getY_Koordinate()) {
			esseApfel();
			erschaffeApfel();
		}
	}
	
	public void erschaffeApfel() {
		Random random = new Random();
		outer: while (true) {
			apfel = new Apfel((random.nextInt(19) * 20), (random.nextInt(19) * 20));
			for (int i = 0; i <= schlange.getLänge_Körper(); i++) {
				if(apfel.getX_Koordinate() == schlange.getX_Koordinate(i)
						&& apfel.getY_Koordinate() == schlange.getY_Koordinate(i)) {
					continue outer;
				}
			}
			break;
		}
		repaint();
	}

	public void gameOverChecken() {
		JLabel label = new JLabel("Dein Score beträgt " + score + "!");
		label.setFont(new Font("Times New Roman", Font.ITALIC + Font.BOLD, 16));
		if (schlange.getX_Koordinate(0) < 0 || schlange.getX_Koordinate(0) > 380 || schlange.getY_Koordinate(0) < 0
				|| schlange.getY_Koordinate(0) > 380) {
			alive = false;
			System.out.println("Wand");
			JOptionPane.showMessageDialog(Spiel.this, label, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			neuesSpiel();

		}

		for (int i = 1; i <= schlange.getLänge_Körper(); i++) {
			if ((schlange.getX_Koordinate(0) == schlange.getX_Koordinate(i))
					&& (schlange.getY_Koordinate(0) == schlange.getY_Koordinate(i))) {
				alive = false;
				System.out.println("schlange");
				JOptionPane.showMessageDialog(Spiel.this, label, "Game Over", JOptionPane.INFORMATION_MESSAGE);
				neuesSpiel();
			}
		}
	}
	
	public void levelChecken() {
		if (level_Counter++ % level_Teiler == 0) {
			schlange.verlängerSchlange();
			if (level_Teiler_Counter++ % 10 == 0) {
				level_Teiler--;
			}
		}
	}
	
	public void esseApfel() {
		schlange.verlängerSchlange();
		score += 100;
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
			} else if ((e.getKeyCode() == KeyEvent.VK_UP) && (schlange.getRichtung() != 1)) {
				schlange.setRichtung(0);
			} else if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (schlange.getRichtung() != 0)) {
				schlange.setRichtung(1);
			} else if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (schlange.getRichtung() != 2)) {
				schlange.setRichtung(3);
			} else if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (schlange.getRichtung() != 3)) {
				schlange.setRichtung(2);
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

			switch (schlange.getRichtung()) {
			case 0:
				g.drawImage(head_0, schlange.getX_Koordinate(0), schlange.getY_Koordinate(0), this);
				break;
			case 1:
				g.drawImage(head_1, schlange.getX_Koordinate(0), schlange.getY_Koordinate(0), this);
				break;
			case 2:
				g.drawImage(head_2, schlange.getX_Koordinate(0), schlange.getY_Koordinate(0), this);
				break;
			case 3:
				g.drawImage(head_3, schlange.getX_Koordinate(0), schlange.getY_Koordinate(0), this);
			}

			for (int i = 1; i <= schlange.getLänge_Körper(); i++) {
				g.setColor(Color.BLUE);
				g.fillOval(schlange.getX_Koordinate(i), schlange.getY_Koordinate(i), 20, 20);
			}

			g.drawImage(apple, apfel.getX_Koordinate(), apfel.getY_Koordinate(), this);

			Font font = new Font("Times New Roman", Font.BOLD, 14);

			g.setColor(Color.white);
			g.setFont(font);

			String S_Score = "Score: " + score;
			g.drawString(S_Score, 300, 15);

			String S_Länge = "Länge: " + (schlange.getLänge_Körper() + 1);
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
