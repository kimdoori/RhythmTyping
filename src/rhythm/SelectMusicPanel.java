package rhythm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class SelectMusicPanel extends JPanel {

	private RhythmTyping frame;

	// 종료버튼
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private Image musicTrack = new ImageIcon(Main.class.getResource("../images/musicTrack.png")).getImage();

	private Image musicTrackLine = new ImageIcon(Main.class.getResource("../images/musicTrackLine.png")).getImage();

	public SelectMusicPanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		setLayout(null); // 패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT - 30);

		// musicTrack.setBounds(0, 0, 1280, 100);
		// add(musicTrack);
		// musicTrackLine.setBounds(0, 100, 1280, 4);
		// add(musicTrack);

		JButton goButton = new JButton("go");
		goButton.setBounds(1200, 50, 30, 30);
		goButton.setBorderPainted(false);
		goButton.setContentAreaFilled(false);
		goButton.setFocusPainted(false);
		goButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				goButton.setIcon(exitButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				goButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				goButton.setIcon(exitButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				goButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
				
				frame.change("gamePanel");
			}

		});
		add(goButton);

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(musicTrack, 0, 30, null);
		g.drawImage(musicTrack, 0, 134, null);
		g.drawImage(musicTrack, 0, 238, null);
		// g.drawImage(musicTrack, 0, 372, null);
		// g.drawImage(musicTrack, 0, 506, null);
		// g.drawImage(musicTrack, 0, 640, null);

		//
		// g.setColor(Color.white);
		// g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// g.setFont(new Font("Arial", Font.BOLD, 30));
		// g.drawString(titleName, 20, 702);
		// g.drawString(difficulty, 1190, 702);
		// g.setColor(Color.DARK_GRAY);
		// g.drawString("S", 270, 609);
		// g.drawString("D", 374, 609);
		// g.drawString("f", 478, 609);
		// g.drawString("Space Bar", 580, 609);
		// g.drawString("J", 784, 609);
		// g.drawString("K", 889, 609);
		// g.drawString("L", 993, 609);
		// g.setColor(Color.LIGHT_GRAY);
		// g.setFont(new Font("Elephant", Font.BOLD, 30));
		// g.drawString("00000", 565, 702);

		this.repaint();

	}
	
	

}
