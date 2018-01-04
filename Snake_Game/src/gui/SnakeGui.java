package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logik.Spiel;

@SuppressWarnings("serial")
public class SnakeGui extends JFrame {

	private CardLayout hauptLayout = new CardLayout();

	private JPanel menue;
	private JPanel snake;
	private JLabel header;
	private Spiel spielfeld;
	private JPanel startKnopf;
	private BufferedImage startBild;

	public SnakeGui() {
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setMinimumSize(new Dimension(416, 444));
		pack();
		setLocationRelativeTo(null);

		addImages();

		createWidgets();
		addWidgets();
		
		repaint();
	}

	private void addWidgets() {
		getContentPane().setBackground(Color.GRAY);

		getContentPane().setLayout(hauptLayout);
		getContentPane().add(menue, "menue");
		getContentPane().add(snake, "snake");

		snake.setLayout(new BorderLayout(5, 5));
		snake.add(BorderLayout.PAGE_START, header);

		snake.add(BorderLayout.CENTER, spielfeld);

		menue.setLayout(new BorderLayout(5, 5));
		menue.add(BorderLayout.CENTER, startKnopf);

	}

	private void createWidgets() {
		header = new JLabel("Snake Versuch");
		header.setPreferredSize(new Dimension(500, 50));
		header.setOpaque(true);
		header.setFont(header.getFont().deriveFont(Font.BOLD + Font.ITALIC, 40));
		header.setBackground(new Color(0, 0, 128));
		header.setForeground(new Color(238, 201, 0));
		header.setHorizontalAlignment(SwingConstants.CENTER);

		menue = new JPanel();
		menue.setPreferredSize(new Dimension(400, 400));

		snake = new JPanel();
		snake.setPreferredSize(new Dimension(400, 400));

		startKnopf = new BildPanel(startBild);
		startKnopf.addMouseListener(new switchCard());

		spielfeld = new Spiel();
		spielfeld.setPreferredSize(new Dimension(400, 400));

	}

	public void addImages() {
		try {
			startBild = ImageIO.read(SnakeGui.class.getClassLoader().getResourceAsStream("img/snake.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Listener
	public class switchCard implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				setMinimumSize(new Dimension(416, 494));
				hauptLayout.show(getContentPane(), "snake");
				spielfeld.setFocusable(true);
				spielfeld.requestFocus();
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
