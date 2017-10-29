package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import rhythm.Main;

public class GamePanel extends JPanel {
	private RhythmTyping frame;

	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	private JButton backButton = new JButton(backButtonBasicImage);

	public GamePanel(RhythmTyping rhythmTyping) {
		frame = rhythmTyping;
		
		setLayout(null); // 패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT - 30);
		
	
		
		backButton.setVisible(true);
		backButton.setBounds(30, 55, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				backButton.setIcon(backButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				backButton.setIcon(backButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
				// 메인화면으로 돌아가는 이벤트
				backSelect();

			}

		});
		add(backButton);

	}

	public void backSelect() {
		RhythmTyping.game.close();// 현재실행되고 있는 게임 종료
		frame.change("selectMusicPanel");
	}

	
}
