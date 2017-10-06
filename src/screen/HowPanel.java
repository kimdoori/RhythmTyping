package screen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import rhythm.Main;

public class HowPanel extends JPanel {
	private RhythmTyping frame;
	//TODO: 화면 스르륵
	// start버튼에 넣을 이미지
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/howToPlay.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/howToPlay.png"));
	// start버튼
	private JButton startButton = new JButton(startButtonBasicImage);
	
	public HowPanel(RhythmTyping rhythmTyping) {
		frame=rhythmTyping;
		setLayout(null); //패널의 Layout을 NULL
		setBackground(new Color(255, 0, 0, 0));
		setBounds(0, 30,Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT-30);
		
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스가 올라왔을 때 실행
				startButton.setIcon(startButtonEnteredImage);
				// 버튼위에 마우스가 올라가면 손 모양으로 바뀜
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {// 해당 버튼에서 마우스가 나왔을 때
				startButton.setIcon(startButtonBasicImage);
				// 마우스가 버튼에서 벗어나면 원래 모양으로 바뀜
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// TODO:화면전환
			@Override
			public void mousePressed(MouseEvent e) {// 버튼이 클릭됬을 때
				// 버튼 안보이게
				// introMusic 안들리게
				// enterMain();
				// TODO:selectMusicView로 이동
				frame.change("selectMusicPanel");


			}

		});
		add(startButton);
	}

}
